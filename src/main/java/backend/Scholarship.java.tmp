package backend;

import java.util.List;

/**
 * Scholarship model for Matching Engine.
 *
 * Supports:
 *  - ME-001 Minimum GPA requirement
 *  - ME-002 Eligible majors list
 *  - ME-003 Required keyword list
 */
public class Scholarship {

    private String name;
    private double minGpa;
    private List<String> eligibleMajors;
    private List<String> requiredKeywords;

    public Scholarship(String name, double minGpa,
                       List<String> eligibleMajors,
                       List<String> requiredKeywords) {

        this.name = name;
        this.minGpa = minGpa;
        this.eligibleMajors = eligibleMajors;
        this.requiredKeywords = requiredKeywords;
    }

    public String getName() { return name; }
    public double getMinGpa() { return minGpa; }
    public List<String> getEligibleMajors() { return eligibleMajors; }
    public List<String> getRequiredKeywords() { return requiredKeywords; }

}

