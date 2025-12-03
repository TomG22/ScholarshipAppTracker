import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LoginSystem {

    public static User client;
    private final Scanner scanner = new Scanner(System.in); // Reuse single Scanner

    public void signIn() {
        if (UsersDatabase.getCurrentUser() != null) {
            System.out.println("A user is already signed in. Must log out first.");
            return;
        }

        while (true) {
            System.out.print("Enter your netID: ");
            String netId = scanner.next();

            System.out.print("Enter your password: ");
            String password = scanner.next();

            User user = UsersDatabase.getUserByNetID(netId);

            if (user == null) {
                System.out.println("User does not exist. Please enter a valid user or create a new account.");
                continue;
            }

            if (!UsersDatabase.authUser(netId, password)) {
                System.out.println("Incorrect password. Please try again.");
                continue;
            }

            UsersDatabase.setCurrentUser(user);
            client = user;
            System.out.println("Signed in successfully as " + user.getName());
            break;
        }
    }

    public void signOut() {
        if (client != null) {
            System.out.println("Signing out " + client.getName());
            UsersDatabase.setCurrentUser(null);
            client = null;
        } else {
            System.out.println("No user is currently signed in.");
        }
    }

    public void createAccount(User.RoleType role) {
        System.out.print("Enter your netID: ");
        String netId = scanner.next();

        if (UsersDatabase.getUserByNetID(netId) != null) {
            System.out.println("An account with this netID already exists. Please choose a different netID.");
            return;
        }

        scanner.nextLine(); // clear newline
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.next();

        System.out.print("Confirm your password: ");
        String confirmPassword = scanner.next();

        while (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Try again.");
            System.out.print("Confirm your password: ");
            confirmPassword = scanner.next();
        }

        User newUser = null;

        if (role == User.RoleType.APPLICANT) {
            System.out.print("Enter your major: ");
            scanner.nextLine(); // clear newline
            String major = scanner.nextLine();

            System.out.print("Enter your GPA (0.0 - 4.0): ");
            double gpa = scanner.nextDouble();

            scanner.nextLine(); // clear newline
            System.out.print("Enter extracurriculars (comma-separated): ");
            String extrasInput = scanner.nextLine();
            Set<String> extracurriculars = new HashSet<>();
            for (String e : extrasInput.split(",")) {
                if (!e.trim().isEmpty()) extracurriculars.add(e.trim());
            }

            newUser = new Applicant(netId, name, password, major, extracurriculars, gpa);
            ApplicantsDatabase.addApplicant((Applicant) newUser);

        } else if (role == User.RoleType.REVIEWER) {
            System.out.print("Enter majors you can review (comma-separated): ");
            scanner.nextLine(); // clear newline
            String majorsInput = scanner.nextLine();
            Set<String> majors = new HashSet<>();
            for (String m : majorsInput.split(",")) {
                if (!m.trim().isEmpty()) majors.add(m.trim());
            }

            newUser = new Reviewer(netId, name, password, majors);
            ReviewersDatabase.addReviewer((Reviewer) newUser);

        } else {
            System.out.println("Invalid role.");
            return;
        }

        UsersDatabase.setCurrentUser(newUser);
        client = newUser;

        System.out.println("Account created successfully for role: " + role);
        System.out.println("Signed in as " + newUser.getName());
    }
}