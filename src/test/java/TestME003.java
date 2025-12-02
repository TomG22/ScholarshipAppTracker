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

    public static void printKeywordMatchingResults(List<Applicant> applicants, Scholarship scholarship) {
        try {
            MatchingEngine engine = new MatchingEngine();

            System.out.println("Keyword Matching Results for Scholarship: " + scholarship.getName());
            System.out.println("Required Keywords: " + scholarship.getRequiredKeywords());
            System.out.println();

            for (Applicant applicant : applicants) {
                System.out.println("Applicant: " + applicant.getName());
                System.out.println("  Extracurriculars: " + applicant.getExtracurriculars());
                
                List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));
                
                if (results.isEmpty()) {
                    System.out.println("  Result: NOT MATCHED");
                    System.out.println("  Reason: Missing required keywords or other eligibility criteria");
                } else {
                    MatchResult mr = results.get(0);
                    System.out.println("  Result: MATCHED");
                    System.out.println("  Score: " + String.format("%.3f", mr.getScore()));
                    System.out.println("  Explanation: " + mr.getExplanation());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error while printing keyword matching results: " + e.getMessage());
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

        Applicant applicant1 = new Applicant(
                "kwMatch1",
                "Sarah Chen",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "machine learning", "research")),
                3.8
        );

        Applicant applicant2 = new Applicant(
                "kwMatch2",
                "Michael Brown",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "robotics")),
                3.7
        );

        Applicant applicant3 = new Applicant(
                "kwNoMatch1",
                "Jessica Lee",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("web development", "databases")),
                3.6
        );

        Applicant applicant4 = new Applicant(
                "kwNoMatch2",
                "David Kim",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("cybersecurity", "networking")),
                3.5
        );

        Scholarship scholarship = new Scholarship(
                "AI Research Excellence Award",
                5000.0,
                3.5,
                Set.of("computer science"),
                Set.of("ai", "machine learning")
        );

        List<Applicant> applicants = List.of(applicant1, applicant2, applicant3, applicant4);

        System.out.println("\nKeyword Matching Example");
        printKeywordMatchingResults(applicants, scholarship);
    }
}
