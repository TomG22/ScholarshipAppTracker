import java.util.Set;
import java.util.List;

public class MatchingEngineMajorTest {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        Applicant applicant = new Applicant(
                "sampleNetId",
                "Wrong Major",
                "badpassword",
                "Business",
                Set.of("management"),
                3.8
        );

        Scholarship scholarship = new Scholarship(
                "Engineering Scholarship",
                3.0,
                8000,
                Set.of("computer science", "software engineering"),
                Set.of("coding")
        );

        List<MatchResult> results = engine.matchApplicant(applicant, Set.of(scholarship));

        System.out.println("=== Major Test ===");
        if (results.isEmpty()) {
            System.out.println("Correct: Wrong major → rejected");
        } else {
            System.out.println("ERROR: Applicant with wrong major should NOT match!");
        }
    }
}
