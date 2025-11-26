package backend;

import java.util.*;


public class MatchingEngineTest {

    public static void main(String[] args) {

        // --- Create test applicants using real team 8 names ---

        Applicant a1 = new Applicant(
                "Samiur R",
                "Computer Science",
                3.9,
                Arrays.asList("ai", "machine learning", "coding"),
                Arrays.asList("leadership", "volunteer")
        );

        Applicant a2 = new Applicant(
                "Tom G",
                "Software Engineering",
                3.4,
                Arrays.asList("java", "backend"),
                Arrays.asList("teamwork", "research")
        );

        Applicant a3 = new Applicant(
                "Lainey W",
                "Information Science",
                3.7,
                Arrays.asList("design", "ux"),
                Arrays.asList("service", "organization")
        );

        Applicant a4 = new Applicant(
                "Sarah J",
                "Business",
                3.2,
                Arrays.asList("marketing", "analytics"),
                Arrays.asList("finance", "management")
        );

        // --- Create scholarships ---
        Scholarship s1 = new Scholarship(
                "STEM Excellence Scholarship",
                3.5,
                Arrays.asList("computer science", "software engineering"),
                Arrays.asList("coding")
        );

        Scholarship s2 = new Scholarship(
                "Leadership Award",
                3.0,
                Collections.emptyList(), // any major
                Arrays.asList("leadership")
        );

        Scholarship s3 = new Scholarship(
                "Business Merit Scholarship",
                3.0,
                Arrays.asList("business"),
                Arrays.asList("analytics")
        );

        List<Scholarship> scholarshipList = List.of(s1, s2, s3);

        MatchingEngine engine = new MatchingEngine();

      
        testApplicant(engine, a1, scholarshipList);
        testApplicant(engine, a2, scholarshipList);
        testApplicant(engine, a3, scholarshipList);
        testApplicant(engine, a4, scholarshipList);

        System.out.println("\nTEST COMPLETE — Matching Engine functional.");
    }


    private static void testApplicant(MatchingEngine engine,
                                      Applicant applicant,
                                      List<Scholarship> scholarships) {

        System.out.println("\n----- Testing Applicant: " + applicant.getName() + " -----");

        List<MatchResult> results = engine.matchApplicant(applicant, scholarships);

        if (results.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            for (MatchResult r : results) {
                System.out.println(r);
            }
        }
    }
}
