import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class TestLogin {
    public static void main(String[] args) {
        LoginSystem loginSys = new LoginSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("TEST: Creating an Applicant");
        // Simulate creating an applicant
        User.RoleType applicantRole = User.RoleType.APPLICANT;

        // Manually create an Applicant for testing
        Set<String> extracurriculars = new HashSet<>();
        extracurriculars.add("debate");
        extracurriculars.add("robotics");
        Applicant testApplicant = new Applicant("a123", "Alice Johnson", "pass123", "Computer Science", extracurriculars, 3.8);
        ApplicantsDatabase.addApplicant(testApplicant);

        System.out.println("Applicant 'Alice Johnson' added to database.");

        System.out.println("\nTEST: Signing in as Applicant");
        // Test sign in with correct credentials
        simulateSignIn("a123", "pass123", loginSys);

        // Test sign out
        loginSys.signOut();
        if (UsersDatabase.getCurrentUser() == null) {
            System.out.println("Applicant successfully logged out.");
        }

        System.out.println("\nTEST: Creating a Reviewer");
        User.RoleType reviewerRole = User.RoleType.REVIEWER;
        Set<String> reviewerMajors = new HashSet<>();
        reviewerMajors.add("Computer Science");
        reviewerMajors.add("Mathematics");
        Reviewer testReviewer = new Reviewer("r456", "Bob Smith", "revpass", reviewerMajors);
        ReviewersDatabase.addReviewer(testReviewer);

        System.out.println("Reviewer 'Bob Smith' added to database.");

        System.out.println("\nTEST: Signing in as Reviewer with wrong password");
        simulateSignIn("r456", "wrongpass", loginSys); // should fail

        System.out.println("\nTEST: Signing in as Reviewer with correct password");
        simulateSignIn("r456", "revpass", loginSys);

        loginSys.signOut();
        if (UsersDatabase.getCurrentUser() == null) {
            System.out.println("Reviewer successfully logged out.");
        }

        scanner.close();
    }

    // Helper method to simulate login without user input
    private static void simulateSignIn(String netID, String password, LoginSystem loginSys) {
        User user = UsersDatabase.getUserByNetID(netID);

        if (user == null) {
            System.out.println("No such user exists: " + netID);
            return;
        }

        if (!UsersDatabase.authUser(netID, password)) {
            System.out.println("Incorrect password for user: " + netID);
            return;
        }

        UsersDatabase.setCurrentUser(user);
        LoginSystem.client = user;

        System.out.println(user.getRole() + " '" + user.getName() + "' signed in successfully.");
    }
}
