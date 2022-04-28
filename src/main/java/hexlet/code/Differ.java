package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
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
        return Formatter.formatters(generateDiff(map1, map2), formatName);

    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }


    public static List<Map<String, Object>> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> temp = new LinkedList<>();

        for (String key : allKeys) {

            Map<String, Object> res = new HashMap<>();

            if (!map1.containsKey(key)) {
                res.put("Field", key);
                res.put("status", "added");
                res.put("oldValue", map1.get(key));
                res.put("newValue", map2.get(key));
            } else if (!map2.containsKey(key)) {
                res.put("Field", key);
                res.put("status", "deleted");
                res.put("oldValue", map1.get(key));
                res.put("newValue", map2.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                res.put("Field", key);
                res.put("status", "unchanged");
                res.put("oldValue", map1.get(key));
                res.put("newValue", map2.get(key));
            } else {
                res.put("Field", key);
                res.put("status", "changed");
                res.put("oldValue", map1.get(key));
                res.put("newValue", map2.get(key));
            }
            temp.add(res);

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
