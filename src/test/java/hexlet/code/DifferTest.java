package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void testSum() {
        final int expected = 4;
        int actual = Differ.sum(2, 2);

        assertEquals(expected, actual);
    }
}
