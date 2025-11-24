package backend;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Scholarship model used for MatchingEngine.
 * Supports ME-001, ME-002, ME-003, ME-004.
 */
public class Scholarship {

    private final String id;
    private final String name;
    private final double minGpa;
    private final Set<String> eligibleMajors;
    private final Set<String> requiredKeywords;

    public Scholarship(String id,
                       String name,
                       double minGpa,
                       Set<String> eligibleMajors,
                       Set<String> requiredKeywords) {

        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");

        if (minGpa < 0.0 || minGpa > 4.0) {
            throw new IllegalArgumentException("minGpa must be between 0.0 and 4.0");
        }
        this.minGpa = minGpa;

        this.eligibleMajors =
                (eligibleMajors == null) ? new HashSet<>() : new HashSet<>(eligibleMajors);
        this.requiredKeywords =
                (requiredKeywords == null) ? new HashSet<>() : new HashSet<>(requiredKeywords);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getMinGpa() { return minGpa; }

    /** majors must be lowercase strings in this set */
    public Set<String> getEligibleMajors() { return new HashSet<>(eligibleMajors); }

    /** keywords expected to be lowercase strings */
    public Set<String> getRequiredKeywords() { return new HashSet<>(requiredKeywords); }
}
