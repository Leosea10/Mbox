import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
public class UserRegistration {
    private static final double VIP_BASE_FEE = 100.0;
    private static final Scanner SCANNER = new Scanner(System.in);

    private int choice;
    private String userType;
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private LocalDate dob;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private int cvv;

    private boolean emailValid;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;
    private boolean minor;
    private boolean minorAndBirthday;
    private double feeToCharge;

    public void registration() {
        minor = false;
        minorAndBirthday = false;

        System.out.print("Please enter your choice (1 or 2): ");
        choice = SCANNER.nextInt();
        SCANNER.nextLine();

        System.out.print("Please enter your full name: ");
        fullName = SCANNER.nextLine();

        System.out.print("Please enter your email address: ");
        emailAddress = SCANNER.nextLine();
        emailValid = analyseEmail(emailAddress);
        if (!emailValid) {
            return;
        }

        System.out.print("Please enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = SCANNER.nextLine();
        dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        if (!ageValid) {
            return;
        }

        System.out.print("Please enter your card number (Visa, MasterCard, and American Express only): ");
        cardNumber = SCANNER.nextLong();
        SCANNER.nextLine();
        cardNumberValid = analyseCardNumber(cardNumber);
        if (!cardNumberValid) {
            return;
        }

        System.out.print("Please enter your card expiry date (MM/YY): ");
        cardExpiryDate = SCANNER.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        if (!cardStillValid) {
            return;
        }

        System.out.print("Please enter your card CVV: ");
        cvv = SCANNER.nextInt();
        SCANNER.nextLine();
        validCVV = analyseCVV(cvv);
        if (!validCVV) {
            return;
        }

        finalCheckpoint();
    }
    private boolean analyseEmail(String emailAddress) {
        boolean hasAt = emailAddress.contains("@");
        boolean hasDot = emailAddress.contains(".");
        if (hasAt && hasDot) {
            System.out.println("Email is valid");
            return true;
        }

        System.out.println("Invalid email address. Going back to the start of the registration");
        registration();
        return false;
    }
    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        int ageInYears = Period.between(dob, currentDate).getYears();
        boolean isBirthday = dob.getMonthValue() == currentDate.getMonthValue()
                && dob.getDayOfMonth() == currentDate.getDayOfMonth();

        if ("VIP User".equals(userType)) {
            if (isBirthday && ageInYears <= 18 && ageInYears > 12) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && ageInYears <= 18 && ageInYears > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }

        if (ageInYears <= 12 || ageInYears > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
            return false;
        }

        return true;
    }
    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        }

        if (cardNumStr.length() == 16
                && ((firstTwoDigits >= 51 && firstTwoDigits <= 55)
                || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        }

        if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }

        System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
        System.out.println("Going back to the start of the registration.");
        registration();
        return false;
    }
    private boolean analyseCardExpiryDate(String cardExpiryDate) {
        int month = Integer.parseInt(cardExpiryDate.substring(0, 2));
        int year = Integer.parseInt(cardExpiryDate.substring(3, 5)) + 2000;

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        }

        System.out.println("Sorry, your card has expired. Please use a different card.");
        System.out.println("Going back to the start to the registration process...");
        registration();
        return false;
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        boolean valid = ("American Express".equals(cardProvider) && cvvStr.length() == 4)
                || ("VISA".equals(cardProvider) && cvvStr.length() == 3)
                || ("MasterCard".equals(cardProvider) && cvvStr.length() == 3);

        if (valid) {
            System.out.println("Card CVV is valid.");
            return true;
        }

        System.out.println("Invalid CVV for the given card.");
        System.out.println("Going back to the start of the registration process.");
        registration();
        return false;
    }
    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
            return;
        }

        System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
        if (!emailValid) {
            System.out.println("Invalid email address");
        }
        if (!ageValid) {
            System.out.println("Invalid age");
        }
        if (!cardNumberValid) {
            System.out.println("Invalid card number");
        }
        if (!cardStillValid) {
            System.out.println("Card has expired");
        }
        if (!validCVV) {
            System.out.println("Invalid CVV");
        }
        System.out.println("Going back to the start of the registration process.");
        registration();
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * 0.75;
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * 0.80;
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumberStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);

        System.out.println("Thank you for your payment.");
        System.out.println("A fee of " + feeToCharge + " has been charged to your card ending with " + lastFourDigits);
        SCANNER.close();
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "Registration successful! Here are your details:\n"
                + "User Type: " + userType + "\n"
                + "Full Name: " + fullName + "\n"
                + "Email Address: " + emailAddress + "\n"
                + "Date of Birth: " + dateOfBirth + "\n"
                + "Card Number: " + censoredNumber + "\n"
                + "Card Provider: " + cardProvider + "\n"
                + "Card Expiry Date: " + cardExpiryDate;
    }
}

