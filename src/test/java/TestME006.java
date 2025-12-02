/**
 * Testing Requirement: ME-006 Logging and Traceability
 * The system logs matching results and explanations for auditability.
 */

import java.util.*;

public class TestME006 {

    public static boolean testTimestampLogged() {
        try {
            Applicant applicant = new Applicant(
                    "log1",
                    "Log Tester",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("ai")),
                    3.5
            );

            Scholarship scholarship = new Scholarship(
                    "Logging Scholarship",
                    1000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            if (results.size() != 1)
                return false;

            MatchResult mr = results.get(0);

            return mr.getTimestamp() != null;
        } catch (Exception e) {
            System.out.println("Test failed for timestamp logging: " + e.getMessage());
            return false;
        }
    }

    public static boolean testExplanationLogged() {
        try {
            Applicant applicant = new Applicant(
                    "log2",
                    "Explanation Tester",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("ai")),
                    3.8
            );

            Scholarship scholarship = new Scholarship(
                    "Explanation Scholarship",
                    1500.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            if (results.size() != 1)
                return false;

            MatchResult mr = results.get(0);

            return mr.getExplanation() != null && !mr.getExplanation().isEmpty();
        } catch (Exception e) {
            System.out.println("Test failed for explanation logging: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testTimestampLogged()) {
            System.out.println("Test: Failed to log timestamp in MatchResult");
            passed = false;
        }

        if (!testExplanationLogged()) {
            System.out.println("Test: Failed to log explanation in MatchResult");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-006 logging and traceability tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-006 tests FAILED.");
        }
    }
}
