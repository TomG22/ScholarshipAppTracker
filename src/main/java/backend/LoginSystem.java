package backend;

/**
 * LoginSystem – Handles login/logout state for the application.
 * Works with UsersDatabase and User objects.
 *
 * Supports:
 *  - FR-003 Login and Authentication
 *  - BA-006 Role-Based Access Control (RBAC)
 */
public class LoginSystem {

    private static User currentUser = null;

    /**
     * Attempts to authenticate a user with given credentials.
     *
     * @param name      Username (NetID or name)
     * @param password  Password associated with the user
     * @return true if login successful, false otherwise
     */
    public boolean login(String name, String password) {

        // Prevent logging in twice
        if (currentUser != null) {
            System.out.println("A user is already logged in: " + currentUser.getName());
            return false;
        }

        User user = UsersDatabase.getUser(name, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return false;
        }

        currentUser = user;
        System.out.println("Login successful: " + currentUser.getName()
                + " (" + currentUser.getRole() + ")");
        return true;
    }

    /**
     * Logs out the current user.
     *
     * @return true if logout happened, false otherwise
     */
    public boolean logout() {
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return false;
        }

        System.out.println("User logged out: " + currentUser.getName());
        currentUser = null;
        return true;
    }

    /**
     * @return the currently logged-in user, or null if none
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
