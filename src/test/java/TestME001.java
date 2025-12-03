/**
 * Testing Requirement: ME-001 Eligibility Filtering
 * The system shall filter out applicants whose GPA is below the
 * scholarship's minimum GPA requirement.
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

    public static void printGpaFilteringResults(List<Applicant> applicants, Scholarship scholarship) {
        try {
            MatchingEngine engine = new MatchingEngine();

            System.out.println("GPA Filtering Results for Scholarship: " + scholarship.getName());
            System.out.println("Minimum GPA Requirement: " + scholarship.getMinGpa());
            System.out.println();

            for (Applicant applicant : applicants) {
                System.out.println("Applicant: " + applicant.getName());
                System.out.println("  GPA: " + applicant.getGpa());
                System.out.println("  Major: " + applicant.getMajor());
                System.out.println("  Extracurriculars: " + applicant.getExtracurriculars());
                
                List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));
                
                if (results.isEmpty()) {
                    System.out.println("  Result: REJECTED");
                    System.out.println("  Reason: GPA below minimum requirement (" + applicant.getGpa() + " < " + scholarship.getMinGpa() + ")");
                } else {
                    MatchResult mr = results.get(0);
                    System.out.println("  Result: ACCEPTED");
                    System.out.println("  Match Score: " + mr.getScore());
                    System.out.println("  Explanation: " + mr.getExplanation());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error while printing GPA filtering results: " + e.getMessage());
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

        Applicant applicant1 = new Applicant(
                "top001",
                "Emma Thompson",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("ai", "research")),
                3.9
        );

        Applicant applicant2 = new Applicant(
                "good002",
                "James Wilson",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("robotics", "programming")),
                3.5
        );

        Applicant applicant3 = new Applicant(
                "border003",
                "Sophia Lee",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("web development")),
                3.0
        );

        Applicant applicant4 = new Applicant(
                "below004",
                "Michael Chen",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("databases", "networking")),
                2.8
        );

        Applicant applicant5 = new Applicant(
                "low005",
                "Isabella Rodriguez",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("cybersecurity")),
                2.5
        );

        Scholarship scholarship = new Scholarship(
                "Computer Science Excellence Award",
                5000.0,
                3.0,
                Set.of("computer science"),
                Set.of("programming", "ai", "robotics", "web development", "databases", "networking", "cybersecurity")
        );

        List<Applicant> applicants = List.of(applicant1, applicant2, applicant3, applicant4, applicant5);

        System.out.println("\nGPA Eligibility Filtering Example");
        printGpaFilteringResults(applicants, scholarship);
    }
}
