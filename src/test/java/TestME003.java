/**
 * Testing Requirement: ME-003 Keyword Matching
 * The algorithm evaluates essays and activities for required keywords
 * for each scholarship.
 */

import java.util.*;

public class TestME003 {

    public static boolean testMissingRequiredKeyword() {
        try {
            Applicant applicant = new Applicant(
                    "noKW",
                    "No Keyword",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("leadership")),
                    3.2
            );

            Scholarship scholarship = new Scholarship(
                    "STEM Research Grant",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("research")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            return results.isEmpty();
        } catch (Exception e) {
            System.out.println("Test failed for missing keyword: " + e.getMessage());
            return false;
        }
    }

    public static boolean testContainsRequiredKeyword() {
        try {
            Applicant applicant = new Applicant(
                    "hasKW",
                    "Keyword Match",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("research")),
                    3.2
            );

            Scholarship scholarship = new Scholarship(
                    "STEM Research Grant",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("research")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            if (results.size() != 1) {
                return false;
            }

            MatchResult mr = results.get(0);
            return mr.getExplanation().contains("Contains required keywords");
        } catch (Exception e) {
            System.out.println("Test failed for matching keyword: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testMissingRequiredKeyword()) {
            System.out.println("Test: Failed to reject applicant missing required keywords");
            passed = false;
        }

        if (!testContainsRequiredKeyword()) {
            System.out.println("Test: Failed to accept applicant containing required keywords");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-003 keyword matching tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-003 tests FAILED.");
        }
    }
}
