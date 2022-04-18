package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




public class DifferTest {

    private final Map<String, String> testMap1 = new HashMap<>();
    private final Map<String, String> testMap2 = new HashMap<>();

    private final File file1 = new File("src/test/resources/testJson1.json");
    private final File file2 = new File("src/test/resources/testJson2.json");


    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public final void beforeEach() {
        testMap1.put("host", "hexlet.io");
        testMap1.put("timeout", "50");
        testMap1.put("proxy", "123.234.53.22");
        testMap1.put("follow", "false");

        testMap2.put("timeout", "20");
        testMap2.put("verbose", "true");
        testMap2.put("host", "hexlet.io");

    }

    @Test
    public void testAbsolutePath() {
        String path = "src/test/resources";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        assertTrue(absolutePath.endsWith("src/test/resources"));

    }

//    @Test
//    public void whenArgsIsWrong() {
//        byte[] wrong = {1, 2};
//        Exception exception = assertThrows(JsonParseException.class, () -> Parser2.parseJson(wrong));
//        String expectedMessage = "Illegal character";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

//    @Test
//    public void differTest1() {
//        String actual = Differ.generate(testMap1, testMap2);
//        String expected = "io";
//        System.out.println(actual);
//        assertTrue(actual.contains(expected));
//
//    }
//
    @Test
    public void differTest2() throws IOException {


        String actual = Differ.generate(file1, file2);
        String expected = "{\n"
                          + "  - follow: false\n"
                          + "    host: hexlet.io\n"
                          + "  - proxy: 123.234.53.22\n"
                          + "  - timeout: 50\n"
                          + "  + timeout: 20\n"
                          + "  + verbose: true\n"
                          + "}";
        assertEquals(actual, expected);
    }
//
//    @Test
//    public void differTest3() {
//        String actual = Differ.generate(testMap1, testMap2);
//        String expected = "{";
//        assertTrue(actual.startsWith(expected));
//    }



}


