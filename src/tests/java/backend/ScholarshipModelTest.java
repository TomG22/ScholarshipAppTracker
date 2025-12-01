import java.util.Set;

public class ScholarshipModelTest {

    public static void main(String[] args) {

        System.out.println("=== Scholarship Model Tests ===");

        Scholarship s = new Scholarship(
                "Engineering Excellence Scholarship",
                5000,
                3.5,
                Set.of("computer science", "electrical engineering"),
                Set.of("leadership", "research")
        );

        System.out.println(s.toString());
        
        Scholarship open = new Scholarship(
                "Open Major Scholarship",
                2000,
                3.0,
                Set.of(), 
                Set.of("service")
        );

        System.out.println(open.toString());

        if (!open.getEligibleMajors().isEmpty()) {
            System.out.println("Test: Failed to construct a scholarship with all eligible majors");
        } else {
            System.out.println("Test: All tests passed successfully!");
        }
    }
}
