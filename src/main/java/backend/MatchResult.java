package backend;

/**
 * Represents the result of matching one Applicant to one Scholarship.
 *
 * Supports:
 *  - ME-005 Match Ranking Output (implements Comparable for sorting)
 *  - ME-006 Logging and Traceability (reason field)
 */
public class MatchResult implements Comparable<MatchResult> {

    private final Applicant applicant;
    private final Scholarship scholarship;
    private final double score;   // 0.0 – 1.0
    private final String reason;  // explanation text

    public MatchResult(Applicant applicant,
                       Scholarship scholarship,
                       double score,
                       String reason) {

        if (applicant == null) {
            throw new IllegalArgumentException("applicant cannot be null");
        }
        if (scholarship == null) {
            throw new IllegalArgumentException("scholarship cannot be null");
        }
        if (score < 0.0 || score > 1.0) {
            throw new IllegalArgumentException("score must be between 0.0 and 1.0");
        }

        this.applicant = applicant;
        this.scholarship = scholarship;
        this.score = score;
        this.reason = (reason == null) ? "" : reason;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public double getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

   
    @Override
    public int compareTo(MatchResult other) {
        return Double.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "applicant='" + applicant.getName() + '\'' +
                ", scholarship='" + scholarship.getName() + '\'' +
                ", score=" + String.format("%.3f", score) +
                ", reason='" + reason + '\'' +
                '}';
    }
}
