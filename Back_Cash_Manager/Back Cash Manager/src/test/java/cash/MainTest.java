package cash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testExampleAdd() {
        Main main = new Main();
        int result = main.exampleAdd(2, 3);
        assertEquals(5, result);
    }
}
