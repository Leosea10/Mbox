import java.time.LocalDateTime;

public class ActiveRental {
    private String bikeID;
    private String userEmail;
    private RegisteredUsers user;
    private LocalDateTime tripStartTime;

    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime) {
        this(bikeID, userEmail, null, tripStartTime);
    }

    public ActiveRental(String bikeID, String userEmail, RegisteredUsers user, LocalDateTime tripStartTime) {
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.user = user;
        this.tripStartTime = tripStartTime;
    }

    public String getBikeID() {
        return bikeID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public RegisteredUsers getUser() {
        return user;
    }

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    @Override
    public String toString() {
        return "Bike ID: " + bikeID + "\n"
                + "User Email: " + userEmail + "\n"
                + "Trip Start Time: " + tripStartTime;
    }
}
