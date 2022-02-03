package com.utils;

import com.hankcs.hanlp.seg.common.Term;
import com.nlp.segment.nature.Nature;
import com.nlp.segment.nature.NatureEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtils {

    public static Map<String, Integer> getStringToId(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (String line : lines) {
            map.put(line, id);
            id += 1;
        }
        return map;
    }

    public static Map<Integer, String> getIdToString(List<String> lines) {
        Map<Integer, String> map = new HashMap<>();
        int id = 0;
        for (String line : lines) {
            map.put(id, line);
            id += 1;
        }
        return map;
    }

    /**
     * 获取指定词性的词
     */
    public static List<Nature> getTerm(List<Nature> natures, NatureEnum type) {
        List<Nature> results = new ArrayList<>();
        for (Nature nature : natures) {
            if (nature.getNatureEnum().equals(type)) {
                results.add(nature);
            }
        }
        return results;
    }

    /**
     *
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

}
