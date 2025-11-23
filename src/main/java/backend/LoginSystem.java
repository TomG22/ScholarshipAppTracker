import java.util.Scanner;

public class LoginSystem {

    User client;

    public User getClient() {
        return client;
    }

    // Prompts for a user to sign in until they have signed in
    public void signIn() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prompt for the user's login details
            System.out.print("Enter your netID: ");
            String netID = scanner.next();

            System.out.print("Enter your password: ");
            String password = scanner.next();

            // If the user exists, break from the loop
            User client = UsersDatabase.getUser(netID, password);
            if (client != null) {
                scanner.close();
                return;
            }

            // Otherwise, report a login failure message and continue loop
            System.out.println("Incorrect netID or password. Please try again.");
        } 
    }

    public void createAccount() {
        // TODO
    }
}
