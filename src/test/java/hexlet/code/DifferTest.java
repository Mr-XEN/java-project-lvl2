package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private final String file1 = getFile("testJson1.json");
    private final String file2 = getFile("testJson2.json");
    private final String file3 = getFile("testJson3.json");
    private final String file4 = getFile("testJson4.json");


    private String getFile(String fileName) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile();
    }

    private static String readFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    public void stylishFormatterTestWithoutArgument() throws Exception {

        String actual = Differ.generate(file1, file2);
        String expected = readFiles("src/test/resources/Expected/stylish.txt");
        assertEquals(actual, expected);
    }

    @Test
    public void stylishFormatterTestWithArgument() throws Exception {

        String actual = Differ.generate(file1, file2, "stylish");
        String expected = readFiles("src/test/resources/Expected/stylish.txt");
        assertEquals(actual, expected);
    }

    @Test
    public void jsonFormatterTest() throws Exception {

        String actual = Differ.generate(file1, file2, "json");
        String expected = readFiles("src/test/resources/Expected/json.txt");
        assertEquals(actual, expected);
    }

    @Test
    public void plainFormatterTest() throws Exception {

        String actual = Differ.generate(file3, file4, "plain");
        String expected = readFiles("src/test/resources/Expected/plain.txt");
        assertEquals(actual, expected);
    }



}


