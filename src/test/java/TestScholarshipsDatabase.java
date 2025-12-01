import java.util.Set;

public class TestScholarshipsDatabase {

    public static boolean testScholarshipAddSuccess(Scholarship dummyScholarship) {
        return ScholarshipsDatabase.addScholarship(dummyScholarship);
    }

    public static boolean testScholarshipAddFail(Scholarship dummyScholarship) {
        ScholarshipsDatabase.addScholarship(dummyScholarship);
        boolean addResult = ScholarshipsDatabase.addScholarship(dummyScholarship);
        return !addResult;
    }

    public static boolean testScholarshipRemoveSuccess(Scholarship dummyScholarship) {
        return ScholarshipsDatabase.removeScholarship(dummyScholarship.getID());
    }

    public static boolean testScholarshipRemoveFail(Scholarship dummyScholarship) {
        return !ScholarshipsDatabase.removeScholarship(dummyScholarship.getID());
    }

    public static boolean testClear() {
        ScholarshipsDatabase.clear();
        return ScholarshipsDatabase.isEmpty();
    }

    public static void main(String[] args) {

        Scholarship dummy = new Scholarship(
                "Test Scholarship",
                3000.0,
                3.0,
                Set.of("Computer Science", "Mathematics"),
                Set.of("leadership", "community service")
        );

        boolean passed = true;

        if (!testClear()) {
            System.out.println("Test: Failed to clear the scholarship database");
            passed = false;
        }

        if (!testScholarshipAddSuccess(dummy)) {
            System.out.println("Test: Failed to add a new scholarship");
            passed = false;
        }

        if (!testScholarshipAddFail(dummy)) {
            System.out.println("Test: Failed to reject a duplicate scholarship");
            passed = false;
        }

        if (!testScholarshipRemoveSuccess(dummy)) {
            System.out.println("Test: Failed to remove an existing scholarship");
            passed = false;
        }

        if (!testScholarshipRemoveFail(dummy)) {
            System.out.println("Test: Failed to reject removal of a non-existing scholarship");
            passed = false;
        }

        if (!testClear()) {
            System.out.println("Test: Failed to clear the scholarship database");
            passed = false;
        }

        if (passed) {
            System.out.println("Test: All scholarship tests passed successfully!");
        }
    }
}
