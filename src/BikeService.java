import java.time.LocalDateTime;

public class BikeService {
    public String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    public LocalDateTime reserveBike(String bikeID) {
        if (bikeID == null) {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
            return null;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equalsIgnoreCase(bikeID)) {
                LocalDateTime tripStartTime = LocalDateTime.now();
                bike.setIsAvailable(false);
                bike.setLastUsedTime(tripStartTime);
                System.out.println("Reserving the bike with the " + bikeID
                        + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                return tripStartTime;
            }
        }

        System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        return null;
    }

    public void releaseBike(String bikeID) {
        if (bikeID == null) {
            return;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equalsIgnoreCase(bikeID)) {
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                return;
            }
        }
    }
}
