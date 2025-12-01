package backend;

public class Applicant extends User {

    private final String major;
    private final double gpa;

    public Applicant(String netId, String name, String password, String major, double gpa) {
        super(netId, name, password, RoleType.APPLICANT);

        if (major == null || major.isEmpty())
            throw new IllegalArgumentException("Major cannot be null or empty.");
        this.major = major;

        if (gpa < 0.0 || gpa > 4.0)
            throw new IllegalArgumentException("GPA must be 0.0 – 4.0");
        this.gpa = gpa;
    }

    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
}

