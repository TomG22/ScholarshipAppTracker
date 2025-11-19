package backend;

/**
 * MatchResult – Holds the match score and explanation text.
 *
 * Supports:
 *  - ME-004 Weighted scoring output
 *  - ME-005 Sorting match results
 *  - ME-006 Logging and traceability
 */
public class MatchResult implements Comparable<MatchResult> {

    private Applicant applicant;
    private Scholarship scholarship;
    private double score;
    private String reason;

    public MatchResult(Applicant applicant, Scholarship scholarship,
                       double score, String reason) {
        this.applicant = applicant;
        this.scholarship = scholarship;
        this.score = score;
        this.reason = reason;
    }

    public Applicant getApplicant() { return applicant; }
    public Scholarship getScholarship() { return scholarship; }
    public double getScore() { return score; }
    public String getReason() { return reason; }

    @Override
    public int compareTo(MatchResult other) {
        return Double.compare(other.score, this.score); // highest first
    }

}

