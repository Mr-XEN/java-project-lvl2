package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    private static ObjectMapper objectMapper = new ObjectMapper();
    public static  String file1 = "src/main/resources/file1.json";
    public static  String file2 = "src/main/resources/file2.json";

    public static String generate() {
        return "Kek";
    }

    public static Map<String, String> parse(String file) throws IOException {

        return objectMapper.readValue(readJson(file), new TypeReference<>() {
        });
    }

    public static String readJson(String s) throws IOException {
        return readFile(s);
    }

    public static String readFile (String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }


}
