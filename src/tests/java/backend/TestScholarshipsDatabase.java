package backend;

import java.util.Arrays;
import java.util.Collections;

public class TestScholarshipsDatabase {

    public static boolean testScholarshipAddSuccess(Scholarship dummyScholarship) {
        return ScholarshipsDatabase.addScholarship(dummyScholarship);
    }

    public static boolean testScholarshipAddFail(Scholarship dummyScholarship) {
        // First add (should succeed)
        ScholarshipsDatabase.addScholarship(dummyScholarship);

        // Second add (should fail — duplicate)
        boolean addResult = ScholarshipsDatabase.addScholarship(dummyScholarship);
        return !addResult;
    }

    public static boolean testScholarshipRemoveSuccess(Scholarship dummyScholarship) {
        return ScholarshipsDatabase.removeScholarship(dummyScholarship.getName());
    }

    public static boolean testScholarshipRemoveFail(Scholarship dummyScholarship) {
        return !ScholarshipsDatabase.removeScholarship(dummyScholarship.getName());
    }

    public static boolean testClear() {
        ScholarshipsDatabase.clear();
        return ScholarshipsDatabase.isEmpty();
    }

    public static void main(String[] args) {

        // Dummy scholarship consistent with your constructor
        Scholarship dummy = new Scholarship(
                "Test Scholarship",
                3.0,
                Arrays.asList("Computer Science", "Mathematics"),
                Arrays.asList("leadership", "community service")
        );

        boolean passed = true;

        // Clear DB first
        if (!testClear()) {
            System.out.println("Test: Failed to clear the scholarship database");
            passed = false;
        }

        // Add success
        if (!testScholarshipAddSuccess(dummy)) {
            System.out.println("Test: Failed to add a new scholarship");
            passed = false;
        }

        // Add duplicate fail
        if (!testScholarshipAddFail(dummy)) {
            System.out.println("Test: Failed to reject a duplicate scholarship");
            passed = false;
        }

        // Remove success
        if (!testScholarshipRemoveSuccess(dummy)) {
            System.out.println("Test: Failed to remove an existing scholarship");
            passed = false;
        }

        // Remove fail (non-existent)
        if (!testScholarshipRemoveFail(dummy)) {
            System.out.println("Test: Failed to reject removal of a non-existing scholarship");
            passed = false;
        }

        // Test clear again
        if (!testClear()) {
            System.out.println("Test: Failed to clear the scholarship database");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All scholarship tests passed successfully!");
        }
    }
}
