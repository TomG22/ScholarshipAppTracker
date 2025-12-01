import java.util.UUID;

public class UsersDatabase {

    private static User currentUser = null;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean authUser(String netID, String password) {
        User user = getUserByNetID(netID);
        return user != null && user.getPassword().equals(password);
    }

    public static User getUserByNetID(String netID) {
        Applicant a = ApplicantsDatabase.getApplicantByNetID(netID);
        if (a != null) return a;

        Reviewer r = ReviewersDatabase.getReviewerByNetID(netID);
        if (r != null) return r;

        // Not in either database
        return null;
    }

    public static User getUser(UUID id) {
        Applicant a = ApplicantsDatabase.getApplicant(id);
        if (a != null) return a;

        Reviewer r = ReviewersDatabase.getReviewer(id);
        if (r != null) return r;

        // Not in either database
        return null;
    }

    public static boolean removeUser(UUID id) {
        boolean a = ApplicantsDatabase.removeApplicant(id);
        if (a) return a;

        boolean r = ReviewersDatabase.removeReviewer(id);
        if (r) return r;

        // Not in either database
        return false;
    }
}
