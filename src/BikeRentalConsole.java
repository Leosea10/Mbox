import java.util.Scanner;

public class BikeRentalConsole {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private String bikeID;

    private final UserRegistration userRegistration = new UserRegistration();
    private final BikeService bikeService;
    private final RentalService rentalService;
    private final Scanner scanner;

    public BikeRentalConsole(Scanner scanner, BikeService bikeService, RentalService rentalService) {
        this.scanner = scanner;
        this.bikeService = bikeService;
        this.rentalService = rentalService;
    }

    public void simulateApplication() {
        simulateApplicationInput();
    }

    private void simulateApplicationInput() {
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

        if (bikeID == null) {
            return;
        }

        System.out.println("Simulating e-bike reservation...");
        rentalService.startRental(bikeID, emailAddress);

        System.out.println("Displaying the active rentals...");
        rentalService.viewActiveRentals();

        System.out.println("Simulating the end of the trip...");
        rentalService.endRental(bikeID);

        System.out.println("Displaying the active rentals after trip end...");
        rentalService.viewActiveRentals();
    }

    private String analyseRequest(boolean isRegisteredUser, String emailAddress, String location) {
        if (isRegisteredUser) {
            System.out.println("Welcome back, " + emailAddress + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            userRegistration.registration();
        }

        return bikeService.validateLocation(location, emailAddress);
    }
}
