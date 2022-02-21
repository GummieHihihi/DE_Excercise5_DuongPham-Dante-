/**
 * this class handling the raw data from CSV to a list of String[]
 */
package Model.Raw;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class CSVDataHandling {

//    turn the raw data into list of String[]

    /**
     * convert the data from CSV file into a list of String array
     * @param filePath
     * @return List<String[]>
     * @throws CsvValidationException
     * @throws IOException
     */
    public List<String[]> readDataCSV(String filePath) throws CsvValidationException, IOException {
        FileReader fileReader = new FileReader(filePath);
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .build();
        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(parser)
                .build();
        List<String[]> output = new ArrayList<>();
        String[] test = csvReader.readNext();
        while (test!=null){
            output.add(test);
            test = csvReader.readNext();
        }
        return output;
    }

    /**
     * write data to CSV format
     * @param data : a list of string
     * @param fileName : the file of the name that data will be written to
     * @throws IOException
     */
    public void writeToCSV(List<String[]> data, String fileName) throws IOException {
        String path = "src/main/resources/";
        File file = new File(path + fileName);
        FileWriter outputFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputFile);
        //add data to csv
        String[] header = {"Identification Code", "Model", "Brand", "Number of seat", "License Plate"};
        writer.writeNext(header);
        writer.writeAll(data);
        //close connection
        //
        writer.close();

    }
}
