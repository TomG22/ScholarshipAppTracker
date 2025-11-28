package backend;

public class TestLogin {
    public static void main(String[] args) {
        // Initialize the login helper
        LoginSystem loginSys = new LoginSystem();

        // Sign in a user
        loginSys.signIn();

        // Output when the user has logged in
        String clientName = loginSys.getClient().getName();
        switch (loginSys.getClient().getRole()) {
            case APPLICANT:
                System.out.println("Applicant " + clientName + " has successfully logged in");
                break;
            case REVIEWER:
                System.out.println("Scholarship Reviewer " + clientName + " has successfully logged in");
                break;
            case SCHOLARSHIP_PROVIDER:
                System.out.println("Scholarship Provider " + clientName + " has successfully logged in");
                break;
        }

        loginSys.signOut();

        if (loginSys.getClient() == null) {
            System.out.println("Successfully logged out " + clientName);
        }
    }
}
