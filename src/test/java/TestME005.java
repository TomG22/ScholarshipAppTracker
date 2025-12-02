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

    public static void printRankedApplicantsForScholarship(Scholarship scholarship, List<Applicant> applicants) {
        try {
            MatchingEngine engine = new MatchingEngine();
            List<MatchResult> results = new ArrayList<>();

            for (Applicant applicant : applicants) {
                List<MatchResult> matches = engine.matchApplicant(applicant, List.of(scholarship));
                results.addAll(matches);
            }

            Collections.sort(results);

            System.out.println("Ranked applicants for scholarship: " + scholarship.getName());
            if (results.isEmpty()) {
                System.out.println("No qualifying applicants found.");
                return;
            }

            for (int i = 0; i < results.size(); i++) {
                MatchResult mr = results.get(i);
                System.out.println((i + 1) + ". Applicant: " + mr.getApplicant().getName() +
                        " | Score: " + mr.getScore() +
                        " | GPA: " + mr.getApplicant().getGpa() +
                        " | Major: " + mr.getApplicant().getMajor() +
                        " | Explanation: " + mr.getExplanation());
            }
        } catch (Exception e) {
            System.out.println("Error while printing ranked applicants: " + e.getMessage());
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

        Applicant applicant1 = new Applicant(
                "topApplicant",
                "Alice Johnson",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "machine learning")),
                3.95
        );

        Applicant applicant2 = new Applicant(
                "goodApplicant",
                "Bob Smith",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "research")),
                3.85
        );

        Applicant applicant3 = new Applicant(
                "decentApplicant",
                "Carol Davis",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("machine learning")),
                3.7
        );

        Applicant applicant4 = new Applicant(
                "lowApplicant",
                "David Lee",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("web development")),
                3.5
        );

        Scholarship scholarship = new Scholarship(
                "AI Research Excellence Scholarship",
                5000.0,
                3.5,
                Set.of("computer science"),
                Set.of("ai", "machine learning")
        );

        List<Applicant> applicants = List.of(applicant1, applicant2, applicant3, applicant4);
        printRankedApplicantsForScholarship(scholarship, applicants);
    }
}
