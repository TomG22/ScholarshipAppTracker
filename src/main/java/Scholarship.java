import java.util.*;

public class Scholarship {

    private final UUID id;
    private final String name;
    private final double awardAmount;
    private final double minGpa;
    private final Set<String> eligibleMajors;
    private final Set<String> requiredKeywords;
    private final ScholarshipStatus status;
    private final Set<Application> applications;

    public enum ScholarshipStatus {
        OPEN,
        PENDING,
        AWARDED,
    }

    public Scholarship(UUID id,
                       String name,
                       double awardAmount,
                       double minGpa,
                       Set<String> eligibleMajors,
                       Set<String> requiredKeywords) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null.");
        this.id = id;

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        this.name = name;

        if (awardAmount < 0)
            throw new IllegalArgumentException("Award amount must be >= 0");
        this.awardAmount = awardAmount;

        if (minGpa < 0.0 || minGpa > 4.0)
            throw new IllegalArgumentException("Minimum GPA must be between 0.0 and 4.0");
        this.minGpa = minGpa;

        if (eligibleMajors == null)
            throw new IllegalArgumentException("Eligible Majors cannot be null.");
        this.eligibleMajors = new HashSet<>(eligibleMajors);

        if (requiredKeywords == null)
            throw new IllegalArgumentException("Required Keywords cannot be null.");
        this.requiredKeywords = new HashSet<>(requiredKeywords);

        this.status = ScholarshipStatus.OPEN;
        this.applications = new HashSet<>();
    }

    public Scholarship(String name,
                       double awardAmount,
                       double minGpa,
                       Set<String> eligibleMajors,
                       Set<String> requiredKeywords) {
        this.id = UUID.randomUUID();

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        this.name = name;

        if (awardAmount < 0)
            throw new IllegalArgumentException("Award amount must be >= 0");
        this.awardAmount = awardAmount;

        if (minGpa < 0.0 || minGpa > 4.0)
            throw new IllegalArgumentException("Minimum GPA must be between 0.0 and 4.0");
        this.minGpa = minGpa;

        if (eligibleMajors == null)
            throw new IllegalArgumentException("Eligible Majors cannot be null.");
        this.eligibleMajors = new HashSet<>(eligibleMajors);

        if (requiredKeywords == null)
            throw new IllegalArgumentException("Required Keywords cannot be null.");
        this.requiredKeywords = new HashSet<>(requiredKeywords);

        this.status = ScholarshipStatus.OPEN;
        this.applications = new HashSet<>();
    }

    public UUID getID() { return id; }
    public String getName() { return name; }
    public double getAwardAmount() { return awardAmount; }
    public double getMinGpa() { return minGpa; }

    public Set<String> getEligibleMajors() {
        return new HashSet<>(eligibleMajors);
    }

    public Set<String> getRequiredKeywords() {
        return new HashSet<>(requiredKeywords);
    }

    public boolean addApplication(Application application) {
        boolean addResult = applications.add(application);
        ApplicationsDatabase.addApplication(application);
        return addResult;
    }

    public boolean removeApplication(Application application) {
        boolean removeResult = applications.remove(application);
        ApplicationsDatabase.removeApplication(application.getID());
        return removeResult;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "\tID: " + id + '\n' +
                "\tName: " + name + '\n' +
                "\tAward Amount: " + awardAmount + '\n' +
                "\tMinimum GPA: " + minGpa + '\n' +
                "\tEligible Majors: " + eligibleMajors + '\n' +
                "\tRequired Keywords: " + requiredKeywords + '\n' +
                '}';
    }
}
