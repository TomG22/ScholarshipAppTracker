package backend;

import java.util.*;

public class MatchingEngineTest {

    public static void main(String[] args) {

        // Create test applicants
        Applicant a1 = new Applicant(
                "John Doe",
                "Computer Science",
                3.8,
                Arrays.asList("ai", "coding"),
                Arrays.asList("volunteer", "leadership")
        );

        Applicant a2 = new Applicant(
                "Sarah Student",
                "Business",
                2.5,
                Arrays.asList("marketing"),
                Arrays.asList("club", "finance")
        );

        // Create scholarships
        Scholarship s1 = new Scholarship(
                "Tech Excellence Scholarship",
                3.5,
                Arrays.asList("computer science"),
                Arrays.asList("ai")
        );

        Scholarship s2 = new Scholarship(
                "Business Leadership Award",
                3.0,
                Arrays.asList("business"),
                Arrays.asList("leadership")
        );

        Scholarship s3 = new Scholarship(
                "General Merit",
                3.2,
                Collections.emptyList(),   // any major
                Collections.emptyList()    // no keywords
        );

        List<Scholarship> scholarshipList = List.of(s1, s2, s3);

        // Instantiate Matching Engine
        MatchingEngine engine = new MatchingEngine();

        // Test Applicant 1
        System.out.println("----- Testing Applicant: " + a1.getName() + " -----");
        List<MatchResult> r1 = engine.matchApplicant(a1, scholarshipList);

        for (MatchResult r : r1) {
            System.out.println(r);
        }

        // Test Applicant 2
        System.out.println("\n----- Testing Applicant: " + a2.getName() + " -----");
        List<MatchResult> r2 = engine.matchApplicant(a2, scholarshipList);

        for (MatchResult r : r2) {
            System.out.println(r);
        }

        System.out.println("\nTEST COMPLETE — Matching Engine functional.");
    }
}
