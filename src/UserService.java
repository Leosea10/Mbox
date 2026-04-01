import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private final List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addNewUsers() {
        System.out.print("How many users would you like to add? ");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Invalid number. Returning to menu.");
            return;
        }
        int userCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            System.out.println("Entering details for user " + (i + 1) + ":");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Card Number: ");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExpiryDate = scanner.nextLine();

            System.out.print("CVV: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] lastThreeTrips = new String[3];
            for (int t = 0; t < lastThreeTrips.length; t++) {
                System.out.println("Trip " + (t + 1) + " details:");

                System.out.print("Date of trip (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String destination = scanner.nextLine();

                System.out.print("Fare paid ($): ");
                double fare = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Feedback (can be NULL): ");
                String feedback = scanner.nextLine();
                if (feedback == null || feedback.trim().isEmpty()) {
                    feedback = "NULL";
                }

                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("Date: ").append(tripDate)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare($): ").append(fare)
                        .append(", Feedback: ").append(feedback);

                lastThreeTrips[t] = tripInfo.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName,
                    emailAddress,
                    dateOfBirth,
                    cardNumber,
                    cardExpiryDate,
                    cardProvider,
                    cvv,
                    userType,
                    lastThreeTrips
            );

            registeredUsersList.add(user);
        }
    }

    public void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
            System.out.println();
        }
    }

    public void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter the email address of the user to remove: ");
        String emailToRemove = scanner.nextLine();

        boolean removed = false;
        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(emailToRemove)) {
                iterator.remove();
                removed = true;
            }
        }

        if (!removed) {
            System.out.println("No user found with this email address");
        }
    }

    public void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter the email address of the user to update: ");
        String emailToUpdate = scanner.nextLine();

        RegisteredUsers targetUser = null;
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(emailToUpdate)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.print("Type new full name: (Press ENTER for no change) ");
        String fullName = scanner.nextLine();
        if (!fullName.isEmpty()) {
            targetUser.setFullName(fullName);
        }

        System.out.print("Type new email address: (Press ENTER for no change) ");
        String emailAddress = scanner.nextLine();
        if (!emailAddress.isEmpty()) {
            targetUser.setEmailAddress(emailAddress);
        }

        System.out.print("Type new date of birth (YYYY-MM-DD): (Press ENTER for no change) ");
        String dateOfBirth = scanner.nextLine();
        if (!dateOfBirth.isEmpty()) {
            targetUser.setDateOfBirth(dateOfBirth);
        }

        System.out.print("Type new card number (enter 0 for no change): ");
        String cardNumberInput = scanner.nextLine();
        if (!cardNumberInput.isEmpty() && !cardNumberInput.equals("0")) {
            try {
                long cardNumber = Long.parseLong(cardNumberInput);
                targetUser.setCardNumber(cardNumber);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid card number. Skipped updating.");
            }
        }

        System.out.print("Type new card provider: (Press ENTER for no change) ");
        String cardProvider = scanner.nextLine();
        if (!cardProvider.isEmpty()) {
            targetUser.setCardProvider(cardProvider);
        }

        System.out.print("Type new card expiry date (MM/YY): (Press ENTER for no change) ");
        String cardExpiryDate = scanner.nextLine();
        if (!cardExpiryDate.isEmpty()) {
            targetUser.setCardExpiryDate(cardExpiryDate);
        }

        System.out.print("Type new CVV (enter 0 for no change): ");
        String cvvInput = scanner.nextLine();
        if (!cvvInput.isEmpty() && !cvvInput.equals("0")) {
            try {
                int cvv = Integer.parseInt(cvvInput);
                targetUser.setCvv(cvv);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid CVV. Skipped updating.");
            }
        }

        System.out.print("Type new user type: (Press ENTER for no change) ");
        String userType = scanner.nextLine();
        if (!userType.isEmpty()) {
            targetUser.setUserType(userType);
        }
    }
}
