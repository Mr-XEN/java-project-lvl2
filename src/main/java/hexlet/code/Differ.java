package hexlet.code;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;


//import java.io.IOException;
//import java.lang.reflect.Type;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {


    public static String generate(File filePath1, File filePath2, String formatName) throws IOException {
        String file1Extension = getFileExtension(filePath1);
        String file2Extension = getFileExtension(filePath2);
        Map<String, Object> map1 = Parser.fileToMap(filePath1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(filePath2, file2Extension);
        return Formatter.formatters(map1, map2, formatName);

    }

    public static String generate(File filePath1, File filePath2) throws IOException {

        String file1Extension = getFileExtension(filePath1);
        String file2Extension = getFileExtension(filePath2);
        Map<String, Object> map1 = Parser.fileToMap(filePath1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(filePath2, file2Extension);
        return Formatter.formatters(map1, map2);
    }


    public static String run(File file1, File file2) throws IOException {
        String file1Extension = getFileExtension(file1);
        String file2Extension = getFileExtension(file2);
        Map<String, Object> map1 = Parser.fileToMap(file1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(file2, file2Extension);
        return stylish(map1, map2);
    }

    public static List<Map<String, Object>> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {


        Set<String> mergeMap = new TreeSet<>();
        mergeMap.addAll(map1.keySet());
        mergeMap.addAll(map2.keySet());

        List<Map<String, Object>> temp = new LinkedList<>();

        for (String key : mergeMap) {

            if (!map1.containsKey(key)) {   //   added
                temp.add(Map.of(key, "added"));
            } else if (!map2.containsKey(key)) {    //  remove
                temp.add(Map.of(key, "deleted"));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {  //  unchanged
                temp.add(Map.of(key, "unchanged"));
            } else {    //  deleted
                temp.add(Map.of(key, "changed"));
            }

        }

        return temp;

    }

    public static String stylish(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> superMap = Differ.generateDiff(map1, map2);

        StringBuilder x = new StringBuilder();
        x.append("{\n");
        for (Map<String, Object> stringObjectMap : superMap) {
            for (Map.Entry<String, Object> s : stringObjectMap.entrySet()) {
                if (stringObjectMap.containsValue("added")) {
                    x.append("  + ")
                            .append(s.getKey())
                            .append(": ")
                            .append(map2.get(s.getKey()))
                            .append("\n");
                } else if (stringObjectMap.containsValue("deleted")) {
                    x.append("  - ")
                            .append(s.getKey())
                            .append(": ")
                            .append(map1.get(s.getKey()))
                            .append("\n");
                } else if (stringObjectMap.containsValue("unchanged")) {
                    x.append("    ")
                            .append(s.getKey())
                            .append(": ")
                            .append(map1.get(s.getKey()))
                            .append("\n");
                } else if (stringObjectMap.containsValue("changed")) {
                    x.append("  - ")
                            .append(s.getKey())
                            .append(": ")
                            .append(map1.get(s.getKey()))
                            .append("\n")
                            .append("  + ")
                            .append(s.getKey())
                            .append(": ")
                            .append(map2.get(s.getKey()))
                            .append("\n");
                }
            }
        }
        x.append("}");
        return x.toString();

    }


    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

//    public static Map<Map<String, Object>, String> generateDiff2(Map<String, Object> map1, Map<String, Object> map2) {
//
//        LinkedHashMap<Map<String, Object>, String> resultMap = new LinkedHashMap<>();
//        Set<String> mergeMap = new TreeSet<>();
//        mergeMap.addAll(map1.keySet());
//        mergeMap.addAll(map2.keySet());
//
//        for (String key : mergeMap) {
//
//            if (!map1.containsKey(key)) {   //   added
//                HashMap<String, Object> temp = new HashMap<>();
//                temp.put(key, map2.get(key));
//                resultMap.put(temp, "added");
//            } else if (!map2.containsKey(key)) {    //  remove
//                HashMap<String, Object> temp = new HashMap<>();
//                temp.put(key, map1.get(key));
//                resultMap.put(temp, "remove");
//            } else if (Objects.equals(map1.get(key), map2.get(key))) {  //  unchanged
//                HashMap<String, Object> temp = new HashMap<>();
//                temp.put(key, map1.get(key));
//                resultMap.put(temp, "unchanged");
//            } else {    //  deleted
//                HashMap<String, Object> temp = new HashMap<>();
//                temp.put(key, map1.get(key));
//                resultMap.put(temp, "changed");
//            }
//        }
//        return resultMap;
//
//    }
//
//    public static String generateReport2(Map<String, Object> map1, Map<String, Object> map2) {
//
//        Set<String> mergeMap = new TreeSet<>();
//        mergeMap.addAll(map1.keySet());
//        mergeMap.addAll(map2.keySet());
//
//        StringBuilder x = new StringBuilder();
//        x.append("{");
//
//        for (String key : mergeMap) {
//
//            if (!map1.containsKey(key)) {
//                x.append("\n").append("  + ").append(key).append(": ").append(map2.get(key));
//            } else if (!map2.containsKey(key)) {
//                x.append("\n").append("  - ").append(key).append(": ").append(map1.get(key));
//            } else if (Objects.equals(map1.get(key), map2.get(key))) {
//                x.append("\n").append("    ").append(key).append(": ").append(map1.get(key));
//            } else {
//                x.append("\n").append("  - ").append(key).append(": ").append(map1.get(key));
//                x.append("\n").append("  + ").append(key).append(": ").append(map2.get(key));
//            }
//        }
//
//        x.append("\n").append("}");
//
//        return x.toString();
//    }


}
