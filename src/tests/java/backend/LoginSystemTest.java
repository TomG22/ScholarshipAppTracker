package backend;

public class LoginSystemTest {

    public static void main(String[] args) {
        LoginSystem login = new LoginSystem();

        System.out.println("=== LoginSystem demo ===");

        // 1) valid login
        System.out.println("\nTest 1: valid login (samiur / pass123)");
        boolean ok1 = login.login("samiur", "pass123");
        System.out.println("Result: " + ok1 + ", current user = " +
                (login.getCurrentUser() != null ? login.getCurrentUser() : "null"));

        // 2) cannot login again while logged in
        System.out.println("\nTest 2: try logging in again while already logged in");
        boolean ok2 = login.login("tom", "pass123");
        System.out.println("Result: " + ok2);

        // 3) logout
        System.out.println("\nTest 3: logout");
        boolean ok3 = login.logout();
        System.out.println("Result: " + ok3);

        // 4) bad password
        System.out.println("\nTest 4: invalid credentials");
        boolean ok4 = login.login("samiur", "wrong");
        System.out.println("Result: " + ok4);

        // 5) login as admin
        System.out.println("\nTest 5: login as admin");
        boolean ok5 = login.login("admin", "admin123");
        System.out.println("Result: " + ok5 +
                ", role = " + login.getCurrentUserRole());

        System.out.println("\nLoginSystem tests complete.");
    }
}
