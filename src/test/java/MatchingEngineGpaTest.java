import java.util.List;
import java.util.Set;

public class MatchingEngineGpaTest {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        // Create low GPA applicant using correct Applicant constructor
        Applicant lowGpa = new Applicant(
                "lgpa01",
                "Low GPA Applicant",
                "password123",
                "Computer Science",
                Set.of("coding"),
                2.0
        );

        // Create a scholarship requiring a 3.0 GPA
        Scholarship scholarship = new Scholarship(
                "STEM Scholarship",
                5000,
                3.0,
                Set.of("computer science"),
                Set.of("coding")
        );

        List<MatchResult> results = engine.matchApplicant(
                lowGpa,
                List.of(scholarship)
        );

        System.out.println("=== GPA Test ===");
        if (results.isEmpty()) {
            System.out.println("Correct: GPA too low → rejected");
        } else {
            System.out.println("ERROR: Applicant with low GPA should NOT match!");
        }
    }
}

