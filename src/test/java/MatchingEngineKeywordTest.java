import java.util.Set;
import java.util.List;

public class MatchingEngineKeywordTest {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        // Applicant with ONLY "coding"
        Applicant applicant = new Applicant(
                "testNetId",
                "Keyword Tester",
                "passowrd123",
                "Computer Science",
                Set.of("coding"),
                3.6
        );

        // Scholarship that requires just "coding" -> should MATCH
        Scholarship codingOnly = new Scholarship(
                "Coding Only Scholarship",
                3.0,
                10000,
                Set.of("computer science"),
                Set.of("coding")
        );

        // Scholarship that requires "coding" AND "ai" -> should NOT MATCH
        Scholarship codingAndAi = new Scholarship(
                "Coding + AI Scholarship",
                3.0,
                10000,
                Set.of("computer science"),
                Set.of("coding", "ai")
        );

        Set<Scholarship> scholarships = Set.of(codingOnly, codingAndAi);

        System.out.println("=== Keyword Matching Test ===");
        List<MatchResult> results = engine.matchApplicant(applicant, scholarships);

        if (results.isEmpty()) {
            System.out.println("No matches returned.");
        } else {
            for (MatchResult r : results) {
                System.out.println(r);
            }
        }

        System.out.println("=== End of Keyword Matching Test ===");
    }
}

