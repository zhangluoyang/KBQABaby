package com;

import com.alibaba.fastjson.JSON;
import com.neo4j.entity.DiseaseInformation;
import com.neo4j.entity.impl.*;
import com.nlp.model.SentenceClassification;
import com.nlp.segment.Segment;
import com.nlp.segment.nature.Nature;
import com.repository.*;
import com.utils.FileUtils;
import com.utils.O;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.nlp.process.Process;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class AppGenerateDataDemo {

    @Autowired
    private DiseaseRepository diseaseRepository;


    @Autowired
    private DepartmentRepository departmentRepository;


    @Autowired
    private DiseaseCheckRepository diseaseCheckRepository;


    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private DrugProductRepository drugProductRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private GetWayRepository getWayRepository;

    @Autowired
    private AcompanyRepository acompanyRepository;

    @Autowired
    private CureWayRepository cutWayRepository;

    @Value("${dataPath}")
    private String dataPath;

    @Value("${questionPath}")
    private String questionPath;

    @Autowired
    private Segment segment;

    @Autowired
    private Process sentenceProcess;

    @Value("${modelPath}")
    private String modelPath;

    @Value("${vocabSize}")
    private int vocabSize;

    @Value("${cSize}")
    private int cSize;

    @Value("${embeddingSize}")
    private int embeddingSize;

    @Value("${minLen}")
    private int minLen;

    @Value("${maxLen}")
    private int maxLen;

    @Value("${labelSize}")
    private int labelSize;

    @Value("${batchSize}")
    private int batchSize;

    @Test
    void segmentTest() {
        String str = "肾虚的症状是什么？";
        List<Nature> result = segment.run(str);
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    void insertNeo4j() throws FileNotFoundException {
        List<String> lines = FileUtils.readLines(FileUtils.getSpringPath(dataPath));

        List<Disease> diseasesNode = new ArrayList<>();
        List<Department> departmentsNode = new ArrayList<>();
        List<DiseaseCheck> diseaseChecksNode = new ArrayList<>();
        List<Drug> drugsNode = new ArrayList<>();
        List<Food> foodsNode = new ArrayList<>();
        List<DrugProduct> drugProductsNode = new ArrayList<>();
        List<Symptom> symptomsNode = new ArrayList<>();
        List<GetWay> getWaysNode = new ArrayList<>();
        List<CureWay> cutWaysNode = new ArrayList<>();
        List<Acompany> acompanyNode = new ArrayList<>();

        // 创建节点
        for (String line : lines) {
            DiseaseInformation info = JSON.parseObject(line, DiseaseInformation.class);
            // 疾病节点
            Disease disease = Disease.create(info.getName(), info.getPrevent(), info.getCause(), info.getYibao_status(), info.getGet_prob(),
                    info.getEasy_get(), info.getCure_lasttime(), info.getCured_prob(), info.getCost_money());
            diseasesNode.add(disease);

            if (!O.empty(info.getCheck())) {
                diseaseChecksNode.addAll(info.getCheck().stream().map(DiseaseCheck::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getSymptom())) {
                symptomsNode.addAll(info.getSymptom().stream().map(Symptom::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getRecommand_drug())) {
                drugsNode.addAll(info.getRecommand_drug().stream().map(Drug::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getDrug_detail())) {
                for (String detail : info.getDrug_detail()) {
                    String[] split = detail.replace(")", "").split("\\(");
                    if (split.length > 1) {
                        drugProductsNode.add(DrugProduct.create(split[0]));
                        drugsNode.add(Drug.create(split[1]));
                    }
                }
            }
            if (!O.empty(info.getCure_department())) {
                departmentsNode.addAll(info.getCure_department().stream().map(Department::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getNot_eat())) {
                foodsNode.addAll(info.getNot_eat().stream().map(Food::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getDo_eat())) {
                foodsNode.addAll(info.getDo_eat().stream().map(Food::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getGet_way())) {
                getWaysNode.add(GetWay.create(info.getGet_way()));
            }
            if (!O.empty(info.getCure_way())) {
                cutWaysNode.addAll(info.getCure_way().stream().map(CureWay::create).collect(Collectors.toList()));
            }
            if (!O.empty(info.getAcompany())) {
                acompanyNode.addAll(info.getAcompany().stream().map(Acompany::create).collect(Collectors.toList()));
            }
        }

        // 节点去重 并且插入 neo4j
        diseasesNode.stream().distinct().forEach(diseaseRepository::save);
        departmentsNode.stream().distinct().forEach(departmentRepository::save);
        diseaseChecksNode.stream().distinct().forEach(diseaseCheckRepository::save);
        drugsNode.stream().distinct().forEach(drugRepository::save);
        foodsNode.stream().distinct().forEach(foodRepository::save);
        drugProductsNode.stream().distinct().forEach(drugProductRepository::save);
        symptomsNode.stream().distinct().forEach(symptomRepository::save);
        getWaysNode.stream().distinct().forEach(getWayRepository::save);
        cutWaysNode.stream().distinct().forEach(cutWayRepository::save);
        acompanyNode.stream().distinct().forEach(acompanyRepository::save);

        // 关联关系 并插入 neo4j

        for (String line : lines) {
            DiseaseInformation info = JSON.parseObject(line, DiseaseInformation.class);
            List<Disease> diseases = diseaseRepository.findByName(info.getName());
            if (!O.empty(diseases)) {
                Disease disease = diseases.get(0);
                // 推荐食物
                if (!O.empty(info.getRecommand_eat())) {
                    for (String foodName : info.getRecommand_eat()) {
                        List<Food> foods = foodRepository.findByName(foodName);
                        if (!O.empty(foods)) {
                            foods.forEach(disease::withRecommandFood);
                        }
                    }
                }
                // 忌吃食物
                if (!O.empty(info.getNot_eat())) {
                    for (String not_eat : info.getNot_eat()) {
                        List<Food> foods = foodRepository.findByName(not_eat);
                        if (!O.empty(foods)) {
                            foods.forEach(disease::withNotEatFood);
                        }
                    }
                }
                // 宜吃食物
                if (!O.empty(info.getDo_eat())) {
                    for (String not_eat : info.getDo_eat()) {
                        List<Food> foods = foodRepository.findByName(not_eat);
                        if (!O.empty(foods)) {
                            foods.forEach(disease::withDoEatFood);
                        }
                    }
                }
                // 处理科室
                if (!O.empty(info.getCure_department())) {
                    for (String departmentStr : info.getCure_department()) {
                        List<Department> departments = departmentRepository.findByName(departmentStr);
                        if (!O.empty(departments)) {
                            departments.forEach(disease::withDepartMent);
                        }
                    }
                }
                // 常用药品
                if (!O.empty(info.getCommon_drug())) {
                    for (String drugStr : info.getCommon_drug()) {
                        List<Drug> drugs = drugRepository.findByName(drugStr);
                        if (!O.empty(drugs)) {
                            drugs.forEach(disease::withCommonDrugs);
                        }
                    }
                }
                // 推荐药品
                if (!O.empty(info.getRecommand_drug())) {
                    for (String drugStr : info.getRecommand_drug()) {
                        List<Drug> drugs = drugRepository.findByName(drugStr);
                        if (!O.empty(drugs)) {
                            drugs.forEach(disease::withRecommandDrug);
                        }
                    }
                }
                // 检查项目
                if (!O.empty(info.getCheck())) {
                    for (String checkStr : info.getCheck()) {
                        List<DiseaseCheck> checks = diseaseCheckRepository.findByName(checkStr);
                        if (!O.empty(checks)) {
                            checks.forEach(disease::withChecks);
                        }
                    }
                }
                // 症状
                if (!O.empty(info.getSymptom())) {
                    for (String symptom : info.getSymptom()) {
                        List<Symptom> checks = symptomRepository.findByName(symptom);
                        if (!O.empty(checks)) {
                            checks.forEach(disease::withSymptom);
                        }
                    }
                }
                // 并发症
                if (!O.empty(info.getAcompany())) {
                    for (String acompanyStr : info.getAcompany()) {
                        List<Acompany> acompanies = acompanyRepository.findByName(acompanyStr);
                        if (!O.empty(acompanies)) {
                            acompanies.forEach(disease::withAcompany);
                        }
                    }
                }
                // 得病途径
                if (!O.empty(info.getGet_way())) {
                    String way = info.getGet_way();
                    List<GetWay> getWays = getWayRepository.findByName(way);
                    if (!O.empty(getWays)) {
                        getWays.forEach(disease::withGetWay);
                    }
                }
                // 治疗途径
                if (!O.empty(info.getCure_way())) {
                    for (String cureWayStr : info.getCure_way()) {
                        List<CureWay> cureWays = cutWayRepository.findByName(cureWayStr);
                        if (!O.empty(cureWays)) {
                            cureWays.forEach(disease::withCureWay);
                        }
                    }
                }
                // 添加关系后 更新药品节点信息
                try {
                    diseaseRepository.save(disease);
                } catch (Exception e) {
                    System.out.println(e);
                }
                // 药品
                if (!O.empty(info.getDrug_detail())) {
                    for (String detail : info.getDrug_detail()) {
                        String[] split = detail.replace(")", "").split("\\(");
                        if (split.length > 1) {
                            List<DrugProduct> drugProducts = drugProductRepository.findByName(split[0]);
                            List<Drug> drugs = drugRepository.findByName(split[1]);
                            if (!O.empty(drugProducts) && !O.empty(drugs)) {
                                Drug drug = drugs.get(0);
                                DrugProduct drugProduct = drugProducts.get(0);
                                drug.withProduct(drugProduct);
                                // 添加关系后 更新节点信息
                                try {
                                    drugRepository.save(drug);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Test
    void trainTextCnn() throws FileNotFoundException {
        List<String> paths = FileUtils.getFiles(FileUtils.getSpringPath(questionPath));
        List<String> labels = new ArrayList<>();
        List<Integer> labelIds = new ArrayList<>();
        List<List<String>> linesList = new ArrayList<>();
        for (int labelId = 0; labelId < paths.size(); labelId++) {
            String path = paths.get(labelId);
            String fileName = FileUtils.getFileName(path);
            labels.add(fileName);
            labelIds.add(labelId);
            List<String> lines = FileUtils.readLines(path);
            linesList.add(lines);
        }
        List<List<Integer>> dataList = new ArrayList<>();
        List<Integer> labelList = new ArrayList<>();
        List<List<String>> dataSegments = new ArrayList<>();

        for (int i = 0; i < linesList.size(); i++) {
            List<String> lines = linesList.get(i);
            for (String line : lines) {
                List<Nature> natures = new ArrayList<>();
                List<Integer> segmentsId = new ArrayList<>();
                sentenceProcess.process(line, segmentsId, natures);
                labelList.add(labelIds.get(i));
                dataList.add(segmentsId);
                List<String> localWords = natures.stream().map(Nature::getWord).collect(Collectors.toList());
                dataSegments.add(localWords);
            }
        }

        TextCnnDataLoad trainLoad = new TextCnnDataLoad(dataList, labelList, this.labelSize, batchSize, minLen, maxLen);
        ComputationGraph net =
                SentenceClassification.getTextCnn(vocabSize, cSize, embeddingSize, labelSize);
        System.out.println(net.summary());
        int nEpochs = 128;
        net.setListeners(new ScoreIterationListener(1));
        for (int epoch = 0; epoch < nEpochs; epoch++) {
            net.fit(trainLoad);
            System.out.println("Epoch " + epoch + " complete. Starting evaluation:");
        }
        try {
            net.save(new File("C:/Users/55058/Desktop/nlp/src/test/resources/" + modelPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
