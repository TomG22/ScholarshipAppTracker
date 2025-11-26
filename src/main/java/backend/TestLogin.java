package backend;

public class TestLogin {
    public static void main(String[] args) {
        // Initialize the login helper
        LoginSystem loginSys = new LoginSystem();

        // Try to log in a user (replace with actual test credentials)
        boolean loggedIn = loginSys.login("testUser", "testPassword");

        if (!loggedIn) {
            System.out.println("Login failed. Cannot proceed with role-based message.");
            return;
        }

        // Get the currently logged-in user
        User client = loginSys.getCurrentUser();

        if (client == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        String clientName = client.getName();

        switch (client.getRole()) {
            case APPLICANT:
                System.out.println("Applicant " + clientName + " has successfully logged in");
                break;
            case REVIEWER:
                System.out.println("Scholarship Reviewer " + clientName + " has successfully logged in");
                break;
            case SCHOLARSHIP_PROVIDER:
                System.out.println("Scholarship Provider " + clientName + " has successfully logged in");
                break;
            default:
                System.out.println("User " + clientName + " with role " + client.getRole()
                        + " has successfully logged in");
                break;
        }

        // Optional: log out at the end
        loginSys.logout();
    }
}
