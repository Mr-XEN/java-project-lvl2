package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String generate() {
        return "Kek";
    }

    public static Map<String, String> parse(String src) throws IOException {
        ObjectMapper x = new ObjectMapper();
        Map<String,String> result = objectMapper.readValue(readJson(), new TypeReference<Map<String,String>>(){});
        return result;
    }

    public static String readJson() throws IOException {
        String file = "src/main/resources/file1.json";
        String json = readFile(file);
        return json;
    }

    public static String readFile (String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }


}
