import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MunroLibraryTest {

    MunroLibrary munroLibrary;

    @Before
    public void setUp() throws Exception {
        munroLibrary = new MunroLibrary("munrotab.csv");
    }

//    @Test
//    public void importDataExperiment() {
//        munroLibrary.getData();
//    }

    @Test
    public void libraryHasCsvFileName() {
        assertEquals("munrotab.csv", munroLibrary.getCsvFileName());
    }

    @Test
    public void libraryHasRelevantData() {
        assertEquals("Ben Chonzie", munroLibrary.getMunros().get(0).getName());
    }

    @Test
    public void canSortInHeightDescendingOrderEmptyCriteria() {
        HashMap<String, String> criteria = new HashMap<>();
        ArrayList<Munro> results = munroLibrary.heightDescending(criteria);
    }
}
