package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String formatters(Map<String, Object> map1, Map<String, Object> map2, String formatter) {
        return switch (formatter) {
            case "plain" -> Plain.plain(map1, map2);
            default -> Stylish.stylish(map1, map2);
        };
    }

    public static String formatters(Map<String, Object> map1, Map<String, Object> map2) {
        return Stylish.stylish(map1, map2);
    }


}
