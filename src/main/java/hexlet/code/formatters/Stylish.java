package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> list) {

        StringBuilder x = new StringBuilder();
        x.append("{\n");

        for (Map<String, Object> s : list) {
            if (s.containsValue("added")) {
                x.append("  + ")
                        .append(s.get("Field"))
                        .append(": ")
                        .append(s.get("newValue"))
                        .append("\n");
            } else if (s.containsValue("deleted")) {
                x.append("  - ")
                        .append(s.get("Field"))
                        .append(": ")
                        .append(s.get("oldValue"))
                        .append("\n");
            } else if (s.containsValue("unchanged")) {
                x.append("    ")
                        .append(s.get("Field"))
                        .append(": ")
                        .append(s.get("oldValue"))
                        .append("\n");
            } else if (s.containsValue("changed")) {
                x.append("  - ")
                        .append(s.get("Field"))
                        .append(": ")
                        .append(s.get("oldValue"))
                        .append("\n")
                        .append("  + ")
                        .append(s.get("Field"))
                        .append(": ")
                        .append(s.get("newValue"))
                        .append("\n");
            }
        }
        x.append("}");
        return x.toString();
    }
}
