package backend;

import java.util.*;

public class Scholarship {

    private final String name;
    private final double minGpa;
    private final Set<String> eligibleMajors;
    private final Set<String> requiredKeywords;

    public Scholarship(String name,
                       double minGpa,
                       Set<String> eligibleMajors,
                       Set<String> requiredKeywords) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        this.name = name;

        if (minGpa < 0.0 || minGpa > 4.0) {
            throw new IllegalArgumentException("minGpa must be between 0.0 and 4.0");
        }
        this.minGpa = minGpa;

        if (eligibleMajors == null)
            throw new IllegalArgumentException("Eligible Majors cannot be null.");
        this.eligibleMajors = new HashSet<>(eligibleMajors);

        if (requiredKeywords == null)
            throw new IllegalArgumentException("Required Majors cannot be null.");
        this.requiredKeywords = new HashSet<>(requiredKeywords);
    }

    public String getName() {
        return name;
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
                "\tname=" + name + '\n' +
                "\tminGpa=" + minGpa + '\n' +
                "\teligibleMajors=" + eligibleMajors + '\n' +
                "\trequiredKeywords=" + requiredKeywords + '\n' +
                '}';
    }
}
