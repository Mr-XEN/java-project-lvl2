package hexlet.code;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;


//import java.io.IOException;
//import java.lang.reflect.Type;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String run(File file1, File file2) throws IOException {
        String file1Extension = getFileExtension(file1);
        String file2Extension = getFileExtension(file2);
        Map<String, Object> map1 = Parser.fileToMap(file1, file1Extension);
        Map<String, Object> map2 = Parser.fileToMap(file2, file2Extension);
        return generateReport(map1, map2);
    }

    public static String generateReport(Map<String, Object> map1, Map<String, Object> map2) {

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
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                x.append("\n").append("    ").append(key).append(": ").append(map1.get(key));
            } else {
                x.append("\n").append("  - ").append(key).append(": ").append(map1.get(key));
                x.append("\n").append("  + ").append(key).append(": ").append(map2.get(key));
            }
        }

        x.append("\n").append("}");

        return x.toString();
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }




}
