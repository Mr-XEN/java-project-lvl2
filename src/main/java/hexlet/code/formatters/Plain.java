package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> diff) {
        StringBuilder builder = new StringBuilder();

        for (Map<String, Object> node : diff) {

            String oldValue = objectToFormattedString(node.get("oldValue"));
            String newValue = objectToFormattedString(node.get("newValue"));

            if (node.get("status").equals("added")) {
                builder.append("Property '")
                        .append(node.get("Field"))
                        .append("'")
                        .append(" was added with value: ")
                        .append(newValue)
                        .append("\n");
            } else if (node.get("status").equals("deleted")) {
                builder.append("Property '")
                        .append(node.get("Field"))
                        .append("'")
                        .append(" was removed")
                        .append("\n");
            } else if (node.get("status").equals("changed")) {
                builder.append("Property '")
                        .append(node.get("Field"))
                        .append("'")
                        .append(" was updated. From ")
                        .append(oldValue)
                        .append(" to ")
                        .append(newValue)
                        .append("\n");
            }
        }
        return builder.toString().trim();
    }

    public static String objectToFormattedString(Object o) {
        String result;
        if (o instanceof String) {
            result = "'" + o + "'";
        } else if (o instanceof Collection<?> | o instanceof Map<?, ?>) {
            result = "[complex value]";
        } else {
            result = String.valueOf(o);
        }
        return result;
    }
}
