/**
 * Testing Requirement: ME-005 Match Ranking Output
 * Produces a ranked list of qualified applicants for each scholarship
 * based on match scores.
 */

import java.util.*;

public class TestME005 {

    public static boolean testRankingOrder() {
        try {
            Applicant applicant = new Applicant(
                    "rankingUser",
                    "Ranking User",
                    "password123",
                    "computer science",
                    new HashSet<>(Arrays.asList("research", "ai")),
                    3.9
            );

            Scholarship highMatch = new Scholarship(
                    "High Match Scholarship",
                    3000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("ai")
            );

            Scholarship mediumMatch = new Scholarship(
                    "Medium Match Scholarship",
                    3000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("research")
            );

            Scholarship lowMatch = new Scholarship(
                    "Low Match Scholarship",
                    3000.0,
                    3.0,
                    Set.of("computer science"),
                    Set.of("nothing")
            );

            MatchingEngine engine = new MatchingEngine();

            List<Scholarship> scholarships = List.of(highMatch, mediumMatch, lowMatch);
            List<MatchResult> results = engine.matchApplicant(applicant, scholarships);

            if (results.size() != 2)
                return false;

            MatchResult first = results.get(0);
            MatchResult second = results.get(1);

            return first.getScore() >= second.getScore();
        } catch (Exception e) {
            System.out.println("Test failed for ranking output: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testRankingOrder()) {
            System.out.println("Test: Failed to correctly rank match results by score");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All ME-005 match ranking tests passed successfully!");
        } else {
            System.out.println("Test: One or more ME-005 tests FAILED.");
        }
    }
}

