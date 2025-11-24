package backend;

/**
 * Result of a single applicant–scholarship match.
 * Supports ME-005 and ME-006.
 */
public class MatchResult implements Comparable<MatchResult> {

    private final Applicant applicant;
    private final Scholarship scholarship;
    private final double score;      // 0.0 – 1.0
    private final String explanation;

    public MatchResult(Applicant applicant,
                       Scholarship scholarship,
                       double score,
                       String explanation) {
        this.applicant = applicant;
        this.scholarship = scholarship;
        this.score = score;
        this.explanation = explanation;
    }

    public Applicant getApplicant() { return applicant; }
    public Scholarship getScholarship() { return scholarship; }
    public double getScore() { return score; }
    public String getExplanation() { return explanation; }

    @Override
    public int compareTo(MatchResult other) {
        
        return Double.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "applicant=" + applicant.getName() +
                ", scholarship=" + scholarship.getName() +
                ", score=" + score +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
