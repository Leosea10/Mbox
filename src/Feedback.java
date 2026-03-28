public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private boolean longFeedback;
    private String reviewID;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(
            boolean isConcatenation,
            String sent1,
            String sent2,
            String sent3,
            String sent4,
            String sent5
    ) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            StringBuilder sb = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
            completeFeedback = sb.toString();
        }

        longFeedback = checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }

    private StringBuilder feedbackUsingStringBuilder(
            String sent1,
            String sent2,
            String sent3,
            String sent4,
            String sent5
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb;
    }

    private boolean checkFeedbackLength(String completeFeedback) {
        if (completeFeedback != null && completeFeedback.length() > 500) {
            longFeedback = true;
        } else {
            longFeedback = false;
        }
        return longFeedback;
    }

    private String feedbackUsingConcatenation(
            String sent1,
            String sent2,
            String sent3,
            String sent4,
            String sent5
    ) {
        return sent1 + sent2 + sent3 + sent4 + sent5;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String combinedNames = firstName + lastName;
        String namePart = combinedNames.substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();

        String id = namePart + feedbackPart + completeFeedback.length() + "_" + System.currentTimeMillis();
        reviewID = id.replace(" ", "");
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Email: " + email + "\n"
                + "Complete Feedback: " + completeFeedback + "\n"
                + "Long Feedback: " + longFeedback + "\n"
                + "Review ID: " + reviewID;
    }

    public static void main(String[] args) {
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        Feedback userFeedback = new Feedback("John", "Doe", "john.doe@example.com");
        userFeedback.analyseFeedback(false, sent1, sent2, sent3, sent4, sent5);
        System.out.println(userFeedback);
    }
}
