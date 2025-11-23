package backend;

import java.util.UUID;

/**
 * User – represents any authenticated system user.
 *
 * Supports:
 *  - FR-003 Login and Authentication
 *  - BA-006 Role-Based Access Control (RBAC)
 *  - System Requirement: Security & Compliance (FERPA-based access)
 */
public class User {

    /**
     * RoleType – defines privileges for each user group.
     *
     * Aligns with:
     *  - AdminConsole (ADMIN)
     *  - StudentConsole (APPLICANT)
     *  - Reviewer workflows (REVIEWER)
     *  - Donor requirements (SCHOLARSHIP_PROVIDER)
     */
    public enum RoleType {
        APPLICANT,
        REVIEWER,
        SCHOLARSHIP_PROVIDER,
        ADMIN   
    }

    private final UUID userId;
    private final String netId;
    private final String name;
    private final String email;
    private final RoleType role;

   
    public User(UUID userId, String netId, String name, String email, RoleType role) {

        if (userId == null) throw new IllegalArgumentException("UserId cannot be null.");
        if (netId == null || netId.isEmpty()) throw new IllegalArgumentException("NetID cannot be null or empty.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email must be valid.");
        if (role == null) throw new IllegalArgumentException("Role cannot be null.");

        this.userId = userId;
        this.netId = netId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    /**
     * Helper method to generate a new user with a random UUID.
     */
    public static User genUser(String netId, String name, String email, RoleType role) {
        return new User(UUID.randomUUID(), netId, name, email, role);
    }

    // Getters
    public UUID getUserId() { return userId; }
    public String getNetId() { return netId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public RoleType getRole() { return role; }

    @Override
    public String toString() {
        return "[User: " + name + ", NetID=" + netId + ", Role=" + role + "]";
    }
}
