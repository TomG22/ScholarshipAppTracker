package backend;

/**
 * Result of matching an Applicant to a Scholarship.
 * Implements Comparable so we can sort by score.
 */
public class MatchResult implements Comparable<MatchResult> {

    private final Applicant applicant;
    private final Scholarship scholarship;
    private final double score;
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

    public Applicant getApplicant() {
        return applicant;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public double getScore() {
        return score;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public int compareTo(MatchResult other) {
        // Sort by score descending; tie-break on scholarship then applicant name
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
                '}';
    }
}
