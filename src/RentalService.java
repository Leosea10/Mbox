import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    private final LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private final BikeService bikeService;

    public RentalService(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    public boolean startRental(String bikeID, String emailAddress) {
        LocalDateTime tripStartTime = bikeService.reserveBike(bikeID);
        if (tripStartTime == null) {
            return false;
        }

        ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
        activeRentalsList.add(activeRental);
        return true;
    }

    public void endRental(String bikeID) {
        removeActiveRental(bikeID);
        bikeService.releaseBike(bikeID);
        if (bikeID != null) {
            System.out.println("Your trip has ended. Thank you for riding with us.");
        }
    }

    public void cancelRental(String bikeID) {
        removeActiveRental(bikeID);
        bikeService.releaseBike(bikeID);
        if (bikeID != null) {
            System.out.println("Your rental has been canceled.");
        }
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }

        for (ActiveRental rental : activeRentalsList) {
            System.out.println(rental);
        }
    }

    private void removeActiveRental(String bikeID) {
        if (bikeID == null) {
            return;
        }

        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equalsIgnoreCase(bikeID)) {
                iterator.remove();
                break;
            }
        }
    }
}
