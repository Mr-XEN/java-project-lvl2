package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatters(List<Map<String, Object>> list, String formatter)
            throws JsonProcessingException {
        return switch (formatter) {
            case "plain" -> Plain.plain(list);
            case "json" -> Json.json(list);
            default -> Stylish.stylish(list);
        };
    }

    public static String formatters(List<Map<String, Object>> list) {
        return Stylish.stylish(list);
    }
}
