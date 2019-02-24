import java.io.*;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MunroLibrary {

    private ArrayList<Munro> munros;
    private String csvFileName;

    public MunroLibrary(String fileName) {

        this.csvFileName = fileName;
        this.munros = new ArrayList<>();
        this.munros = getDataUpdated();


    }

    public ArrayList<Munro> getDataUpdated() {
        List<List<String>> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./" + this.csvFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1); // limit compensates for empty fields.
                csvData.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.generateMunros(csvData);

    }

    private ArrayList<Munro> generateMunros(List<List<String>> csvData) {

        // NOTE: Need to add 1 for now, due to presence of comma in some fields causing extra columns.
        // TODO: Compensate for commas in field entries.

        List<String> columnHeads = csvData.get(0);
        int nameIndex = columnHeads.indexOf("Name") + 1;
        int heightIndex = columnHeads.indexOf("Height (m)") + 1;
        int categoryIndex = columnHeads.indexOf("Post 1997") + 1;
        int gridIndex = columnHeads.indexOf("Grid Ref") + 1;
        csvData.remove(0);

        ArrayList<Munro> munrosData = new ArrayList<>();

        for (int i=0; i<csvData.size(); i++) {

            List<String> munroData = csvData.get(i);

            // Filter out csvData that is not a hill entry at all
            if (!munroData.get(0).equals("")) {
                double munroHeight = parseDouble(munroData.get(heightIndex));
                String munroCategory = munroData.get(categoryIndex);
                String munroName = munroData.get(nameIndex);
                String munroGrid = munroData.get(gridIndex);
                // Filter out fields with no hillCategory
                if (!munroCategory.equals("")) {
                    Munro munro = new Munro(munroName, munroHeight, munroCategory, munroGrid);
                    munrosData.add(munro);
                }
            }
        }

        return munrosData;

    }

    public ArrayList<Munro> getMunros() {
        return munros;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public List<Munro> customRequest(HashMap<String, String> criteria) {
        List<Munro> results;

        String primarySort = criteria.get("sortCategory");
        String primarySortDirection = criteria.get("sortDirection");

        if (!criteria.containsKey("secondarySortDirection")) {
            if (primarySort.equals("height") && primarySortDirection.equals("descending")) {
                results = heightDescending(criteria);
                return results;
            } else if (primarySort.equals("height") && primarySortDirection.equals("ascending")) {
                results = heightAscending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("ascending")) {
                results = nameAscending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("descending")) {
                results = nameDescending(criteria);
                return results;
            } else {
                results = new ArrayList<>();
                return results;
            }
        } else if (criteria.get("secondarySortDirection").equals("ascending")){
            if (primarySort.equals("height") && primarySortDirection.equals("descending")) {
                results = heightDescendingNameAscending(criteria);
                return results;
            } else if (primarySort.equals("height") && primarySortDirection.equals("ascending")) {
                results = heightAscendingNameAscending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("ascending")) {
                results = nameAscendingHeightAscending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("descending")) {
                results = nameDescendingHeightAscending(criteria);
                return results;
            } else {
                results = new ArrayList<>();
                return results;
            }
        } else if (criteria.get("secondarySortDirection").equals("descending")){
            if (primarySort.equals("height") && primarySortDirection.equals("descending")) {
                results = heightDescendingNameDescending(criteria);
                return results;
            } else if (primarySort.equals("height") && primarySortDirection.equals("ascending")) {
                results = heightAscendingNameDescending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("ascending")) {
                results = nameAscendingHeightDescending(criteria);
                return results;
            } else if (primarySort.equals("name") && primarySortDirection.equals("descending")) {
                results = nameDescendingHeightDescending(criteria);
                return results;
            } else {
                results = new ArrayList<>();
                return results;
            }
        } else {
            results = new ArrayList<>();
            return results;
        }
    }

    private List<Munro> heightDescendingNameAscending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).reversed().thenComparing(Munro::getName));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> heightAscendingNameAscending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).thenComparing(Munro::getName));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> nameAscendingHeightAscending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName).thenComparing(Munro::getHeightInMetres));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> nameDescendingHeightAscending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName).reversed().thenComparing(Munro::getHeightInMetres));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> heightDescendingNameDescending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).reversed().thenComparing(Munro::getName).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> heightAscendingNameDescending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).thenComparing(Munro::getName).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> nameAscendingHeightDescending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName).thenComparing(Munro::getHeightInMetres).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private List<Munro> nameDescendingHeightDescending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName).reversed().thenComparing(Munro::getHeightInMetres).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    // The below functions become unnecessary with the addition of customRequest and its supporting
    // functions, however users might still prefer them if a layered request is not required.
    public List<Munro> heightAscending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    public List<Munro> heightDescending(HashMap<String, String> criteria) {
        List<Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getHeightInMetres).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    // See note in test file RE 'Comparator.comparing' treatment of apostrophes.
    public List<Munro> nameAscending(HashMap<String, String> criteria) {
        List <Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName));

        results = standardQuery(criteria, munroStream);

        return results;
    }

    public List<Munro> nameDescending(HashMap<String, String> criteria) {
        List <Munro> results;

        this.validateCriteria(criteria);

        Stream<Munro> munroStream = this.munros.stream()
                .sorted(Comparator.comparing(Munro::getName).reversed());

        results = standardQuery(criteria, munroStream);

        return results;
    }

    private Boolean validateCriteria(HashMap<String, String> criteria) {
        boolean criteriaValidity = true;
        if (criteria.containsKey("hillCategory")) {
            String cat = criteria.get("hillCategory");
            if (!(cat.equals("MUN") || cat.equals("TOP"))) {
                System.out.println("Criteria Error: Field hillCategory must contain string value MUN or TOP.");
                criteriaValidity = false;
            }
        }
        if (criteria.containsKey("maxHeight") && criteria.containsKey("minHeight")) {
            double min = parseDouble(criteria.get("minHeight"));
            double max = parseDouble(criteria.get("maxHeight"));
            if (min > max) {
                System.out.println("Criteria Error: Field minHeight must not be greater than field maxHeight.");
                criteriaValidity = false;
            }
        }
        return criteriaValidity;
    }



    // Receives open stream from specific sort type.
    private List<Munro> standardQuery(HashMap<String, String> criteria, Stream<Munro> munroStream) {

        List<Munro> results;

        // Filters by hill category
        if (criteria.containsKey("hillCategory")) {
            munroStream = munroStream
                    .filter(munro -> munro.getHillCategory().equals(criteria.get("hillCategory")));
        }

        // Filters by max height
        if (criteria.containsKey("maxHeight")) {
            double maxHeight = parseDouble(criteria.get("maxHeight"));
            munroStream = munroStream
                    .filter(munro -> munro.getHeightInMetres() <= maxHeight);
        }

        // Filters by minHeight
        if (criteria.containsKey("minHeight")) {
            double minHeight = parseDouble(criteria.get("minHeight"));
            munroStream = munroStream
                    .filter(munro -> munro.getHeightInMetres() >= minHeight);
        }

        // Turning stream back in to results list.
        results = munroStream.collect(Collectors.toList());

        // Limiting return length according to criteria.
        if (criteria.containsKey("resultLength") && results.size() > parseInt(criteria.get("resultLength"))) {
            results = results.subList(0, parseInt(criteria.get("resultLength")));
        }
        return results;
    }

}

