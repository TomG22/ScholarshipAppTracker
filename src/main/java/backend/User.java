package backend;

import java.util.UUID;

public class User {

    // Enum used for classifying user privileges
    public enum RoleType {
        APPLICANT,
        REVIEWER,
        SCHOLARSHIP_PROVIDER,
        // Extra roles, if needed:
        // SCHOLARSHIP_ADMIN,
        // FUND_STEWARD,
        // ENGR_IT,
    }

    private final String netId;
    private final String password;
    private final String name;
    private final RoleType role;

    // Constructor
    public User(String netId, String password, String name, RoleType role) {

        if (netId == null || netId.isEmpty()) throw new IllegalArgumentException("NetID cannot be null or empty.");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty.");
        if (role == null) throw new IllegalArgumentException("Role cannot be null.");

        this.netId = netId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // Getters
    public String getNetId() { return netId; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public RoleType getRole() { return role; }
}
