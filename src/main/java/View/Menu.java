package View;

public class Menu {
    /**
     * print out the main menu of the program
     */
    public static void printMainMenu(){
        System.out.println("WELCOME TO OUR CAR FLEET UNION");
        System.out.println("1. Add new rental contract information");
        System.out.println("2. Return of the vehicle to the car rental");
        System.out.println("3. Display the list of all vehicles belonging to the car rental fleet");
        System.out.println("4. view the list of all vehicles availible for rental in specified period of time");
        System.out.println("5. Adding a new vehicle to the car rental fleet");
        System.out.println("6. Cancellation of a vehicle from the fleet");
        System.out.println("7. Importing vehicles from a CSV file");
        System.out.println("8. Export of all car rental vehicles in a CSV file");
        System.out.println("9. Exit the program");
    }

    /**
     * print out the second section of the section 2 of the program
     */
    public static void printSubMenu2() {
        System.out.println("1. enter the indication number");
        System.out.println("2. enter the license plate");
    }
}
