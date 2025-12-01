import java.time.LocalDateTime;

/**
 * MatchResult – represents the result of matching one Applicant to one Scholarship.
 * Implements Comparable so results can be sorted by score (highest first).
 */
public class MatchResult implements Comparable<MatchResult> {

    private final Applicant applicant;
    private final Scholarship scholarship;
    private final double score;
    private final String explanation;
    private final LocalDateTime timestamp;  // ME-006 Traceability

    public MatchResult(Applicant applicant,
                       Scholarship scholarship,
                       double score,
                       String explanation) {

        if (applicant == null) throw new IllegalArgumentException("Applicant cannot be null");
        if (scholarship == null) throw new IllegalArgumentException("Scholarship cannot be null");
        if (explanation == null) explanation = "";

        this.applicant = applicant;
        this.scholarship = scholarship;
        this.score = score;
        this.explanation = explanation;
        this.timestamp = LocalDateTime.now();  // add timestamp for auditing
    }

    public Applicant getApplicant() { return applicant; }
    public Scholarship getScholarship() { return scholarship; }
    public double getScore() { return score; }
    public String getExplanation() { return explanation; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public int compareTo(MatchResult other) {
        int cmp = Double.compare(other.score, this.score);
        if (cmp != 0) return cmp;

        cmp = this.scholarship.getName().compareTo(other.scholarship.getName());
        if (cmp != 0) return cmp;

        return this.applicant.getName().compareTo(other.applicant.getName());
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "applicant=" + applicant.getName() +
                ", scholarship=" + scholarship.getName() +
                ", score=" + score +
                ", explanation='" + explanation + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
