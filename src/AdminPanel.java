import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AdminPanel {
    private ArrayList<RegisteredUsers> registeredUsersList;
    private Scanner scanner;

    public AdminPanel() {
        registeredUsersList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== E-Ryder Administrator Panel =====");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Program exited.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("\nHow many users to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- User " + (i + 1) + " ---");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Card Expiry Date: ");
            String cardExpiryDate = scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] lastThreeTrips = new String[3];
            for (int t = 0; t < 3; t++) {
                System.out.println("\nTrip " + (t + 1));
                System.out.print("Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String destination = scanner.nextLine();

                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();

                System.out.print("Feedback (NULL allowed): ");
                String feedback = scanner.nextLine();

                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("Date: ").append(tripDate)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare (€): ").append(fare)
                        .append(", Feedback: ").append(feedback);

                lastThreeTrips[t] = tripInfo.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider,
                    cvv, userType, lastThreeTrips
            );
            registeredUsersList.add(user);
            System.out.println("User added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to display");
            return;
        }

        for (RegisteredUsers user : registeredUsersList) {
            System.out.println("\n" + user);
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to remove");
            return;
        }

        System.out.print("\nEnter email to remove: ");
        String email = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to update");
            return;
        }

        System.out.print("\nEnter email to update: ");
        String email = scanner.nextLine();
        RegisteredUsers targetUser = null;

        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(email)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\n--- Update User (Press ENTER to keep old value) ---");

        System.out.print("New full name: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            targetUser.setFullName(newName);
        }

        System.out.print("New email: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            targetUser.setEmailAddress(newEmail);
        }

        System.out.print("New date of birth: ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) {
            targetUser.setDateOfBirth(newDob);
        }

        System.out.print("New card number (0 = no change): ");
        String newCardNo = scanner.nextLine();
        if (!newCardNo.equals("0") && !newCardNo.isEmpty()) {
            targetUser.setCardNumber(newCardNo);
        }

        System.out.print("New card expiry date: ");
        String newExpiry = scanner.nextLine();
        if (!newExpiry.isEmpty()) {
            targetUser.setCardExpiryDate(newExpiry);
        }

        System.out.print("New card provider: ");
        String newProvider = scanner.nextLine();
        if (!newProvider.isEmpty()) {
            targetUser.setCardProvider(newProvider);
        }

        System.out.print("New CVV (0 = no change): ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0") && !newCvv.isEmpty()) {
            targetUser.setCvv(newCvv);
        }

        System.out.print("New user type: ");
        String newType = scanner.nextLine();
        if (!newType.isEmpty()) {
            targetUser.setUserType(newType);
        }

        System.out.println("User updated successfully!");
    }
}
