package backend;

import java.util.Arrays;

public class ApplicantModelTest {

    public static void main(String[] args) {

        System.out.println("=== Applicant Model Tests ===");

        
        Applicant a = new Applicant(
                "Test Student",
                "Computer Science",
                3.6,
                Arrays.asList("coding", "ai"),
                Arrays.asList("leadership", "volunteer")
        );

        System.out.println("\nName: " + a.getName());
        System.out.println("Major: " + a.getMajor());
        System.out.println("GPA: " + a.getGpa());
        System.out.println("Skills: " + a.getSkills());
        System.out.println("Tags: " + a.getTags());

        // Edge case: low GPA
        Applicant lowGpa = new Applicant(
                "Low GPA",
                "Math",
                2.1,
                Arrays.asList("calculus"),
                Arrays.asList("research")
        );

        System.out.println("\nLow GPA Applicant: " + lowGpa.getName() + " (GPA=" + lowGpa.getGpa() + ")");

        System.out.println("\nApplicant model tests complete.");
    }
}
