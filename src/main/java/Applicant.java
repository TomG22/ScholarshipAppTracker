import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Applicant extends User {

    private final String major;
    private final double gpa;
    private double awardBalance;
    private Set<String> extracurriculars;

    public Applicant(String netId, String name, String password, String major, Set<String> extracurriculars, double gpa) {
        super(netId, name, password, RoleType.APPLICANT);

        if (major == null || major.isEmpty())
            throw new IllegalArgumentException("Major cannot be null or empty.");
        this.major = major;

        if (extracurriculars == null)
            throw new IllegalArgumentException("Extracurricular Keywords cannot be null.");
        this.extracurriculars = extracurriculars;

        if (gpa < 0.0 || gpa > 4.0)
            throw new IllegalArgumentException("GPA must be 0.0 – 4.0");
        this.gpa = gpa;

        this.awardBalance = 0.0;
    }

    public Applicant(UUID id, String netId, String name, String password, String major, Set<String> extracurriculars, double gpa, double awardBalance) {
        super(id, netId, name, password, RoleType.APPLICANT);

        if (major == null || major.isEmpty())
            throw new IllegalArgumentException("Major cannot be null or empty.");
        this.major = major;

        if (extracurriculars == null)
            throw new IllegalArgumentException("Extracurricular Keywords cannot be null.");
        this.extracurriculars = extracurriculars;

        if (gpa < 0.0 || gpa > 4.0)
            throw new IllegalArgumentException("GPA must be 0.0 – 4.0");
        this.gpa = gpa;
        this.awardBalance = 0.0;
    }

    public String getMajor() { return major; }

    public Set<String> getExtracurriculars() {
        return new HashSet<>(extracurriculars);
    }

    public double getGpa() { return gpa; }
    public double getAwardBalance() { return awardBalance; }

    public Set<Application> getApplications() {
        Set<Application> applications = new HashSet<Application>();

        for (Application application : ApplicationsDatabase.getAllApplications()) {
            if (application.getAuthor().getID() == getID())
                applications.add(application);
        }

        return applications;
    }

    public Set<String> getEssayWords() {
        Set<String> words = new HashSet<>();

        for (Application application : getApplications()) {
            String essay = application.getEssay();

            if (essay != null) {
                for (String word : essay.split("\\s+")) {
                    if (!word.isEmpty()) {
                        words.add(word.toLowerCase());
                    }
                }
            }
        }

        return words;
    }

    public void setAwardBalance(double awardBalance) {
        this.awardBalance = awardBalance;
        UsersDatabase.removeUser(getID());
        UsersDatabase.addUser(this);
    }
}
