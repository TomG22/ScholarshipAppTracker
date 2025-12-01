package backend;

import java.util.Arrays;
import java.util.List;

public class MatchingEngineGpaTest {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        Applicant lowGpa = new Applicant(
                "Low GPA Applicant",
                "Computer Science",
                2.0,
                Arrays.asList("coding"),
                Arrays.asList("teamwork")
        );

        Scholarship scholarship = new Scholarship(
                "STEM Scholarship",
                3.0,
                Arrays.asList("computer science"),
                Arrays.asList("coding")
        );

        List<MatchResult> results = engine.matchApplicant(lowGpa, Arrays.asList(scholarship));

        System.out.println("=== GPA Test ===");
        if (results.isEmpty()) {
            System.out.println("Correct: GPA too low → rejected");
        } else {
            System.out.println("ERROR: Applicant with low GPA should NOT match!");
        }
    }
}
