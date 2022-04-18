package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> superMap = Differ.generateDiff(map1, map2);

        StringBuilder x = new StringBuilder();

        for (Map<String, Object> stringObjectMap : superMap) {
            for (Map.Entry<String, Object> s : stringObjectMap.entrySet()) {
                if (stringObjectMap.containsValue("added")) {
                    x.append("Property '")
                            .append(s.getKey())
                            .append("'")
                            .append(" was added with value: ");
                    if (map2.get(s.getKey()) instanceof String) {
                        x.append("'")
                                .append(map2.get(s.getKey()))
                                .append("'")
                                .append("\n");
                    } else {
                        x.append("[complex value]").append("\n");
                    }

                } else if (stringObjectMap.containsValue("deleted")) {
                    x.append("Property '")
                            .append(s.getKey())
                            .append("'")
                            .append(" was removed")
                            .append("\n");
                } else if (stringObjectMap.containsValue("changed")) {
                    x.append("Property '")
                            .append(s.getKey())
                            .append("'")
                            .append(" was updated. From ");
                    if (map1.get(s.getKey()) instanceof String) {
                        x.append("'")
                                .append(map1.get(s.getKey()))
                                .append("'");
                    } else if (map1.get(s.getKey()) instanceof Boolean | map1.get(s.getKey()) instanceof Integer) {
                        x.append(map1.get(s.getKey()));
                    } else if (map1.get(s.getKey()) != null) {
                        x.append("[complex value]");
                    } else {
                        x.append(map1.get(s.getKey()));
                    }

                    x.append(" to ");

                    if (map2.get(s.getKey()) instanceof String) {
                        x.append("'")
                                .append(map2.get(s.getKey()))
                                .append("'");
                    } else if (map2.get(s.getKey()) instanceof Boolean
                               | map2.get(s.getKey()) instanceof Integer) {
                        x.append(map2.get(s.getKey()));
                    } else if (map2.get(s.getKey()) != null) {
                        x.append("[complex value]");
                    } else {
                        x.append(map2.get(s.getKey()));
                    }

//                    x.append("\n");
                }
            }
        }
        return x.toString();

    }
}
