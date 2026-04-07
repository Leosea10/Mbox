public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;

    public RegisteredUsers(
            String fullName,
            String emailAddress,
            String dateOfBirth,
            long cardNumber,
            String cardExpiryDate,
            String cardProvider,
            int cvv,
            String userType,
            String[] lastThreeTrips
    ) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }

    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    public double calculateFare(double baseFare) {
        return baseFare;
    }

    public void displayUserType() {
        System.out.println("RegularUser");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Type: ").append(userType).append("\n");
        sb.append("Full Name: ").append(fullName).append("\n");
        sb.append("Email Address: ").append(emailAddress).append("\n");
        sb.append("Date of Birth: ").append(dateOfBirth).append("\n");
        sb.append("Card Number: ").append(cardNumber).append("\n");
        sb.append("Card Provider: ").append(cardProvider).append("\n");
        sb.append("Card Expiry Date: ").append(cardExpiryDate).append("\n");
        sb.append("CVV: ").append(cvv).append("\n");
        sb.append("Last Three Trips:");
        if (lastThreeTrips == null || lastThreeTrips.length == 0) {
            sb.append(" None");
        } else {
            for (int i = 0; i < lastThreeTrips.length; i++) {
                sb.append("\n").append(i + 1).append(". ").append(lastThreeTrips[i]);
            }
        }
        return sb.toString();
    }
}
