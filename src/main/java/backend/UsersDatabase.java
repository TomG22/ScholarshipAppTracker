package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UsersDatabase {

    // username → password
    private static final Map<String, String> passwordByName = new HashMap<>();
    // username → User
    private static final Map<String, User> userByName = new HashMap<>();

    static {
        // Demo users
        addDemoUser("samiur", "pass123", User.RoleType.APPLICANT, "samiur@arizona.edu");
        addDemoUser("tom", "pass123", User.RoleType.REVIEWER, "tom@arizona.edu");
        addDemoUser("admin", "admin123", User.RoleType.ADMIN, "admin@arizona.edu");
    }

    private static void addDemoUser(String netId,
                                    String password,
                                    User.RoleType role,
                                    String email) {
        User u = new User(UUID.randomUUID(), netId, netId, email, role);
        userByName.put(netId, u);
        passwordByName.put(netId, password);
    }

    
    public static User getUser(String name, String password) {
        if (name == null || password == null) return null;
        String expected = passwordByName.get(name);
        if (expected == null) return null;
        if (!expected.equals(password)) return null;
        return userByName.get(name);
    }

    // Add a new user
    public static boolean addUser(User user) {
        if (user == null) return false;
        String key = user.getNetId();
        if (userByName.containsKey(key)) return false; // already exists
        userByName.put(key, user);
        passwordByName.put(key, "default123"); // demo password
        return true;
    }

    // Remove a user
    public static boolean removeUser(String netId) {
        if (netId == null) return false;
        User removed = userByName.remove(netId);
        passwordByName.remove(netId);
        return removed != null;
    }
}
