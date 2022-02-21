/**
 * this class get the path of the CSV file
 */
package Model.Raw;

final public class CSVPath {
    /**
     * handle the path that point to the resource root folder
     * @param fileName : name of the file in resource root folder
     * @return
     */
    public static String getFilePathFromResource(String fileName){
        return CSVDataHandling.class.getClassLoader().getResource(fileName).getPath();
    }
}
