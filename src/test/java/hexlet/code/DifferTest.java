package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DifferTest {

    private final Map<String, String> testMap1 = new HashMap<>();
    private final Map<String, String> testMap2 = new HashMap<>();


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

        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("src/test/resources"));

    }

    @Test
    public void whenArgsIsWrong() {
        byte[] wrong = {1, 2};
        Exception exception = assertThrows(JsonParseException.class, () -> Differ.parse(wrong));
        String expectedMessage = "Illegal character";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void differTest1() {
        String actual = Differ.generate(testMap1, testMap2);
        String expected = "io";
        System.out.println(actual);
        assertTrue(actual.contains(expected));

    }

    @Test
    public void differTest2() {
        String actual = Differ.generate(testMap1, testMap2);
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

    @Test
    public void differTest3() {
        String actual = Differ.generate(testMap1, testMap2);
        String expected = "{";
        assertTrue(actual.startsWith(expected));
    }



}


