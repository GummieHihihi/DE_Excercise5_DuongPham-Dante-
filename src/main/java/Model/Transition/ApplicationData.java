/**
 * this class is the final data of the application
 */
package Model.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationData {
    private List<Car> listOfCar;
    private List<RentalDeal> deals;

    public ApplicationData(){
        listOfCar = new ArrayList<>();
        this.deals = new ArrayList<>();
    }

    /**
     * create the final data from raw data which is a list of String[]
     * @param rawData : List of String[] as raw data
     */
    public void createData(List<String[]> rawData){
        boolean firstLine = true;
        for (String[] line: rawData) {
            if(!firstLine){
                //start execute from the second line
                Car car = new Car(line[1],line[2],Integer.parseInt(line[3].trim()), line[4]);
                car.setIndicationCode(String.valueOf(this.getListOfCar().size() + 1));
                //check if the car has been duplicated or not
                if(this.getListOfCar().stream()
                        .filter(car1 -> car1.getLicensePlate().equals(line[4]))
                        .collect(Collectors.toList()).size() != 0){
                    //do nothing
                }
                //this car has not been created yet
                else{
                    this.listOfCar.add(car);
                }
            }
            while(firstLine){
                //to skip the first line
                firstLine = false;
            }
        }
    }

    /**
     * return the list of all car
     * @return list<car>
     */
    public List<Car> getListOfCar() {
        return listOfCar;
    }

    /**
     * return list of all rental deals
     * @return List<RentalDeal>
     */
    public List<RentalDeal> getListOfDeals() {
        return deals;
    }
}
