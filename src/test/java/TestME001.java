/**
 * Testing Requirement: ME-001 Eligibility Filtering
 * The system shall filter out applicants whose GPA is below the
 * scholarship’s minimum GPA requirement.
 */

import java.util.*;

public class TestME001 {

    public static boolean testBelowMinGpaRejected() {
        try {
            Applicant applicant = new Applicant(
                    "alice",
                    "Alice Example",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("robotics")),
                    2.5
            );

            Scholarship scholarship = new Scholarship(
                    "Tech Scholars",
                    5000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("robotics")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            return results.isEmpty();
        } catch (Exception e) {
            System.out.println("Test failed for GPA below minimum: " + e.getMessage());
            return false;
        }
    }

    public static boolean testMeetsMinGpaAccepted() {
        try {
            Applicant applicant = new Applicant(
                    "bob",
                    "Bob Example",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("robotics")),
                    3.5
            );

            Scholarship scholarship = new Scholarship(
                    "Tech Scholars",
                    5000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("robotics")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            if (results.size() != 1) {
                return false;
            }

            MatchResult mr = results.get(0);
            return mr.getExplanation().contains("Meets GPA requirement");
        } catch (Exception e) {
            System.out.println("Test failed for GPA meeting minimum: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testBelowMinGpaRejected()) {
            System.out.println("Test: Failed to reject applicant below minimum GPA");
            passed = false;
        }

        if (!testMeetsMinGpaAccepted()) {
            System.out.println("Test: Failed to accept applicant meeting minimum GPA");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-001 GPA filtering tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-001 GPA filtering tests FAILED.");
        }
    }
}
