package backend;

import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Scanner;

public class LoginSystem {

    User client;

    public User getClient() {
        return this.client;
    }

    public User.RoleType getClientRole() {
        return this.client.getRole();
    }

    public String encStr(String str) {
        try {
            // Get a sha-256 byte array of the string 
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buf = md.digest(str.getBytes());

            // Convert the byte array to a hex and then to a string 
            return HexFormat.of().formatHex(buf);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Prompts for a user to sign in until they have signed in
    public void signIn() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prompt for the user's login details
            System.out.print("Enter your netID: ");
            String netId = scanner.next();

            System.out.print("Enter your password: ");
            String password = scanner.next();

            // Check if the user exists
            User client = UsersDatabase.getUser(netId);

            if (client == null) {
                System.out.println("User does not exist. Please enter a valid user or create a new account.");
                continue;
            }

            // If the user exists, attempt to log them in
            String encPassword = encStr(password);

            // The user entered the wrong password for the given account
            if (!UsersDatabase.authUser(netId, encPassword)) {
                System.out.println("Incorrect password. Please try again.");
                continue;
            }

            // The user successfully logged in
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
            // Prompt for netID
            System.out.print("Enter your netID: ");
            String netId = scanner.next();

            // Check if user already exists
            if (UsersDatabase.getUser(netId) != null) {
                System.out.println("An account with this netID already exists. Please choose a different netID.");
                continue;
            }

            // Prompt for full name
            System.out.print("Enter your full name: ");
            scanner.nextLine();  // clear newline
            String name = scanner.nextLine();

            // Password + confirmation
            System.out.print("Enter a password: ");
            String password = scanner.next();

            System.out.print("Confirm your password: ");
            String confirmPassword = scanner.next();

            while (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Try again.");
                System.out.print("Confirm your password: ");
                confirmPassword = scanner.next();
                continue;
            }

            // Encrypt password
            String encPassword = encStr(password);

            // Create user
            User newUser = new User(netId, name, encPassword, role);

            // Add user to database
            UsersDatabase.addUser(newUser);

            System.out.println("Account created successfully for role: " + role);
            scanner.close();
            break;
        }
    }
}
