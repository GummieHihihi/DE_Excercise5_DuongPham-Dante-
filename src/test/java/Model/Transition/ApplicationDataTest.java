package Model.Transition;

import Model.Raw.CSVDataHandling;
import Model.Raw.CSVPath;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationDataTest {

    ApplicationData appdata;
    CSVDataHandling dataHandling;
    @org.junit.Before
    public void setUp() throws Exception {
        appdata = new ApplicationData();
        dataHandling = new CSVDataHandling();
    }

    @org.junit.Test
    public void createData() throws CsvValidationException, IOException {
        List<String[]> rawData = dataHandling.readDataCSV(CSVPath.getFilePathFromResource("test.csv"));
        appdata.createData(rawData);
        Car expectedCar = new Car("Mazda RX4","Mazda",4,"30T1-12345");
        assertTrue(compareCar(expectedCar, appdata.getListOfCar().get(0)));
        System.out.println("test successfully");
    }

    private boolean compareCar(Car a, Car b){
        if(a.getModel().equals(b.getModel()) && a.getLicensePlate().equals(b.getLicensePlate())
                && a.getNumberOfSeat() == b.getNumberOfSeat()){
            return true;
        }
        return false;
    }
}