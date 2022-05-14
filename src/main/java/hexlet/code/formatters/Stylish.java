package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> diff) {

        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for (Map<String, Object> node : diff) {
            if (node.get("status").equals("added")) {
                builder.append("  + ")
                        .append(node.get("Field"))
                        .append(": ")
                        .append(node.get("newValue"))
                        .append("\n");
            } else if (node.get("status").equals("deleted")) {
                builder.append("  - ")
                        .append(node.get("Field"))
                        .append(": ")
                        .append(node.get("oldValue"))
                        .append("\n");
            } else if (node.get("status").equals("unchanged")) {
                builder.append("    ")
                        .append(node.get("Field"))
                        .append(": ")
                        .append(node.get("oldValue"))
                        .append("\n");
            } else if (node.get("status").equals("changed")) {
                builder.append("  - ")
                        .append(node.get("Field"))
                        .append(": ")
                        .append(node.get("oldValue"))
                        .append("\n")
                        .append("  + ")
                        .append(node.get("Field"))
                        .append(": ")
                        .append(node.get("newValue"))
                        .append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }

//    public static String stylish(List<Map<String, Object>> diff) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("{\n");
//
//        for (Map<String, Object> node : diff) {
//            if (node.containsValue("added")) {
//                builder.append("  + ")
//                        .append(node.get("Field"))
//                        .append(": ")
//                        .append(node.get("newValue"))
//                        .append("\n");
//            } else if (node.containsValue("deleted")) {
//                builder.append("  - ")
//                        .append(node.get("Field"))
//                        .append(": ")
//                        .append(node.get("oldValue"))
//                        .append("\n");
//            } else if (node.containsValue("unchanged")) {
//                builder.append("    ")
//                        .append(node.get("Field"))
//                        .append(": ")
//                        .append(node.get("oldValue"))
//                        .append("\n");
//            } else if (node.containsValue("changed")) {
//                builder.append("  - ")
//                        .append(node.get("Field"))
//                        .append(": ")
//                        .append(node.get("oldValue"))
//                        .append("\n")
//                        .append("  + ")
//                        .append(node.get("Field"))
//                        .append(": ")
//                        .append(node.get("newValue"))
//                        .append("\n");
//            }
//        }
//        builder.append("}");
//        return builder.toString();
//    }
}
