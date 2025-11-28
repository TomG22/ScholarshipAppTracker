package backend;

import java.util.Arrays;

public class ApplicantModelTest {

    public static void main(String[] args) {

        System.out.println("=== Applicant Model Tests ===");

        
        Applicant a = new Applicant(
                "Test Student",
                "Computer Science",
                3.6
        );

        System.out.println("\nName: " + a.getName());
        System.out.println("Major: " + a.getMajor());
        System.out.println("GPA: " + a.getGpa());

        // Edge case: low GPA
        Applicant lowGpa = new Applicant(
                "Low GPA",
                "Math",
                2.1
        );

        System.out.println("\nLow GPA Applicant: " + lowGpa.getName() + " (GPA=" + lowGpa.getGpa() + ")");

        System.out.println("\nApplicant model tests complete.");
    }
}
