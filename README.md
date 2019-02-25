# Java Munros Library

### To run the library tests
* This library was built using IntelliJ and Gradle. As such IntelliJ can be used to open the project from your local directory once downloaded and unzipped.

### To use the library
* Download this repository and store it where you like on your local machine.
* In your project, import it from where you store this repository on your local machine and create a new instance of MunroLibrary, giving the argument ("munrotab.csv") as default. If you are replacing that csv file, use the name of the new file instead, but make sure you store the new one in your JavaMunroLibrary directory.
* To make standard requests, call methods .heightAscending(), .heightDescending(), .nameAscending(), .nameDescending() as appropriate. Each of these functions requires a HashMap<String, String> as an argument. This hashMap may be empty, or include any of the following fields:
```
{
  "hillCategory": "MUN" or "TOP",
  "minHeight": "Double",
  "maxHeight": "Double",
  "resultLength": "int"
}
```
* To make custom, layered requests, use the .customRequest() method. In addition to the above optional criteria fields, .customRequest() arguments MUST contain "sortCategory" and "sortDirection" and may optionally contain the "secondarySortDirection" field:
```
{
  "sortCategory": "height" or "name",
  "sortDirection": "ascending" or "descending",
  "secondarySortDirection": "ascending" or "descending"
}
```
### Notes
* The library behaves as intended for the most part, however... Due to the nature of ```Comparator.comparing``` function in the default Java library, the sorting by name functions don't behave entirely as expected: The Comparator function does not ignore apostrophes, as one might be inclined to do when sorting words manually, instead ranking any word which contains an apostrophe last. e.g. " Beinn " before " a' ". This does not alter the result of the list drastically, but is worth bearing in mind while using the library.
