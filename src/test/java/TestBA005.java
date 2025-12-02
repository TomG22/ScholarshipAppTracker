import java.util.*;

public class TestBA005 {

    public static boolean testApplicantValidInput() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Coding"));
            Applicant validApplicant = new Applicant(
                    "netID123",
                    "John Doe",
                    "password123",
                    "Computer Science",
                    extracurriculars,
                    3.8
            );
            
            if (validApplicant == null) {
                return false;
            }

            if (!validApplicant.getNetID().equals("netID123")) {
                return false;
            }

            if (!validApplicant.getName().equals("John Doe")) {
                return false;
            }

            if (!validApplicant.getMajor().equals("Computer Science")) {
                return false;
            }

            if (validApplicant.getGpa() != 3.8) {
                return false;
            }

            if (validApplicant.getPassword() == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Test failed with valid input: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicantPasswordEncryption() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Coding"));
            Applicant validApplicant = new Applicant(
                    "netID123",
                    "John Doe",
                    "password123",
                    "Computer Science",
                    extracurriculars,
                    3.8
            );
            
            String encPassword = validApplicant.getPassword();
            if (encPassword.equals("password123")) {
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Test failed for password encryption: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicantInvalidNetID() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Coding"));
            new Applicant(
                    "",
                    "John Doe", 
                    "password123", 
                    "Computer Science", 
                    extracurriculars, 
                    3.8
            );
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("NetID cannot be null or empty.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid NetID: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicantInvalidName() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Coding"));
            new Applicant(
                    "netID123", 
                    "", 
                    "password123", 
                    "Computer Science", 
                    extracurriculars, 
                    3.8
            );
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Name cannot be null or empty.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid name: " + e.getMessage());
            return false;
        }
    }

    public static boolean testApplicantInvalidMajor() {
        try {
            Set<String> extracurriculars = new HashSet<>(Arrays.asList("Leadership", "Coding"));
            new Applicant(
                    "netID123", 
                    "John Doe", 
                    "password123", 
                    "", 
                    extracurriculars, 
                    3.8
            );
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Major cannot be null or empty.")) {
                return true;
            }
            System.out.println("Unexpected exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Test failed for invalid major: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean passed = true;

        if (!testApplicantValidInput()) {
            System.out.println("Test: Failed to process valid applicant input");
            passed = false;
        }

        if (!testApplicantPasswordEncryption()) {
            System.out.println("Test: Failed to encrypt password properly");
            passed = false;
        }

        if (!testApplicantInvalidNetID()) {
            System.out.println("Test: Failed to catch invalid NetID");
            passed = false;
        }

        if (!testApplicantInvalidName()) {
            System.out.println("Test: Failed to catch invalid name");
            passed = false;
        }

        if (!testApplicantInvalidMajor()) {
            System.out.println("Test: Failed to catch invalid major");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All applicant information storage tests passed successfully!");
        } else {
            System.out.println("Test: One or more tests FAILED.");
        }
    }
}
