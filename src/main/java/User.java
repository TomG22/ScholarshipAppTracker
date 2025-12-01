import java.util.UUID;

public class User {

    public enum RoleType {
        APPLICANT,
        REVIEWER,
        SCHOLARSHIP_PROVIDER,
    }

    private final UUID id;
    private final String netID;
    private final String password;
    private final String name;
    private final RoleType role;

    public User(UUID id,
                String netID,
                String name,
                String password,
                RoleType role) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null.");
        this.id = id;

        if (netID == null || netID.isEmpty())
            throw new IllegalArgumentException("NetID cannot be null or empty.");

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty.");

        if (role == null)
            throw new IllegalArgumentException("Role cannot be null.");

        this.netID = netID;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String netID, String name, String password, RoleType role) {
        this.id = UUID.randomUUID();

        if (netID == null || netID.isEmpty())
            throw new IllegalArgumentException("NetID cannot be null or empty.");

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty.");

        if (role == null)
            throw new IllegalArgumentException("Role cannot be null.");

        this.netID = netID;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UUID getID() { return id; }
    public String getNetID() { return netID; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public RoleType getRole() { return role; }

    @Override
    public String toString() {
        return "User{" +
                "ID: " + id + '\n' +
                "NetID: " + netID + '\n' +
                "Name: " + name + '\n' +
                "Role: " + role + '\n' +
                '}';
    }
}
