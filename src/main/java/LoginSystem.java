import java.util.Scanner;

public class LoginSystem {

    public static User client;

    public void signIn() {
        if (UsersDatabase.getCurrentUser() != null)
            System.out.println("A user is already signed in. Must log out first.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your netID: ");
            String netId = scanner.next();

            System.out.print("Enter your password: ");
            String password = scanner.next();

            User client = UsersDatabase.getUserByNetID(netId);

            if (client == null) {
                System.out.println("User does not exist. Please enter a valid user or create a new account.");
                continue;
            }

            if (!UsersDatabase.authUser(netId, password)) {
                System.out.println("Incorrect password. Please try again.");
                continue;
            }

            scanner.close();
            break;
        }
    }

    public void signOut() {
        client = null;
    }

    public void createAccount(User.RoleType role) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your netID: ");
            String netId = scanner.next();

            if (UsersDatabase.getUserByNetID(netId) != null) {
                System.out.println("An account with this netID already exists. Please choose a different netID.");
                continue;
            }

            System.out.print("Enter your full name: ");
            scanner.nextLine();  // clear newline
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

            User newUser = new User(netId, name, password, role);

            System.out.println("Account created successfully for role: " + role);
            scanner.close();
            break;
        }
    }
}
