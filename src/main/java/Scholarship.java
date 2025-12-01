import java.util.*;

public class Scholarship {

    private final String name;
    private final double awardAmount;
    private final double minGpa;
    private final Set<String> eligibleMajors;
    private final Set<String> requiredKeywords;

    public Scholarship(String name,
                       double awardAmount,
                       double minGpa,
                       Set<String> eligibleMajors,
                       Set<String> requiredKeywords) {

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
    }

    public String getName() {
        return name;
    }

    public double getAwardAmount() {
        return awardAmount;
    }

    public double getMinGpa() {
        return minGpa;
    }

    public Set<String> getEligibleMajors() {
        return new HashSet<>(eligibleMajors);
    }

    public Set<String> getRequiredKeywords() {
        return new HashSet<>(requiredKeywords);
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "Name: " + name + '\n' +
                "Award Amount: " + awardAmount + '\n' +
                "Minimum GPA: " + minGpa + '\n' +
                "Eligible Majors: " + eligibleMajors + '\n' +
                "Required Keywords: " + requiredKeywords + '\n' +
                '}';
    }
}
