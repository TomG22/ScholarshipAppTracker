/**
 * Testing Requirement: ME-002 Major Compatibility
 * Ensures applicants are matched only to scholarships compatible
 * with their major.
 */

import java.util.*;

public class TestME002 {

    public static boolean testMajorNotEligible() {
        try {
            Applicant applicant = new Applicant(
                    "sam123",
                    "Sam Student",
                    "password",
                    "biology",
                    new HashSet<>(Arrays.asList("volunteering")),
                    3.6
            );

            Scholarship scholarship = new Scholarship(
                    "Engineering Scholars",
                    3000.0,
                    3.0,
                    Set.of("computer science", "mechanical engineering"),
                    Set.of("research")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            return results.isEmpty();
        } catch (Exception e) {
            System.out.println("Test failed for non-eligible major: " + e.getMessage());
            return false;
        }
    }

    public static boolean testMajorEligible() {
        try {
            Applicant applicant = new Applicant(
                    "kate123",
                    "Kate Student",
                    "password",
                    "computer science",
                    new HashSet<>(Arrays.asList("research")),
                    3.8
            );

            Scholarship scholarship = new Scholarship(
                    "Engineering Scholars",
                    3000.0,
                    3.0,
                    Set.of("computer science", "mechanical engineering"),
                    Set.of("research")
            );

            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));

            if (results.size() != 1) {
                return false;
            }

            MatchResult mr = results.get(0);
            return mr.getExplanation().contains("Eligible major");
        } catch (Exception e) {
            System.out.println("Test failed for eligible major: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testMajorNotEligible()) {
            System.out.println("Test: Failed to reject applicant with non-eligible major");
            passed = false;
        }

        if (!testMajorEligible()) {
            System.out.println("Test: Failed to accept applicant with eligible major");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-002 major compatibility tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-002 tests FAILED.");
        }
    }
}
