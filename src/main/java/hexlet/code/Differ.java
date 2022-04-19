package hexlet.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        String file1Extension = getFileExtension(filePath1);
        String file2Extension = getFileExtension(filePath2);
        Map<String, Object> map1 = Parser.fileToMap(filePath1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(filePath2, file2Extension);
        return Formatter.formatters(map1, map2, formatName);

    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        String file1Extension = getFileExtension(filePath1);
        String file2Extension = getFileExtension(filePath2);
        Map<String, Object> map1 = Parser.fileToMap(filePath1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(filePath2, file2Extension);
        return Formatter.formatters(map1, map2);
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

    public static String getFileExtension(String file) {
        if (file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0) {
            return file.substring(file.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
