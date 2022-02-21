package Model.Transition;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String indicationCode;
    private String brand;
    private String model;
    private int numberOfSeat;
    private String licensePlate;
    private boolean isAvailible;

    public Car(String brand, String model, int numberOfSeat, String licensePlate){
        this.brand = brand;
        this.model = model;
        this.numberOfSeat = numberOfSeat;
        this.licensePlate = licensePlate;
        this.isAvailible = true;
    }

    public void setIndicationCode(String indicationCode){
        this.indicationCode = indicationCode;
    }

    public void setAvailible(boolean availible) {
        isAvailible = availible;
    }

    public boolean isAvailible() {
        return isAvailible;
    }

    public String getIndicationCode() {
        return indicationCode;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getNumberOfSeat(){
        return this.numberOfSeat;
    }

    /**
     * revert this object into String[] so that we can write it to CSV format later on
     * @return
     */
    public String[] revertToString(){
        String[] returnCar = new String[]{this.indicationCode, this.brand, this.getModel(), String.valueOf(this.numberOfSeat), this.getLicensePlate()};
        return  returnCar;
    }

    @Override
    public String toString() {
        return "Car :" +
                "indicationCode='" + indicationCode + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", numberOfSeat=" + numberOfSeat +
                ", licensePlate='" + licensePlate + '\'' +
                ", isAvailible=" + isAvailible;
    }
}
