import java.util.ArrayList;

public class MunroLibrary {

    ArrayList<Munro> munros;
    String csvFileName;

    public MunroLibrary(String fileName) {

        this.csvFileName = fileName;
        this.munros = new ArrayList<>();
        this.munros = getData();


    }

    public ArrayList<Munro> getData(){



        ArrayList<Munro> munroData;
        return munroData;
    }

    public ArrayList<Munro> getMunros() {
        return munros;
    }


}
