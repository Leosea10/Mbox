public class ERyder {
    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;
    private int totalUsageInMinutes;
    private double totalFare;

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this(bikeID, batteryLevel, isAvailable, kmDriven, "N/A", "N/A");
    }

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.batteryLevel = 0;
        setBatteryLevel(batteryLevel);

        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;

        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("Bike is available.");
        } else {
            System.out.println("Bike is not available.");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Available: " + isAvailable);
        System.out.println("Distance Driven: " + kmDriven + " km");
    }

    private double calculateFare(int usageInMinutes) {
        this.totalUsageInMinutes = usageInMinutes;
        this.totalFare = BASE_FARE + (usageInMinutes * PER_MINUTE_FARE);
        return this.totalFare;
    }

    public void printRideDetails(int usageInMinutes) {
        calculateFare(usageInMinutes);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Total Usage: " + totalUsageInMinutes + " minutes");
        System.out.println("Total Fare: $" + totalFare);
    }

    public String getBikeID() {
        return bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level. Must be between 0 and 100.");
        }
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}

class EbikeMain {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("M-bike", 100, true, 0.0);
        bike1.printBikeDetails();
        bike1.printRideDetails(30);

        ERyder bike2 = new ERyder("N-bike", 45, true, 12.5, "user01", "555-1234");
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(45);
    }
}
