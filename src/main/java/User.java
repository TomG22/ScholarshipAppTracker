public class User {

    public enum RoleType {
        APPLICANT,
        REVIEWER,
        SCHOLARSHIP_PROVIDER,
    }

    private final String netId;
    private final String password;
    private final String name;
    private final RoleType role;

    public User(String netId, String name, String password, RoleType role) {
        if (netId == null || netId.isEmpty())
            throw new IllegalArgumentException("NetID cannot be null or empty.");

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty.");

        if (role == null)
            throw new IllegalArgumentException("Role cannot be null.");

        this.netId = netId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getNetId() { return netId; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public RoleType getRole() { return role; }
}
