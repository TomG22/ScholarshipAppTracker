/**
 * Testing Requirement: BA-004 Processing Applications
 * The backend shall process and validate submitted application data
 * against required form fields.
 */

import java.util.*;

public class TestBA004 {

    public static boolean testApplicationValidInput() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Volunteering"));
            Applicant validApplicant = new Applicant("john_doe", "John Doe", "password123", "Computer Science", extracurriculars, 3.8);

            Application validApplication = new Application(
                    UUID.randomUUID(),
                    "This is a valid essay.",
                    validApplicant,
                    Application.ApplicationStatus.IN_PROGRESS,
                    false
            );
            return validApplication != null;
        } catch (Exception e) {
            System.out.println("Test failed with valid input: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicationInvalidId() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Volunteering"));
            Applicant validApplicant = new Applicant("john_doe", "John Doe", "password123", "Computer Science", extracurriculars, 3.8);

            new Application(null, "This is a valid essay.", validApplicant, Application.ApplicationStatus.IN_PROGRESS, false);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("ID cannot be null.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid ID: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicationInvalidEssay() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Volunteering"));
            Applicant validApplicant = new Applicant("john_doe", "John Doe", "password123", "Computer Science", extracurriculars, 3.8);

            new Application(UUID.randomUUID(), "", validApplicant, Application.ApplicationStatus.IN_PROGRESS, false);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Essay cannot be null or empty.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid essay: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicationInvalidAuthor() {
        try {
            new Application(UUID.randomUUID(), "This is a valid essay.", null, Application.ApplicationStatus.IN_PROGRESS, false);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Author cannot be null.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid author: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testApplicationValidInput()) {
            System.out.println("Test: Failed to process valid application");
            passed = false;
        }

        if (!testApplicationInvalidId()) {
            System.out.println("Test: Failed to catch invalid ID");
            passed = false;
        }

        if (!testApplicationInvalidEssay()) {
            System.out.println("Test: Failed to catch invalid essay");
            passed = false;
        }

        if (!testApplicationInvalidAuthor()) {
            System.out.println("Test: Failed to catch invalid author");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All application validation tests passed successfully!");
        } else {
            System.out.println("Test: One or more tests FAILED.");
        }
    }
}
