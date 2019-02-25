# Java Munros Library

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
