/**
 * Testing Requirement: BA-002 Data Storage and Persistence
 * The system must hold application and scholarship data in a
 * relational database with CRUD operations and unique IDs.
 */

import java.util.*;

public class TestBA002 {

    // Scholarship database tests
    public static boolean testScholarshipAddSuccess(Scholarship s) {
        return ScholarshipsDatabase.addScholarship(s);
    }

    public static boolean testScholarshipAddFailDuplicate(Scholarship s) {
        ScholarshipsDatabase.addScholarship(s);
        boolean result = ScholarshipsDatabase.addScholarship(s);
        return !result;
    }

    public static boolean testScholarshipRemoveSuccess(Scholarship s) {
        return ScholarshipsDatabase.removeScholarship(s.getID());
    }

    public static boolean testScholarshipRemoveFailNonexistent(Scholarship s) {
        return !ScholarshipsDatabase.removeScholarship(s.getID());
    }

    public static boolean testScholarshipClear() {
        ScholarshipsDatabase.clear();
        return ScholarshipsDatabase.isEmpty();
    }


    // Application database tests
    public static boolean testApplicationAddSuccess(Application app) {
        return ApplicationsDatabase.addApplication(app);
    }

    public static boolean testApplicationAddFailDuplicate(Application app) {
        ApplicationsDatabase.addApplication(app);
        boolean result = ApplicationsDatabase.addApplication(app);
        return !result;
    }

    public static boolean testApplicationRemoveSuccess(Application app) {
        return ApplicationsDatabase.removeApplication(app.getID());
    }

    public static boolean testApplicationRemoveFailNonexistent(Application app) {
        return !ApplicationsDatabase.removeApplication(app.getID());
    }

    public static boolean testApplicationClear() {
        ApplicationsDatabase.clear();
        return ApplicationsDatabase.isEmpty();
    }

    public static boolean testApplicationGet(Application app) {
        ApplicationsDatabase.clear();
        ApplicationsDatabase.addApplication(app);

        Application out = ApplicationsDatabase.getApplication(app.getID());
        return out != null &&
               out.getID().equals(app.getID()) &&
               out.getEssay().equals(app.getEssay()) &&
               out.getAuthor().getID().equals(app.getAuthor().getID());
    }

    public static boolean testApplicationGetAll(Application app) {
        ApplicationsDatabase.clear();
        ApplicationsDatabase.addApplication(app);

        Set<Application> all = ApplicationsDatabase.getAllApplications();
        return all.stream().anyMatch(a -> a.getID().equals(app.getID()));
    }

    public static void printDatabaseContents() {
        System.out.println("Database Contents");
        System.out.println();

        System.out.println("Applications Database");
        Set<Application> applications = ApplicationsDatabase.getAllApplications();
        System.out.println("Total applications: " + applications.size());
        if (applications.isEmpty()) {
            System.out.println("No applications in database.");
        } else {
            int count = 1;
            for (Application app : applications) {
                System.out.println("\nApplication #" + count);
                System.out.println("  ID: " + app.getID());
                System.out.println("  Author: " + app.getAuthor().getName());
                System.out.println("  Author NetID: " + app.getAuthor().getNetID());
                System.out.println("  Essay (first 150 chars): " + 
                    (app.getEssay().length() > 150 ? 
                        app.getEssay().substring(0, 150) + "..." : 
                        app.getEssay()));
                count++;
            }
        }
        System.out.println();
    }


