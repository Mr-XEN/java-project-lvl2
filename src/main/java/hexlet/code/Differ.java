package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String file1Extension = getFileExtension(filePath1);
        String file2Extension = getFileExtension(filePath2);
        String file1Context = fileToString(filePath1);
        String file2Context = fileToString(filePath2);

        Map<String, Object> map1 = Parser.fileToMap(file1Context, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(file2Context, file2Extension);
        return Formatter.format(generateDiff(map1, map2), formatName);

    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }


    public static List<Map<String, Object>> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> temp = new LinkedList<>();

        for (String key : allKeys) {

            Map<String, Object> res = new HashMap<>();

            res.put("Field", key);
            res.put("oldValue", map1.get(key));
            res.put("newValue", map2.get(key));

            if (!map1.containsKey(key)) {
                res.put("status", "added");
            } else if (!map2.containsKey(key)) {
                res.put("status", "deleted");
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                res.put("status", "unchanged");
            } else {
                res.put("status", "changed");
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

    public static String fileToString(String pathToFile) throws IOException {

        return Files.readString(Path.of(pathToFile));

    }
}
