/**
 * Testing Requirement: ME-012 Top Three Award Selection
 * Outputs the top three most qualified candidates for each scholarship.
 */

import java.util.*;

public class TestME012 {

    public static boolean testTopThreeReturned() {
        try {
            Applicant applicant = new Applicant(
                    "topUser",
                    "Top User",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("ai", "research", "ml")),
                    3.9
                    );

            Scholarship s1 = new Scholarship(
                    "Scholarship A",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
                    );

            Scholarship s2 = new Scholarship(
                    "Scholarship B",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("research")
                    );

            Scholarship s3 = new Scholarship(
                    "Scholarship C",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ml")
                    );

            Scholarship s4 = new Scholarship(
                    "Scholarship D",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("innovation")
                    );

            MatchingEngine engine = new MatchingEngine();
            List<Scholarship> scholarships = List.of(s1, s2, s3, s4);
            List<MatchResult> results = engine.topMatches(applicant, scholarships, 3);

            return results.get(0).getScore() >= results.get(1).getScore()
                && results.get(1).getScore() >= results.get(2).getScore();
        } catch (Exception e) {
            System.out.println("Test failed for top three matches: " + e.getMessage());
            return false;
        }
    }

    public static boolean testFewerThanThreeReturnedWhenApplicable() {
        try {
            Applicant applicant = new Applicant(
                    "twoUser",
                    "Two User",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("ai")),
                    3.6
                    );

            Scholarship s1 = new Scholarship(
                    "Scholarship A",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
                    );

            Scholarship s2 = new Scholarship(
                    "Scholarship B",
                    2000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
                    );

            MatchingEngine engine = new MatchingEngine();
            List<Scholarship> scholarships = List.of(s1, s2);
            List<MatchResult> results = engine.topMatches(applicant, scholarships, 3);

            return results.size() == 2;
        } catch (Exception e) {
            System.out.println("Test failed with fewer than three matches: " + e.getMessage());
            return false;
        }
    }

    public static void printTopThreeMatches(Applicant applicant, List<Scholarship> scholarships) {
        try {
            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = engine.topMatches(applicant, scholarships, 3);

            System.out.println("Top matches for applicant: " + applicant.getName());
            if (results.isEmpty()) {
                System.out.println("No qualifying matches found.");
                return;
            }

            for (int i = 0; i < results.size(); i++) {
                MatchResult mr = results.get(i);
                System.out.println((i + 1) + ". Scholarship: " + mr.getScholarship().getName() +
                        " | Score: " + String.format("%.3f", mr.getScore()) +
                        " | Explanation: " + mr.getExplanation());

            }
        } catch (Exception e) {
            System.out.println("Error while printing top matches: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testTopThreeReturned()) {
            System.out.println("Test: Failed to correctly return top three matches");
            passed = false;
        }

        if (!testFewerThanThreeReturnedWhenApplicable()) {
            System.out.println("Test: Failed to handle fewer than three possible matches");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-012 top three selection tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-012 tests FAILED.");
        }

        Set<String> keywords = new HashSet<>(Arrays.asList("ai", "research", "ml"));
        Applicant applicant = new Applicant(
                "topUser",
                "Top User",
                "password123",
                "computer science",
                keywords,
                3.9
                );

        Scholarship s1 = new Scholarship(
                "Scholarship A",
                2000.0,
                3.0,
                Set.of("computer science"),
                Set.of("ai")
                );

        Scholarship s2 = new Scholarship(
                "Scholarship B",
                2000.0,
                3.0,
                Set.of("computer science"),
                Set.of("research")
                );

        Scholarship s3 = new Scholarship(
                "Scholarship C",
                2000.0,
                3.0,
                Set.of("computer science"),
                Set.of("ml")
                );

        Scholarship s4 = new Scholarship(
                "Scholarship D",
                2000.0,
                3.0,
                Set.of("computer science"),
                Set.of("innovation")
                );

        List<Scholarship> scholarships = List.of(s1, s2, s3, s4);
        printTopThreeMatches(applicant, scholarships);
    }
}
