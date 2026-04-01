public class BikeRental {
    private final BikeRentalConsole console;

    public BikeRental() {
        BikeService bikeService = new BikeService();
        RentalService rentalService = new RentalService(bikeService);
        console = new BikeRentalConsole(new java.util.Scanner(System.in), bikeService, rentalService);
    }

    public void simulateApplication() {
        console.simulateApplication();
    }
}
