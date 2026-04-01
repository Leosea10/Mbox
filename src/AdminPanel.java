import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService(scanner);
    private final BikeService bikeService = new BikeService();
    private final RentalService rentalService = new RentalService(bikeService);
    private final BikeRentalConsole bikeRentalConsole = new BikeRentalConsole(scanner, bikeService, rentalService);

    public void userManagementOptions() {
        while (true) {
            System.out.println("Welcome to E-Ryder Administrator Panel. What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Demo the BikeRental System");
            System.out.println("6. EXIT");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                userService.addNewUsers();
            } else if (choice == 2) {
                userService.viewRegisteredUsers();
            } else if (choice == 3) {
                userService.removeRegisteredUsers();
            } else if (choice == 4) {
                userService.updateRegisteredUsers();
            } else if (choice == 5) {
                bikeRentalConsole.simulateApplication();
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }
}
