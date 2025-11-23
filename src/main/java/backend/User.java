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

    private final UUID userId;
    private final String netId;
    private final String name;
    private final String email;
    private final RoleType role;

    // Constructor
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

    // Constructor to generate a new user and uuid automatically
    public static User genUser(String netId, String name, String email, RoleType role) {
        UUID generatedUserId = UUID.randomUUID();
        return new User(generatedUserId, netId, name, email, role);
    }

    // Getters
    public UUID getUserId() { return userId; }
    public String getNetId() { return netId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public RoleType getRole() { return role; }
}
