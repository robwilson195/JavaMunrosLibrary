import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MunroLibrary {

    ArrayList<Munro> munros;
    String csvFileName;

    public MunroLibrary(String fileName) {

        this.csvFileName = fileName;
        this.munros = new ArrayList<>();
//        this.munros = getData();


    }

    public void getData(){


        String pathName = this.csvFileName;
        List<List<String>> csvData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("./" + this.csvFileName));) {
            while (scanner.hasNextLine()) {
                csvData.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        The following was used while experimenting with the results from the csv file.
//        System.out.println(csvData.get(1).get(0));
//        System.out.println(csvData.get(1).get(8));
//        System.out.println(csvData.get(1));
//        System.out.println(csvData.get(8));
//        System.out.println("category "+ csvData.get(8).get(18));
//        System.out.println("no category " + csvData.get(8).get(19));
//        System.out.println("post 1997 " + csvData.get(8).get(28));

        

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
        }

    public ArrayList<Munro> getMunros() {
        return munros;
    }

    public String getCsvFileName() {
        return csvFileName;
    }
}
