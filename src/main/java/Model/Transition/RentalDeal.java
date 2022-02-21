package Model.Transition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalDeal {
    //this is the indication code of the car
    String rentaledCarID;
    String customerName;
    Date startDate;
    Date endDate;

    public RentalDeal(String rentaledCarID, String customerName, String startDate, String endDate) throws ParseException {
        this.rentaledCarID = rentaledCarID;
        this.customerName = customerName;
        this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
    }

    public String getRentaledCarID() {
        return rentaledCarID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
