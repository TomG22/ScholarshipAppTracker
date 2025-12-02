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

    public static void printMatchLog(Applicant applicant, List<Scholarship> scholarships) {
        try {
            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, scholarships);

            System.out.println("Match log for applicant: " + applicant.getName());
            System.out.println("Total matches found: " + results.size());
            System.out.println();

            if (results.isEmpty()) {
                System.out.println("No qualifying matches found.");
                return;
            }

            for (int i = 0; i < results.size(); i++) {
                MatchResult mr = results.get(i);
                System.out.println("Match #" + (i + 1));
                System.out.println("  Scholarship: " + mr.getScholarship().getName());
                System.out.println("  Score: " + String.format("%.3f", mr.getScore()));
                System.out.println("  Timestamp: " + mr.getTimestamp());
                System.out.println("  Explanation: " + mr.getExplanation());
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error while printing match log: " + e.getMessage());
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

        Applicant applicant1 = new Applicant(
                "auditUser1",
                "Emma Wilson",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "research")),
                3.85
        );

        Applicant applicant2 = new Applicant(
                "auditUser2",
                "Frank Martinez",
                "password123",
                "engineering",
                new HashSet<>(Arrays.asList("robotics")),
                3.2
        );

        Scholarship s1 = new Scholarship(
                "AI Innovation Scholarship",
                3000.0,
                3.5,
                Set.of("computer science"),
                Set.of("ai")
        );

        Scholarship s2 = new Scholarship(
                "Research Excellence Award",
                2500.0,
                3.0,
                Set.of("computer science", "engineering"),
                Set.of("research")
        );

        Scholarship s3 = new Scholarship(
                "Engineering Leadership Grant",
                2000.0,
                3.0,
                Set.of("engineering"),
                Set.of("robotics")
        );

        Scholarship s4 = new Scholarship(
                "STEM Achievement Scholarship",
                1500.0,
                3.0,
                Set.of("computer science", "engineering"),
                Set.of("innovation")
        );

        List<Scholarship> scholarships = List.of(s1, s2, s3, s4);

        System.out.println("\nAudit Log Example 1");
        printMatchLog(applicant1, scholarships);

        System.out.println("\nAudit Log Example 2");
        printMatchLog(applicant2, scholarships);
    }
}
