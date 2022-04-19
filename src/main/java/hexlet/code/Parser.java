package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {

    private static ObjectMapper objectMapperJson = new ObjectMapper(new JsonFactory());

    private static ObjectMapper objectMapperYaml = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> fileToMap(String file2, String type) throws IOException {

        File file = new File(file2);

        if (type.equalsIgnoreCase("yaml") || type.equalsIgnoreCase("yml")) {
            return objectMapperYaml.readValue(file, new TypeReference<>() {
            });
        } else {
            return objectMapperJson.readValue(file, new TypeReference<>() {
            });
        }
    }

}
