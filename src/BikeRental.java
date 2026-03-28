import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;

    private final UserRegistration userRegistration = new UserRegistration();
    private ActiveRental activeRental;
    private final LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.print("Are you a registered user? (true/false): ");
        if (!scanner.hasNextBoolean()) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter true or false.");
            return;
        }
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter your email address: ");
        emailAddress = scanner.nextLine();

        System.out.print("Enter your location: ");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);

        if (!locationValid) {
            return;
        }

        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals...");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip...");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end...");
        viewActiveRentals();
    }

    public void simulateApplication() {
        simulateApplicationInput();
    }

    private String analyseRequest(boolean isRegisteredUser, String emailAddress, String location) {
        if (isRegisteredUser) {
            System.out.println("Welcome back, " + emailAddress + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            userRegistration.registration();
        }

        return validate(location);
    }

    private String validate(String location) {
        return validateLocation(location);
    }

    private String validateLocation(String location) {
        locationValid = false;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equalsIgnoreCase(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setIsAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the " + bikeID
                            + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                    activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }

        for (ActiveRental rental : activeRentalsList) {
            System.out.println(rental);
        }
    }

    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equalsIgnoreCase(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equalsIgnoreCase(bikeID)) {
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
}
