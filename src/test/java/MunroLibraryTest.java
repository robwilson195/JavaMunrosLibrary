import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MunroLibraryTest {

//    Munro munro1;
//    Munro munro2;
//    Munro munro3;
//    Munro munro4;
    MunroLibrary munroLibrary;

    @Before
    public void setUp() throws Exception {
//        munro1 = new Munro("Munro 1", 2505.5, "MUN", "NN12345");
//        munro2 = new Munro("Auld Hill", 1589.7, "MUN", "NN62485");
//        munro3 = new Munro("Zebra Mountain", 3967.4, "TOP", "NN23567");
//        munro4 = new Munro("Donniegall Rise", 3967.1, "TOP", "NN56789");
        munroLibrary = new MunroLibrary("munrotab.csv");
    }

    @Test
    public void importDataExperiment() {
        munroLibrary.getData();
    }

    @Ignore
    @Test
    public void libraryHasCsvFileName() {
        assertEquals("munrotab.csv", munroLibrary.getCsvFileName());
    }

    @Ignore
    @Test
    public void libraryHasRelevantData() {
        assertEquals("Ben Chonzie", munroLibrary.getMunros().get(0).getName());
    }
}
