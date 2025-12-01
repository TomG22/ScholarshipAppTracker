package backend;

import java.util.Arrays;
import java.util.List;

public class MatchingEngineMajorTest {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        Applicant applicant = new Applicant(
                "Wrong Major",
                "Business",
                3.8,
                Arrays.asList("analytics"),
                Arrays.asList("management")
        );

        Scholarship scholarship = new Scholarship(
                "Engineering Scholarship",
                3.0,
                Arrays.asList("computer science", "software engineering"),
                Arrays.asList("coding")
        );

        List<MatchResult> results = engine.matchApplicant(applicant, Arrays.asList(scholarship));

        System.out.println("=== Major Test ===");
        if (results.isEmpty()) {
            System.out.println("Correct: Wrong major → rejected");
        } else {
            System.out.println("ERROR: Applicant with wrong major should NOT match!");
        }
    }
}
