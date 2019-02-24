import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public void canSortInHeightDescendingOrderNoCriteria() {
        HashMap<String, String> criteria = new HashMap<>();
        List<Munro> results = munroLibrary.heightDescending(criteria);
        assertEquals(901.7, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("resultLength", "10");
        List<Munro> results = munroLibrary.heightDescending(criteria);
        assertEquals(10, results.size());
        assertEquals(1174.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithCategoryFilter() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("resultLength", "10");
        List<Munro> results = munroLibrary.heightDescending(criteria);
        assertEquals(10, results.size());
        assertEquals(1174.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }


}
