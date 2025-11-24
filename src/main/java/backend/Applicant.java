package backend;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Simple Applicant model used for MatchingEngine.
 * Supports ME-001, ME-002, ME-003.
 */
public class Applicant {

    private final String id;
    private final String name;
    private final double gpa;
    private final String major;
    private final Set<String> essayKeywords;
    private final Set<String> extracurricularKeywords;

    public Applicant(String id,
                     String name,
                     double gpa,
                     String major,
                     Set<String> essayKeywords,
                     Set<String> extracurricularKeywords) {

        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.major = Objects.requireNonNull(major, "major cannot be null");

        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("gpa must be between 0.0 and 4.0");
        }
        this.gpa = gpa;

        this.essayKeywords =
                (essayKeywords == null) ? new HashSet<>() : new HashSet<>(essayKeywords);
        this.extracurricularKeywords =
                (extracurricularKeywords == null) ? new HashSet<>() : new HashSet<>(extracurricularKeywords);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public String getMajor() { return major; }
    public Set<String> getEssayKeywords() { return new HashSet<>(essayKeywords); }
    public Set<String> getExtracurricularKeywords() { return new HashSet<>(extracurricularKeywords); }
}
