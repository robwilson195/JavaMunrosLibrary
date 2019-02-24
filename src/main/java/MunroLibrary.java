import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class MunroLibrary {

    private ArrayList<Munro> munros;
    private String csvFileName;

    public MunroLibrary(String fileName) {

        this.csvFileName = fileName;
        this.munros = new ArrayList<>();
        this.munros = getData();


    }

    private ArrayList<Munro> getData(){


        List<List<String>> csvData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("./" + this.csvFileName))) {
            while (scanner.hasNextLine()) {
                csvData.add(this.getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //TODO: Work out why only approx 181 entries are being imported. Probably memory limitation of List.

//        The following was used while experimenting with the results from the csv file.
//        System.out.println(csvData.get(1).get(0));
//        System.out.println(csvData.get(1).get(8));
//        System.out.println(csvData.get(1));
//        System.out.println(csvData.get(8));
//        System.out.println("category "+ csvData.get(8).get(18));
//        System.out.println("no category " + csvData.get(8).get(19));
//        System.out.println("post 1997 " + csvData.get(8).get(27));
//        System.out.println(csvData.get(181));

        return this.generateMunros(csvData);

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    private ArrayList<Munro> generateMunros(List<List<String>> csvData) {

        // This first section should circumvent issues with new csv files having different column order,
        // provided the naming is the same. It also removes the first entry.
        // NOTE: Need to add 1 for now, due to presence of comma in some fields causing extra columns.
        // TODO: Compensate for commas in field entries.

        List<String> columnHeads = csvData.get(0);
        int nameIndex = columnHeads.indexOf("Name") + 1;
        int heightIndex = columnHeads.indexOf("Height (m)") + 1;
        int categoryIndex = columnHeads.indexOf("Post 1997") + 1;
        int gridIndex = columnHeads.indexOf("Grid Ref") + 1;
        csvData.remove(0);

        ArrayList<Munro> munrosData = new ArrayList<>();

        // Limit to 180 is a temporary limitation until memory limitation is circumvented.
        for (int i=0; i<180; i++) {

            List<String> munroData = csvData.get(i);

            double munroHeight = parseDouble(munroData.get(heightIndex));
            String munroCategory = munroData.get(categoryIndex);
            String munroName = munroData.get(nameIndex);
            String munroGrid = munroData.get(gridIndex);

            Munro munro = new Munro(munroName, munroHeight, munroCategory, munroGrid);
            munrosData.add(munro);
        }

        return munrosData;

    }

    public ArrayList<Munro> getMunros() {
        return munros;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public List<Munro> heightDescending(HashMap<String, String> criteria) {


        List<Munro> results = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).reversed())
                .collect(Collectors.toList());

        return results;
    }

}

