package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> list) {
        StringBuilder x = new StringBuilder();

        for (Map<String, Object> s : list) {

            String oldValue = objectToFormattedString(s.get("oldValue"));
            String newValue = objectToFormattedString(s.get("newValue"));

            if (s.containsValue("added")) {
                x.append("Property '")
                        .append(s.get("Field"))
                        .append("'")
                        .append(" was added with value: ")
                        .append(newValue)
                        .append("\n");
            } else if (s.containsValue("deleted")) {
                x.append("Property '")
                        .append(s.get("Field"))
                        .append("'")
                        .append(" was removed")
                        .append("\n");
            } else if (s.containsValue("changed")) {
                x.append("Property '")
                        .append(s.get("Field"))
                        .append("'")
                        .append(" was updated. From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            }
        }
        return x.toString().trim();
    }

    private static String objectToFormattedString(Object o) {
        String result;
        if (o instanceof String) {
            result = "'" + o + "'";
        } else if (o instanceof Boolean | o instanceof Integer) {
            result = "" + o;
        } else if (o == null) {
            result = "null";
        } else {
            result = "[complex value]";
        }
        return result;
    }
}
