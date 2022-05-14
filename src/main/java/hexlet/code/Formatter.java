package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diff, String formatter)
            throws JsonProcessingException {
        return switch (formatter) {
            case "plain" -> Plain.plain(diff);
            case "json" -> Json.json(diff);
            case "stylish" -> Stylish.stylish(diff);
            default -> throw new RuntimeException("Format option not supported");
        };
    }

}
