package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {

    private static ObjectMapper objectMapperJson = new ObjectMapper(new JsonFactory());

    private static ObjectMapper objectMapperYaml = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> fileToMap(String context, String format) throws Exception {

        return switch (format) {
            case "json" -> objectMapperJson.readValue(context, new TypeReference<>() {
            });
            case "yml", "yaml" -> objectMapperYaml.readValue(context, new TypeReference<>() {
            });
            default -> throw new Exception();
        };

    }

}
