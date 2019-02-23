public class Munro {

    private String name;
    private double heightInMetres;
    private String hillCategory;
    private String gridRef;

    public Munro(String name, double heightInMetres, String hillCategory, String gridReference) {
        this.name = name;
        this.heightInMetres = heightInMetres;
        this.hillCategory = hillCategory;
        this.gridRef = gridReference;
    }

    public String getName() {
        return name;
    }

    public double getHeightInMetres() {
        return heightInMetres;
    }

    public String getHillCategory() {
        return hillCategory;
    }

    public String getGridReference() {
        return gridRef;
    }
}
