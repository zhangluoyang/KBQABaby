package com.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

    public static String getSpringPath(String path) throws FileNotFoundException {
        return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX).getAbsolutePath().replace("\\", "/")
                + "/" + path;
    }

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            while ((line = reader.readLine()) != null) {
                if(line.trim().length() > 0){
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    public static <T> void dumpList(List<T> objects, String path) {
        List<String> lines = new ArrayList<>();
        for (T obj : objects) {
            lines.add(JSON.toJSONString(obj));
        }
        writeLines(lines, path);
    }

    public static void writeLines(List<String> lines, String path) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(path)));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<String, Integer> strMapToId(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            map.put(lines.get(i), i);
        }
        return map;
    }

    public static Map<Integer, String> idMapToStr(List<String> lines) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            map.put(i, lines.get(i));
        }
        return map;
    }

    /**
     * 返回目录下所有文件
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    files.add(tempList[i].toString());
                }
            }
        }
        return files;
    }

    /**
     * 返回文件名称
     */
    public static String getFileName(String path) {
        String[] names = path.replace("\\", "/").split("/");
        return names[names.length - 1].split("\\.")[0];
    }


}
