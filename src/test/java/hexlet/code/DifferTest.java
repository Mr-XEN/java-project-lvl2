package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

public class DifferTest {

    private final String file1 = String.valueOf(new File("src/test/resources/testJson1.json"));
    private final String file2 = String.valueOf(new File("src/test/resources/testJson2.json"));
    private final String file3 = String.valueOf(new File("src/test/resources/testJson3.json"));
    private final String file4 = String.valueOf(new File("src/test/resources/testJson4.json"));
    private final String resultJson = String.valueOf(new File("src/test/resources/result.json"));


    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    public void absolutePathTest() {
        String path = "src/test/resources";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        assertTrue(absolutePath.endsWith("src/test/resources"));

    }


    @Test
    public void stylishFormatterTest() throws IOException {


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

    @Test
    public void jsonFormatterTest() throws IOException {


        String actual = Differ.generate(file1, file2, "json");
        String expected = "[{\"follow\":\"deleted\"},{\"host\":\"unchanged\"},{\"proxy\":\"deleted\"},"
                          + "{\"timeout\":\"changed\"},{\"verbose\":\"added\"}]";
        assertEquals(actual, expected);
    }

    @Test
    public void plainFormatterTest() throws IOException {


        String actual = Differ.generate(file3, file4, "plain");
        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                          + "Property 'checked' was updated. From false to true\n"
                          + "Property 'default' was updated. From null to [complex value]\n"
                          + "Property 'id' was updated. From 45 to null\n"
                          + "Property 'key1' was removed\n"
                          + "Property 'key2' was added with value: 'value2'\n"
                          + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                          + "Property 'numbers3' was removed\n"
                          + "Property 'numbers4' was added with value: [complex value]\n"
                          + "Property 'obj1' was added with value: [complex value]\n"
                          + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                          + "Property 'setting2' was updated. From 200 to 300\n"
                          + "Property 'setting3' was updated. From true to 'none'";

        assertEquals(actual, expected);
    }

}


