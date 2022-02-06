package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static String generate(Map<String, String> map1, Map<String, String> map2) {

        Set<String> mergeMap = new TreeSet<>();
        mergeMap.addAll(map1.keySet());
        mergeMap.addAll(map2.keySet());

        StringBuilder x = new StringBuilder();
        x.append("{");

        for (String key : mergeMap) {

            if (!map1.containsKey(key)) {
                x.append("\n").append("  + ").append(key).append(": ").append(map2.get(key));
            } else if (!map2.containsKey(key)) {
                x.append("\n").append("  - ").append(key).append(": ").append(map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) {
                x.append("\n").append("    ").append(key).append(": ").append(map1.get(key));
            } else {
                x.append("\n").append("  - ").append(key).append(": ").append(map1.get(key));
                x.append("\n").append("  + ").append(key).append(": ").append(map2.get(key));
            }
        }

        x.append("\n").append("}");

        return x.toString();
    }


    public static Map<String, String> parse(byte[] file) throws IOException {

        return objectMapper.readValue(file, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

    }

    public static String readFile(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String x(String s) throws IOException {
        return objectMapper.readValue(new File(s), String.class);

    }

    public static int sum(int a, int b) {
        return a * b;
    }

}
