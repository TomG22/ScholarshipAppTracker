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

    public static void printApplicationValidation(List<ApplicationData> testCases) {
        System.out.println("Application Validation Examples");
        System.out.println("Total test cases: " + testCases.size());
        System.out.println();

        for (int i = 0; i < testCases.size(); i++) {
            ApplicationData data = testCases.get(i);
            System.out.println("Test Case #" + (i + 1) + ": " + data.description);
            System.out.println("  Essay: " + (data.essay == null ? "null" : 
                (data.essay.isEmpty() ? "(empty)" : data.essay.substring(0, Math.min(50, data.essay.length())) + "...")));
            System.out.println("  Author: " + (data.author == null ? "null" : data.author.getName()));
            System.out.println("  ID: " + (data.id == null ? "null" : data.id.toString()));
            
            try {
                Application app = new Application(data.id, data.essay, data.author, 
                    Application.ApplicationStatus.IN_PROGRESS, false);
                System.out.println("  Result: VALID - Application created successfully");
                System.out.println("  Application ID: " + app.getID());
            } catch (IllegalArgumentException e) {
                System.out.println("  Result: INVALID - " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  Result: ERROR - " + e.getMessage());
            }
            System.out.println();
        }
    }

    static class ApplicationData {
        UUID id;
        String essay;
        Applicant author;
        String description;

        ApplicationData(UUID id, String essay, Applicant author, String description) {
            this.id = id;
            this.essay = essay;
            this.author = author;
            this.description = description;
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

        Applicant validApplicant1 = new Applicant(
                "alice001",
                "Alice Johnson",
                "password123",
                "Computer Science",
                new HashSet<>(Arrays.asList("research", "ai", "leadership")),
                3.9
        );

        Applicant validApplicant2 = new Applicant(
                "bob002",
                "Bob Martinez",
                "securepass456",
                "Engineering",
                new HashSet<>(Arrays.asList("robotics", "design")),
                3.7
        );

        List<ApplicationData> testCases = new ArrayList<>();
        
        testCases.add(new ApplicationData(
                UUID.randomUUID(),
                "I am passionate about artificial intelligence and have spent the last two years researching machine learning algorithms. My goal is to develop ethical AI systems.",
                validApplicant1,
                "Valid application with complete data"
        ));

        testCases.add(new ApplicationData(
                UUID.randomUUID(),
                "Through my engineering projects and robotics competitions, I have developed strong problem-solving skills and a commitment to innovation.",
                validApplicant2,
                "Valid application from engineering student"
        ));

        testCases.add(new ApplicationData(
                null,
                "This essay has a valid ID.",
                validApplicant1,
                "Invalid: Null ID"
        ));

        testCases.add(new ApplicationData(
                UUID.randomUUID(),
                "",
                validApplicant1,
                "Invalid: Empty essay"
        ));

        testCases.add(new ApplicationData(
                UUID.randomUUID(),
                null,
                validApplicant1,
                "Invalid: Null essay"
        ));

        testCases.add(new ApplicationData(
                UUID.randomUUID(),
                "This is a valid essay but no author.",
                null,
                "Invalid: Null author"
        ));

        System.out.println("\nApplication Processing and Validation Examples");
        printApplicationValidation(testCases);
    }
}
