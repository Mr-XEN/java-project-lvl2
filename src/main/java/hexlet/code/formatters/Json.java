package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class Json {

    private static ObjectMapper objectMapperJson = new ObjectMapper(new JsonFactory());

    public static String json(List<Map<String, Object>> diff) throws JsonProcessingException {
        return objectMapperJson.writeValueAsString(diff);
    }
}
