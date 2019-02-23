import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MunroTest {

    Munro munro;

    @Before
    public void before() {
        munro = new Munro("Fake Munro", 2505.5, "MUN", "NN12345");
    }

    @Test
    public void munroHasName() {
        assertEquals("Fake Munro", munro.getName());
    }

    @Test
    public void munroHasHeight() {
        assertEquals(2505.5, munro.getHeightInMetres(), 0.1);
    }

    @Test
    public void munroHasHillCategory() {
        assertEquals("MUN", munro.getHillCategory());
    }

    @Test
    public void munroHasGridReference() {
        assertEquals("NN12345", munro.getGridReference());
    }


}
