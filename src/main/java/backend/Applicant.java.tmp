package backend;

import java.util.List;

/**
 * Applicant model used by the Matching Engine.
 *
 * Supports:
 *  - ME-001 GPA filtering
 *  - ME-002 Major comparison
 *  - ME-003 Essay & extracurricular keywords
 *
 * This class is intentionally simple so the backend
 * can expand it (e.g., add NetID, UID, year, etc.)
 */
public class Applicant {

    private String name;
    private double gpa;
    private String major;
    private List<String> essayKeywords;
    private List<String> extracurricularKeywords;

    public Applicant(String name, double gpa, String major,
                     List<String> essayKeywords,
                     List<String> extracurricularKeywords) {
        this.name = name;
        this.gpa = gpa;
        this.major = major;
        this.essayKeywords = essayKeywords;
        this.extracurricularKeywords = extracurricularKeywords;
    }

    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public String getMajor() { return major; }

    public List<String> getEssayKeywords() { return essayKeywords; }
    public List<String> getExtracurricularKeywords() { return extracurricularKeywords; }

}

