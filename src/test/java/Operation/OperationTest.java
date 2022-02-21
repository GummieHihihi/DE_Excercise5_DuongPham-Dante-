package Operation;

import Model.Raw.CSVDataHandling;
import Model.Raw.CSVPath;
import Model.Transition.ApplicationData;
import Model.Transition.Car;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OperationTest {
    ApplicationData appdata;
    CSVDataHandling dataHandling;

    @Before
    public void setUp() throws Exception {
        appdata = new ApplicationData();
        dataHandling = new CSVDataHandling();
        List<String[]> rawData = dataHandling.readDataCSV(CSVPath.getFilePathFromResource("test.csv"));
        appdata.createData(rawData);
    }

    @Test
    public void cancelCar() {
        Operation.cancelCar("1", appdata);
        assertTrue(appdata.getListOfCar().size()<1);
        System.out.println("test done completely");
    }

    @Test
    public void getAllCar() {
        Car expectedCar = new Car("Mazda RX4","Mazda",4,"30T1-12345");
        assertTrue(compareCar(expectedCar, appdata.getListOfCar().get(0)));
        System.out.println("test complete");
    }

    @Test
    public void addNewCar() {
        Car newCar = new Car("Testing Car", "Dante testing", 2, "testing license plate");
        Operation.addNewCar(newCar, appdata);
        assertTrue(compareCar(newCar, appdata.getListOfCar().get(appdata.getListOfCar().size() - 1)));
        System.out.println("test done successfully");
    }

    private boolean compareCar(Car a, Car b){
        if(a.getModel().equals(b.getModel()) && a.getLicensePlate().equals(b.getLicensePlate())
                && a.getNumberOfSeat() == b.getNumberOfSeat()){
            return true;
        }
        return false;
    }
}