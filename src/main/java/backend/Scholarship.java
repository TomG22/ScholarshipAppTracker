package backend;

import java.util.*;

/**
 * Scholarship model used for the MatchingEngine.
 *
 * Supports:
 *  - ME-001 (min GPA gate)
 *  - ME-002 (eligible majors)
 *  - ME-003 (required keywords)
 */
public class Scholarship {

    private final String name;
    private final double minGpa;
    private final Set<String> eligibleMajors;     // lowercased
    private final Set<String> requiredKeywords;   // lowercased

    /**
     * Constructor matching MatchingEngineTest usage.
     *
     * @param name              human-readable scholarship name
     * @param minGpa            minimum GPA needed (0.0–4.0)
     * @param eligibleMajors    list of allowed majors (e.g. "computer science"); empty = any major
     * @param requiredKeywords  list of required keywords; empty = no keyword constraints
     */
    public Scholarship(String name,
                       double minGpa,
                       Collection<String> eligibleMajors,
                       Collection<String> requiredKeywords) {

        this.name = Objects.requireNonNull(name, "name cannot be null");

        if (minGpa < 0.0 || minGpa > 4.0) {
            throw new IllegalArgumentException("minGpa must be between 0.0 and 4.0");
        }
        this.minGpa = minGpa;

        this.eligibleMajors = new HashSet<>();
        if (eligibleMajors != null) {
            for (String m : eligibleMajors) {
                if (m != null) {
                    this.eligibleMajors.add(m.toLowerCase());
                }
            }
        }

        this.requiredKeywords = new HashSet<>();
        if (requiredKeywords != null) {
            for (String kw : requiredKeywords) {
                if (kw != null) {
                    this.requiredKeywords.add(kw.toLowerCase());
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getMinGpa() {
        return minGpa;
    }

    /**
     * Eligible majors as lowercase strings.
     */
    public Set<String> getEligibleMajors() {
        return new HashSet<>(eligibleMajors);
    }

    /**
     * Required keywords as lowercase strings.
     */
    public Set<String> getRequiredKeywords() {
        return new HashSet<>(requiredKeywords);
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "name='" + name + '\'' +
                ", minGpa=" + minGpa +
                ", eligibleMajors=" + eligibleMajors +
                ", requiredKeywords=" + requiredKeywords +
                '}';
    }
}
