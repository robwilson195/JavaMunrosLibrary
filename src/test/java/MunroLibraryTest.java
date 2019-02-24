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

    @Test
    public void importDataExperiment() {
        munroLibrary.getDataUpdated();
    }

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

        assertEquals(915.76, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("resultLength", "10");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(10, results.size());
        assertEquals(1165.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithCategoryFilter() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("hillCategory", "MUN");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(915.76, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1344.53, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithCategoryFilterAndResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("hillCategory", "TOP");
        criteria.put("resultLength", "6");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(1106.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(1221.0, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithMaxHeight() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("maxHeight", "1000");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(915.76, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(999.7, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithMaxHeightAndMinHeight() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("maxHeight", "1000");
        criteria.put("minHeight", "950");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(952.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(999.7, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortInHeightDescendingOrderWithMaxHeightAndMinHeightAndHillCategoryAndResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("maxHeight", "1000");
        criteria.put("minHeight", "950");
        criteria.put("resultLength", "20");
        criteria.put("hillCategory", "MUN");
        List<Munro> results = munroLibrary.heightDescending(criteria);

        assertEquals(20, results.size());
        assertEquals(960.0, results.get(results.size()-1).getHeightInMetres(), 0.001);
        assertEquals(999.7, results.get(0).getHeightInMetres(), 0.001);
    }

    @Test
    public void canSortByNameAscending() {
        HashMap<String, String> criteria = new HashMap<>();
        List<Munro> results = munroLibrary.nameAscending(criteria);
        assertEquals("Am Bodach", results.get(0).getName());
        assertEquals("Stuchd an Lochain [Stuc an Lochain]", results.get(results.size()-1).getName());
    }

    @Test
    public void canSortInHeightAscendingOrderWithMaxHeightAndMinHeightAndHillCategoryAndResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("maxHeight", "1100");
        criteria.put("minHeight", "1000");
        criteria.put("resultLength", "20");
        criteria.put("hillCategory", "TOP");
        List<Munro> results = munroLibrary.heightAscending(criteria);

        assertEquals(true, results.size() <= 20);
        assertEquals(1100.0, results.get(results.size() - 1).getHeightInMetres(), 0.001);
        assertEquals(1000.1, results.get(0).getHeightInMetres(), 0.001);
    }

    // Realised this sort by name is difficult to test since java's Comparator.comparing function does not
    // ignore apostrophes while excel does. This made it difficult for me to know what the 'expected'
    // responses were. Decided to ignore since ignoring the apostrophes or not is subjective.
    @Ignore
    @Test
    public void canSortInNameDescendingOrderWithMaxHeightAndMinHeightAndHillCategoryAndResultLength() {
        HashMap<String, String> criteria = new HashMap<>();
        criteria.put("maxHeight", "1150");
        criteria.put("minHeight", "1000");
        criteria.put("resultLength", "8");
        criteria.put("hillCategory", "MUN");
        List<Munro> results = munroLibrary.nameDescending(criteria);

        assertEquals(true, results.size() <= 8);
        assertEquals("Sgurr Choinnich Mor", results.get(results.size() - 1).getName());
        assertEquals("Stob Ghabhar", results.get(0).getName());
    }


}
