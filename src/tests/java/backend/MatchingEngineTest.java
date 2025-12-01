import java.util.*;

public class MatchingEngineTest {

    public static void main(String[] args) {

        Applicant a1 = new Applicant(
                "sr01",
                "Samiur R",
                "pass1",
                "Computer Science",
                3.9
        );

        Applicant a2 = new Applicant(
                "tg01",
                "Tom G",
                "pass2",
                "Software Engineering",
                3.4
        );

        Applicant a3 = new Applicant(
                "lw01",
                "Lainey W",
                "pass3",
                "Information Science",
                3.7
        );

        Applicant a4 = new Applicant(
                "sj01",
                "Sarah J",
                "pass4",
                "Business",
                3.2
        );

        Scholarship s1 = new Scholarship(
                "STEM Excellence Scholarship",
                4000.0,
                3.5,
                Set.of("computer science", "software engineering"),
                Set.of("coding")
        );

        Scholarship s2 = new Scholarship(
                "Leadership Award",
                1500.0,
                3.0,
                Set.of(),
                Set.of("leadership")
        );

        Scholarship s3 = new Scholarship(
                "Business Merit Scholarship",
                2500.0,
                3.0,
                Set.of("business"),
                Set.of("analytics")
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
