package backend;

public class TestUserDatabase {
    public static boolean testUserAddSuccess(User dummyUser) {
        boolean addUserResult = UsersDatabase.addUser(dummyUser);
        return addUserResult;
    }

    public static boolean testUserAddFail(User dummyUser) {
        UsersDatabase.addUser(dummyUser); 
        boolean addUserResult = UsersDatabase.addUser(dummyUser);
        return !addUserResult;  
    }

    public static boolean testUserRemoveSuccess(User dummyUser) {
        return UsersDatabase.removeUser(dummyUser.getNetId());
    }

    public static boolean testUserRemoveFail(User dummyUser) {
        return !UsersDatabase.removeUser(dummyUser.getNetId());
    }

    public static boolean testClear() {
        UsersDatabase.clear(); 
        
        return UsersDatabase.isEmpty();
    }

    public static void main(String[] args) {
        // Create a dummy user
        User dummyUser1 = new User("fakeNetId", "John Doe", "not an encrypted password", User.RoleType.APPLICANT);

        boolean passed = true;

        // Clear the database first to start with an empty sandbox
        if (!testClear()) {
            System.out.println("Test: Failed to clear the users database");
            passed = false;
        }

        // Test add user
        if (!testUserAddSuccess(dummyUser1)) {
            System.out.println("Test: Failed to add a new user");
            passed = false;
        }

        if (!testUserAddFail(dummyUser1)) {
            System.out.println("Test: Failed to reject adding a user if it already exists");
            passed = false;
        }

        // Test remove user
        if (!testUserRemoveSuccess(dummyUser1)) {
            System.out.println("Test: Failed to remove the existing user");
            passed = false;
        }

        if (!testUserRemoveFail(dummyUser1)) {
            System.out.println("Test: Failed to reject removing a non-existing user");
            passed = false;
        }

        // Output test results
        if (passed) {
            System.out.println("Test: All tests passed successfully!");
        }
    }
}

