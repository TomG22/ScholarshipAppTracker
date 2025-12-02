import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TestBA001 {

    private File getFileFromResources(String fileName) {
        File file = new File("resources/" + fileName);
        
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found in resources: " + fileName);
        }

        return file;
    }

    public static boolean testParseValidScholarships() {
        try {
            TestBA001 test = new TestBA001();
            File tempFile = test.getFileFromResources("valid_scholarships.json");
            Parser parser = new Parser();
            Set<Scholarship> scholarships = parser.parseScholarships(tempFile);

            if (scholarships.size() != 2) {
                System.out.println("Test failed: Expected 2 scholarships, found " + scholarships.size());
                return false;
            }

            for (Scholarship scholarship : scholarships) {
                if (scholarship.getAwardAmount() <= 0 || scholarship.getMinGpa() <= 0) {
                    System.out.println("Test failed: Invalid awardAmount or minGpa");
                    return false;
                }
            }

            System.out.println("Test passed: Valid scholarships parsed correctly.");
            return true;

        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
            return false;
        }
    }

    public static boolean testParseInvalidScholarships() {
        try {
            TestBA001 test = new TestBA001();
            File tempFile = test.getFileFromResources("invalid_scholarships.json");
            Parser parser = new Parser();
            Set<Scholarship> scholarships = parser.parseScholarships(tempFile);

            System.out.println("Test failed: Invalid scholarship data should not be parsed.");
            return false;

        } catch (Exception e) {
            if (e.getMessage().contains("Invalid awardAmount format")) {
                System.out.println("Test passed: Caught invalid scholarship data correctly.");
                return true;
            }
            System.out.println("Test failed with unexpected exception: " + e.getMessage());
            return false;
        }
    }

    public static boolean testParseValidApplications() {
        try {
            TestBA001 test = new TestBA001();
            File tempFile = test.getFileFromResources("valid_applications.json");
            Parser parser = new Parser();
            Set<Application> applications = parser.parseApplications(tempFile);

            if (applications.size() != 1) {
                System.out.println("Test failed: Expected 1 application, found " + applications.size());
                return false;
            }

            Application application = applications.iterator().next();
            if (application.getEssay() == null || application.getAuthor() == null) {
                System.out.println("Test failed: Invalid application data");
                return false;
            }

            System.out.println("Test passed: Valid applications parsed correctly.");
            return true;

        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
            return false;
        }
    }

    public static boolean testParseInvalidApplications() {
        try {
            TestBA001 test = new TestBA001();
            File tempFile = test.getFileFromResources("invalid_applications.json");
            Parser parser = new Parser();
            Set<Application> applications = parser.parseApplications(tempFile);

            System.out.println("Test failed: Invalid application data (missing author) should not be parsed.");
            return false;

        } catch (Exception e) {
            if (e.getMessage().contains("Author cannot be null.")) {
                System.out.println("Test passed: Caught invalid application data correctly.");
                return true;
            }
            System.out.println("Test failed with unexpected exception: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testParseValidScholarships()) {
            System.out.println("Test: Failed to parse valid scholarships.");
            passed = false;
        }

        if (!testParseInvalidScholarships()) {
            System.out.println("Test: Failed to catch invalid scholarship data.");
            passed = false;
        }

        if (!testParseValidApplications()) {
            System.out.println("Test: Failed to parse valid applications.");
            passed = false;
        }

        if (!testParseInvalidApplications()) {
            System.out.println("Test: Failed to catch invalid application data.");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All tests passed successfully!");
        } else {
            System.out.println("Test: One or more tests FAILED.");
        }
    }
}
