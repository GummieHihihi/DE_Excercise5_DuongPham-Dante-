package Operation;

import Model.Raw.CSVDataHandling;
import Model.Raw.CSVPath;
import Model.Transition.ApplicationData;
import Model.Transition.Car;
import Model.Transition.RentalDeal;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {
    /**
     * this method create new deal for user
     * @param deal : the deal that created
     * @param appData : application data
     */
    public static void addNewDeal(RentalDeal deal, ApplicationData appData){
        //check if this car has been booked or not
        List<Car> selectedCar = appData.getListOfCar().stream().filter(car -> car.getIndicationCode().equals(deal.getRentaledCarID()))
                .collect(Collectors.toList());
        if (deal.getEndDate().before(deal.getStartDate())){
            System.out.println("your start time need to start before your return time");
        }
        else if(selectedCar.size() > 0 && selectedCar.get(0).isAvailible()){
            appData.getListOfDeals().add(deal);
            System.out.println("create deal successfully");
            //turn the car status to unavailible
            appData.getListOfCar().stream()
                    .filter(car -> car.getIndicationCode().equals(selectedCar.get(0).getIndicationCode()))
                    .forEach(car -> car.setAvailible(false));
        }
        else{
            System.out.println("this car is not availible so we cannot complete the deal");
        }
    }

    /**
     * this method return the rented car and make it availible
     * @param customerChoice : 1 uf customer choose to input car ID and 2 if customer choose to input licese plate
     * @param carInformation : car ID or license plate
     * @param appData : application data
     */
    public static void returnCar(int customerChoice, String carInformation, ApplicationData appData){
        //check if this car is in any deal or not
        switch (customerChoice) {
            //to input the car indication number
            case 1:
//                check if the car status is availible or not
                if(appData.getListOfCar().stream().filter(car -> car.getIndicationCode().equals(carInformation))
                        .collect(Collectors.toList()).get(0).isAvailible()){
                    System.out.println("sorry this car is not recored in any contract");
                }
                else{
                    appData.getListOfCar().stream().filter(car -> car.getIndicationCode().equals(carInformation))
                            .forEach(car -> car.setAvailible(true));
                    // delete that Deal which the car have
                    List<RentalDeal> dealList = appData.getListOfDeals();
                    dealList.remove(dealList.stream()
                            .filter(rentalDeal -> rentalDeal.getRentaledCarID().equals(carInformation))
                            .collect(Collectors.toList()).get(0));
                }
                break;
            //input is car license plate
            case 2:
//                check if the car status is availible or not
                if(appData.getListOfCar().stream().filter(car -> car.getLicensePlate().equals(carInformation))
                        .collect(Collectors.toList()).get(0).isAvailible()){
                    System.out.println("sorry this car is not recored in any contract");
                }
                else{
                    appData.getListOfCar().stream().filter(car -> car.getLicensePlate().equals(carInformation))
                            .forEach(car -> car.setAvailible(true));
                    // delete that Deal which the car have
                    List<RentalDeal> dealList = appData.getListOfDeals();
                    dealList.remove(dealList.stream()
                            .filter(rentalDeal -> rentalDeal.getRentaledCarID().equals(carInformation))
                            .collect(Collectors.toList()).get(0));
                }
                break;
        }
    }

    /**
     * this method return and print all the car of the fleet
     * @param appData : application data
     */
    public static void getAllCar(ApplicationData appData){
        if(appData.getListOfCar().size() < 1){
            System.out.println("there is no data yet, you need to import the data in");
        }
        else{
            for (Car car: appData.getListOfCar()) {
                System.out.println(car.toString());
            }
        }
    }

    /**
     * this method return all the car that availible in a current selected time
     * @param selectedDate : the date we want to review
     * @param data : application data
     * @throws ParseException : in case the date is not parsed
     */
    public static void reviewAvailibleForRent(String selectedDate, ApplicationData data) throws ParseException {
        List<Car> returnList = new ArrayList<>();
        //first get all cars that status are availible
        data.getListOfCar().stream().filter(car -> car.isAvailible() == true)
                .forEach(car -> returnList.add(car));

        java.util.Date choosenDate = new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate);
//        adding all the car which are in the deal but the time range is not in the range of the requested time
        data.getListOfDeals().stream()
                .filter(deal -> !deal.getStartDate().before(choosenDate) || !deal.getEndDate().after(choosenDate))
                .forEach(rentalDeal -> data.getListOfCar()
                        .stream().filter(car -> car.getIndicationCode().equals(rentalDeal.getRentaledCarID()))
                        .forEach(car -> returnList.add(car)));
        for (Car car: returnList) {
            System.out.println(car);
        }
    }

    /**
     * this method add a new car to the list
     * @param newCar : new car been added
     * @param data : application data
     */
    public static void addNewCar(Car newCar, ApplicationData data){
        //check if this car has been in the list or not:
        if (data.getListOfCar().stream()
                .filter(car -> car.getLicensePlate().equals(newCar.getLicensePlate()))
                .collect(Collectors.toList()).size() >0){
            System.out.println("this car already in the list");
        }
        else{
            //add it
            data.getListOfCar().add(newCar);
        }
    }

    /**
     * this method cancel a car in the fleet
     * @param carID : the ID of the canceled car
     * @param data : application data
     */
    public static void cancelCar (String carID, ApplicationData data){
        //search if the car is now for rent or not or if the car is exist or not
        if(data.getListOfCar().stream().filter(car -> carID.equals(car.getIndicationCode()))
                .collect(Collectors.toList()).size() ==0
        ||  !data.getListOfCar().stream().filter(car -> carID.equals(car.getIndicationCode()))
                .collect(Collectors.toList()).get(0).isAvailible()){
            System.out.println("sorry this car is not exist or being rented, you cant cancel it");
        }
        //else we can delete the car from the list
        else{
            data.getListOfCar().remove(data.getListOfCar().stream()
                    .filter(car -> carID.equals(car.getIndicationCode()))
                    .collect(Collectors.toList()).get(0));
        }
    }

    /**
     * this method export the list of car into a CSV file
     * @param fileName : the name of the exported file
     * @param data : application data
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void exportCarListToCSV(String fileName, ApplicationData data) throws IOException, URISyntaxException {
        CSVDataHandling dataHandling = new CSVDataHandling();
        List<String[]> listOfData = new ArrayList<>();
        data.getListOfCar().stream().forEach(car -> listOfData.add(car.revertToString()));
        dataHandling.writeToCSV(listOfData, fileName);
        System.out.println("export done, please check your resource root folder for the output file : " + fileName + ".csv");
    }

    /**
     * this method import a CSV file about the list of car into a CSV file
     * @param fileName : file name that user import from
     * @param appData : application data
     * @throws CsvValidationException
     * @throws IOException
     */
    public static void importDataFromCSV(String fileName, ApplicationData appData) throws CsvValidationException, IOException {
        CSVDataHandling dataHandling = new CSVDataHandling();
        appData.createData(dataHandling.readDataCSV(CSVPath.getFilePathFromResource(fileName)));
        System.out.println("importing data.....");
        System.out.println("Done");
    }
}

