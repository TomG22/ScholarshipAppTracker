package backend;

import java.util.Arrays;

public class ScholarshipModelTest {

    public static void main(String[] args) {

        System.out.println("=== Scholarship Model Tests ===");

        Scholarship s = new Scholarship(
                "Engineering Excellence Scholarship",
                3.5,
                Arrays.asList("computer science", "electrical engineering"),
                Arrays.asList("leadership", "research")
        );

        System.out.println("\nName: " + s.getName());
        System.out.println("Min GPA: " + s.getMinGpa());
        System.out.println("Eligible Majors: " + s.getEligibleMajors());
        System.out.println("Required Keywords: " + s.getRequiredKeywords());

        
        Scholarship open = new Scholarship(
                "Open Major Scholarship",
                3.0,
                Arrays.asList(), 
                Arrays.asList("service")
        );

        System.out.println("\nOpen Scholarship:");
        System.out.println("Majors (should be empty): " + open.getEligibleMajors());

        System.out.println("\nScholarship model tests complete.");
    }
}
