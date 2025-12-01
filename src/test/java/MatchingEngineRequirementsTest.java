import java.util.List;
import java.util.Set;

public class MatchingEngineRequirementsTest {

    public static void main(String[] args) {
        MatchingEngine engine = new MatchingEngine();

        Applicant csGood = new Applicant(
                "cs01",
                "Good CS",
                "pass123",
                "Computer Science",
                Set.of(),
                3.8
        );

        Applicant csLowGpa = new Applicant(
                "cs02",
                "Low GPA",
                "pass321",
                "Computer Science",
                Set.of(),
                2.5
        );

        Scholarship stemHonors = new Scholarship(
                "STEM Honors Scholarship",
                5000.0,
                3.5,
                Set.of("computer science", "software engineering"),
                Set.of("coding", "ai")
        );

        Scholarship anyMajorLeadership = new Scholarship(
                "Leadership Award",
                1000.0,
                3.0,
                Set.of(),
                Set.of("leadership")
        );

        List<Scholarship> scholarships = List.of(stemHonors, anyMajorLeadership);

        System.out.println("=== Case 1: good CS student ===");
        printMatches(engine, csGood, scholarships);

        System.out.println("\n=== Case 2: low GPA CS student ===");
        printMatches(engine, csLowGpa, scholarships);

        System.out.println("\nMatchingEngine requirement-style tests complete.");
    }

    private static void printMatches(MatchingEngine engine,
                                     Applicant applicant,
                                     List<Scholarship> scholarships) {

        System.out.println("Applicant: " + applicant.getName()
                + " (major=" + applicant.getMajor()
                + ", gpa=" + applicant.getGpa() + ")");

        List<MatchResult> results = engine.matchApplicant(applicant, scholarships);

        if (results.isEmpty()) {
            System.out.println("  No matches returned.");
            return;
        }

        int rank = 1;
        for (MatchResult r : results) {
            System.out.printf("  #%d -> %s (score=%.3f)%n",
                    rank++,
                    r.getScholarship().getName(),
                    r.getScore());
            System.out.println("     explanation: " + r.getExplanation());
        }
    }
}
