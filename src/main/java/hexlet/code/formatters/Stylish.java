package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class Stylish {
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
        return x.toString().trim();

    }
}