    // Main execution suite
    public static void main(String[] args) {

        boolean passed = true;

        // Scholarship tests
        Scholarship dummyScholarship = new Scholarship(
                "Test Scholarship",
                3000.0,
                3.0,
                Set.of("Computer Science", "Mathematics"),
                Set.of("leadership", "community service")
        );

        if (!testScholarshipClear()) {
            System.out.println("Scholarship Test: Failed to clear DB");
            passed = false;
        }

        if (!testScholarshipAddSuccess(dummyScholarship)) {
            System.out.println("Scholarship Test: Failed to add");
            passed = false;
        }

        if (!testScholarshipAddFailDuplicate(dummyScholarship)) {
            System.out.println("Scholarship Test: Failed to reject duplicate");
            passed = false;
        }

        if (!testScholarshipRemoveSuccess(dummyScholarship)) {
            System.out.println("Scholarship Test: Failed to remove");
            passed = false;
        }

        if (!testScholarshipRemoveFailNonexistent(dummyScholarship)) {
            System.out.println("Scholarship Test: Failed to reject removal of nonexistent entry");
            passed = false;
        }

        if (!testScholarshipClear()) {
            System.out.println("Scholarship Test: Failed to clear DB");
            passed = false;
        }


        // Application tests
        Applicant dummyApplicant = new Applicant(
            "tstudent1",
            "Test Student",
            "password123",
            "Computer Science",
            java.util.Set.of("coding", "robotics", "volunteering"),
            3.6
        );

        ApplicantsDatabase.addApplicant(dummyApplicant);

        Application dummyApp = new Application(
                "This is a test essay.",
                dummyApplicant
        );

        if (!testApplicationClear()) {
            System.out.println("Application Test: Failed to clear DB");
            passed = false;
        }

        if (!testApplicationAddSuccess(dummyApp)) {
            System.out.println("Application Test: Failed to add");
            passed = false;
        }

        if (!testApplicationAddFailDuplicate(dummyApp)) {
            System.out.println("Application Test: Failed to reject duplicate");
            passed = false;
        }

        if (!testApplicationGet(dummyApp)) {
            System.out.println("Application Test: Failed to retrieve from DB");
            passed = false;
        }

        if (!testApplicationGetAll(dummyApp)) {
            System.out.println("Application Test: Failed to retrieve all entries");
            passed = false;
        }

        if (!testApplicationRemoveSuccess(dummyApp)) {
            System.out.println("Application Test: Failed to remove");
            passed = false;
        }

        if (!testApplicationRemoveFailNonexistent(dummyApp)) {
            System.out.println("Application Test: Failed to reject removal of nonexistent entry");
            passed = false;
        }

        if (!testApplicationClear()) {
            System.out.println("Application Test: Failed to clear DB");
            passed = false;
        }


        // Result
        if (passed) {
            System.out.println("Test: ALL database tests passed successfully!");
        } else {
            System.out.println("Test: One or more tests FAILED.");
        }

        // Don't clear the database here - we want to add example data
        
        Scholarship scholarship1 = new Scholarship(
                "AI Research Grant",
                5000.0,
                3.5,
                Set.of("computer science", "engineering"),
                Set.of("ai", "machine learning")
        );

        Scholarship scholarship2 = new Scholarship(
                "Community Leadership Award",
                3000.0,
                3.0,
                Set.of("any"),
                Set.of("leadership", "community service")
        );

        ScholarshipsDatabase.addScholarship(scholarship1);
        ScholarshipsDatabase.addScholarship(scholarship2);

        Applicant applicant1 = new Applicant(
                "jdoe001",
                "Jane Doe",
                "password123",
                "computer science",
                Set.of("ai", "research", "programming"),
                3.8
        );

        Applicant applicant2 = new Applicant(
                "bsmith002",
                "Bob Smith",
                "securepass456",
                "engineering",
                Set.of("leadership", "robotics", "community service"),
                3.6
        );

        Applicant applicant3 = new Applicant(
                "mchen003",
                "Michelle Chen",
                "pass789",
                "mathematics",
                Set.of("tutoring", "research", "volunteering"),
                3.9
        );

        ApplicantsDatabase.addApplicant(applicant1);
        ApplicantsDatabase.addApplicant(applicant2);
        ApplicantsDatabase.addApplicant(applicant3);

        Application application1 = new Application(
                "I am passionate about artificial intelligence and have conducted research in machine learning algorithms. My goal is to develop AI systems that benefit society.",
                applicant1
        );

        Application application2 = new Application(
                "Through my leadership roles in student organizations and volunteer work, I have learned the importance of community engagement and collaborative problem-solving.",
                applicant2
        );

        Application application3 = new Application(
                "Mathematics has always been my passion. I have tutored fellow students and conducted research in number theory. I believe education is the key to empowering communities.",
                applicant3
        );

        // Explicitly add applications to database
        ApplicationsDatabase.addApplication(application1);
        ApplicationsDatabase.addApplication(application2);
        ApplicationsDatabase.addApplication(application3);

        System.out.println("\nDatabase Persistence Example");
        printDatabaseContents();
    }
}
