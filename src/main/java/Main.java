import Model.Raw.CSVDataHandling;
import Model.Raw.CSVPath;
import Model.Transition.ApplicationData;
import Model.Transition.Car;
import Model.Transition.RentalDeal;
import Operation.Operation;
import View.Menu;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CsvValidationException, IOException, ParseException, URISyntaxException {
        //adding the data before the application run
        CSVDataHandling data = new CSVDataHandling();
        List<String[]> rawData = data.readDataCSV(CSVPath.getFilePathFromResource("cars.csv"));
        System.out.println("starting ....");
        System.out.println("initiate the default data .... complete");
        ApplicationData appData = new ApplicationData();
        appData.createData(rawData);
        RentalDeal deal = new RentalDeal("1", "duong", "12/1/2021", "15/1/2022");
        Operation.addNewDeal(deal, appData);

        //start of the program
        Scanner sc = new Scanner(System.in);
        String choice = "0";
        while (Integer.parseInt(choice) != 9) {
            Menu.printMainMenu();
            System.out.println("choose the section you want to go :");
            choice = sc.nextLine();
            switch (Integer.parseInt(choice)) {
                case 1:
                    System.out.println("what is the customer name ?");
                    String customerName = sc.nextLine();
                    System.out.println("Please enter the indication code of the car:");
                    String carIndication = sc.nextLine();
                    System.out.println("in what day you want to rent it ? (format : dd/mm/yy)");
                    String startTime = sc.nextLine();
                    System.out.println("which day you want to return the car ?(format : dd/mm/yy)");
                    String returnTime = sc.nextLine();
                    RentalDeal newDeal = new RentalDeal(carIndication, customerName, startTime, returnTime);
                    Operation.addNewDeal(newDeal, appData);
                    break;
                case 2:
                    System.out.println("please choose your option:");
                    Menu.printSubMenu2();
                    String subchoiceSection2 = sc.nextLine();
                    System.out.println("please enter your indication code of the car/license plate of the car:");
                    String carInfo = sc.nextLine();
                    Operation.returnCar(Integer.parseInt(subchoiceSection2), carInfo, appData);
                    break;
                case 3:
                    Operation.getAllCar(appData);
                    break;
                case 4:
                    System.out.println("select the date you want to review : (format : dd/mm/yy)");
                    String expectedDate = sc.nextLine();
                    Operation.reviewAvailibleForRent(expectedDate, appData);
                    break;
                case 5:
                    System.out.println("please enter the car brand");
                    String brand = sc.nextLine();
                    System.out.println("please enter the car model");
                    String model = sc.nextLine();
                    System.out.println("please enter the number of seat");
                    String numOfSeat = sc.nextLine();
                    System.out.println("please enter the car license plate");
                    String licensePlate = sc.nextLine();
                    Car newCar = new Car(brand, model, Integer.parseInt(numOfSeat), licensePlate);
                    Operation.addNewCar(newCar, appData);
                    break;
                case 6:
                    System.out.println("please input the car indication number you wish to cancel from the fleet");
                    String indicationCode = sc.nextLine();
                    Operation.cancelCar(indicationCode, appData);
                    break;
                case 7:
                    System.out.println("please type down the name of your CSV file, the file must be in the resource directory of the project");
                    System.out.println("for eg : 'something.csv'");
                    String resourceFile = sc.nextLine();
                    Operation.importDataFromCSV(resourceFile, appData);
                    break;
                case 8:
                    System.out.println("Please let us know the name of the output file you want/you need to add .csv to the end of the file");
                    String fileName = sc.nextLine();
                    Operation.exportCarListToCSV(fileName, appData);
                    break;
            }
        }
    }
}
