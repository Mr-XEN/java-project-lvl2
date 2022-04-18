package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class Json {

    private static ObjectMapper objectMapperJson = new ObjectMapper(new JsonFactory());

    public static String json(Map<String, Object> map1, Map<String, Object> map2) throws JsonProcessingException {

        List<Map<String, Object>> superMap = Differ.generateDiff(map1, map2);
        return objectMapperJson.writeValueAsString(superMap);

    }
}
