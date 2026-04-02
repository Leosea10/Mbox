import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BikeService {
    private static int logCounter = 1;
    private final Stack<ERyderLog> systemLogs = new Stack<>();
    private final Queue<BikeRequest> bikeRequests = new ArrayDeque<>();

    public String validateLocation(String location) {
        return validateLocation(location, null);
    }

    public String validateLocation(String location, String userEmail) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        addBikeRequest(userEmail, location);
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
                String event = "Bike with " + bikeID + " was rented from " + bike.getLocation();
                pushSystemLog("BR", event, tripStartTime);
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

    public void logTripStart(String bikeID, String userEmail, LocalDateTime timeStamp) {
        String event = "Trip started for bike " + bikeID + " by " + userEmail;
        pushSystemLog("TS", event, timeStamp);
    }

    public void logTripEnd(String bikeID, String userEmail, LocalDateTime timeStamp) {
        String event = "Trip ended for bike " + bikeID + " by " + userEmail;
        pushSystemLog("TE", event, timeStamp);
    }

    public void viewSystemLogs() {
        if (systemLogs.isEmpty()) {
            System.out.println("No system logs available.");
            return;
        }

        for (ERyderLog log : systemLogs) {
            System.out.println(log);
        }
    }

    public void viewBikeRequests() {
        if (bikeRequests.isEmpty()) {
            System.out.println("No pending bike requests.");
            return;
        }

        for (BikeRequest request : bikeRequests) {
            System.out.println(request);
        }
    }

    public void removeNextBikeRequest() {
        BikeRequest request = bikeRequests.poll();
        if (request == null) {
            System.out.println("No pending bike requests.");
            return;
        }

        System.out.println("Removed request: " + request);
    }

    public void assignNextBikeRequestIfAny() {
        BikeRequest request = bikeRequests.poll();
        if (request == null) {
            return;
        }

        System.out.println("Assigning available bike to next request: " + request);
    }

    private void addBikeRequest(String userEmail, String location) {
        if (userEmail == null || userEmail.isBlank() || location == null || location.isBlank()) {
            return;
        }

        bikeRequests.offer(new BikeRequest(userEmail, location, LocalDateTime.now()));
    }

    private void pushSystemLog(String prefix, String event, LocalDateTime timeStamp) {
        String id = prefix + String.format("%03d", logCounter++);
        systemLogs.push(new ERyderLog(id, event, timeStamp));
    }
}
