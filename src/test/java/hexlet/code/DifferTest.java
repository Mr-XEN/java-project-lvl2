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

    @Test
    public void testSum2() {
        final int expected = 9;
        final int x = 3;
        final int z = 3;
        int actual = Differ.sum(x, z);

        assertEquals(expected, actual);
    }
}
