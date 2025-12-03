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

    public static void printMajorCompatibilityResults(List<Applicant> applicants, Scholarship scholarship) {
        try {
            MatchingEngine engine = new MatchingEngine();

            System.out.println("Major Compatibility Results for Scholarship: " + scholarship.getName());
            System.out.println("Eligible Majors: " + scholarship.getEligibleMajors());
            System.out.println("Minimum GPA: " + scholarship.getMinGpa());
            System.out.println();

            for (Applicant applicant : applicants) {
                System.out.println("Applicant: " + applicant.getName());
                System.out.println("  Major: " + applicant.getMajor());
                System.out.println("  GPA: " + applicant.getGpa());
                System.out.println("  Extracurriculars: " + applicant.getExtracurriculars());
                
                List<MatchResult> results = engine.matchApplicant(applicant, List.of(scholarship));
                
                if (results.isEmpty()) {
                    System.out.println("  Result: REJECTED");
                    if (applicant.getGpa() < scholarship.getMinGpa()) {
                        System.out.println("  Reason: GPA below minimum requirement");
                    } else if (!scholarship.getEligibleMajors().isEmpty() && 
                               !scholarship.getEligibleMajors().contains(applicant.getMajor().toLowerCase())) {
                        System.out.println("  Reason: Major not eligible for this scholarship");
                    } else {
                        System.out.println("  Reason: Does not meet other requirements");
                    }
                } else {
                    MatchResult mr = results.get(0);
                    System.out.println("  Result: ACCEPTED");
                    System.out.println("  Match Score: " + String.format("%.3f", mr.getScore()));
                    System.out.println("  Explanation: " + mr.getExplanation());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error while printing major compatibility results: " + e.getMessage());
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

        Applicant applicant1 = new Applicant(
                "cs001",
                "Alexandra Chen",
                "password123",
                "computer science",
                new HashSet<>(Arrays.asList("programming", "ai")),
                3.8
        );

        Applicant applicant2 = new Applicant(
                "eng002",
                "Marcus Johnson",
                "password123",
                "mechanical engineering",
                new HashSet<>(Arrays.asList("design", "robotics")),
                3.7
        );

        Applicant applicant3 = new Applicant(
                "ee003",
                "Priya Patel",
                "password123",
                "electrical engineering",
                new HashSet<>(Arrays.asList("circuits", "embedded systems")),
                3.9
        );

        Applicant applicant4 = new Applicant(
                "bio004",
                "David Kim",
                "password123",
                "biology",
                new HashSet<>(Arrays.asList("research", "lab work")),
                3.6
        );

        Applicant applicant5 = new Applicant(
                "math005",
                "Sarah Martinez",
                "password123",
                "mathematics",
                new HashSet<>(Arrays.asList("tutoring", "problem solving")),
                3.85
        );

        Scholarship scholarship = new Scholarship(
                "STEM Engineering Excellence Scholarship",
                5000.0,
                3.5,
                Set.of("computer science", "mechanical engineering", "electrical engineering"),
                Set.of("programming", "design", "robotics", "circuits", "embedded systems", "ai")
        );

        List<Applicant> applicants = List.of(applicant1, applicant2, applicant3, applicant4, applicant5);

        System.out.println("\nMajor Compatibility Filtering Example");
        printMajorCompatibilityResults(applicants, scholarship);
    }
}
