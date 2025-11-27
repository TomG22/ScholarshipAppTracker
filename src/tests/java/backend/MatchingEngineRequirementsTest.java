package backend;

import java.util.Arrays;
import java.util.List;

public class MatchingEngineRequirementsTest {

    public static void main(String[] args) {
        MatchingEngine engine = new MatchingEngine();

        Applicant csGood = new Applicant(
                "Good CS",
                "Computer Science",
                3.8,
                Arrays.asList("coding", "ai"),
                Arrays.asList("leadership")
        );

        Applicant csLowGpa = new Applicant(
                "Low GPA",
                "Computer Science",
                2.5,
                Arrays.asList("coding"),
                Arrays.asList("service")
        );

        Scholarship stemHonors = new Scholarship(
                "STEM Honors Scholarship",
                3.5,
                Arrays.asList("computer science", "software engineering"),
                Arrays.asList("coding", "ai")
        );

        Scholarship anyMajorLeadership = new Scholarship(
                "Leadership Award",
                3.0,
                Arrays.asList(), // any major
                Arrays.asList("leadership")
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
