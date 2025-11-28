package backend;

import java.util.*;

/**
 * Applicant model used for the MatchingEngine.
 * Supports ME-001, ME-002, ME-003 from the SRS.
 */
public class Applicant {

    private final String name;
    private final String major;
    private final double gpa;

    public Applicant(String name,
                     String major,
                     double gpa) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.major = Objects.requireNonNull(major, "major cannot be null");

        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be 0.0 – 4.0");
        }
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
}
