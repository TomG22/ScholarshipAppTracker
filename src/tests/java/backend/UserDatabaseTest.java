package backend;

public class UserDatabaseTest {

    public static void main(String[] args) {

        System.out.println("=== UsersDatabase Tests ===");

        // 1. Test existing demo user
        System.out.println("\nTest 1: login with existing demo user 'samiur'");
        User u1 = UsersDatabase.getUser("samiur", "pass123");
        System.out.println("Found: " + (u1 != null ? u1 : "null"));

        // 2. Test invalid password
        System.out.println("\nTest 2: invalid password");
        User u2 = UsersDatabase.getUser("samiur", "wrong");
        System.out.println("Result: " + (u2 != null ? u2 : "null"));

        // 3. Test add new user
        System.out.println("\nTest 3: add a new user");
        User newUser = User.genUser("john123", "John Doe", "john@arizona.edu", User.RoleType.APPLICANT);
        boolean added = UsersDatabase.addUser(newUser);
        System.out.println("Added new user? " + added);

        // 4. Test login new user
        System.out.println("\nTest 4: attempt login new user (default password)");
        User u3 = UsersDatabase.getUser("john123", "default123");
        System.out.println("Logged in as: " + (u3 != null ? u3 : "null"));

        // 5. Remove user
        System.out.println("\nTest 5: remove user");
        boolean removed = UsersDatabase.removeUser("john123");
        System.out.println("Removed? " + removed);

        // 6. Attempt login removed user
        System.out.println("\nTest 6: login removed user");
        User u4 = UsersDatabase.getUser("john123", "default123");
        System.out.println("Result: " + (u4 != null ? u4 : "null"));

        System.out.println("\nUsersDatabase tests complete.");
    }
}
