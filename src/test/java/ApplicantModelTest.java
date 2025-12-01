public class ApplicantModelTest {

    public static void main(String[] args) {

        System.out.println("=== Applicant Model Tests ===");

        Applicant a = new Applicant(
                "tstudent1",
                "Test Student",
                "password123",
                "Computer Science",
                java.util.Set.of("coding", "robotics", "volunteering"),
                3.6
        );

        System.out.println(a.toString());

        Applicant lowGpa = new Applicant(
                "lgpa01",
                "Low GPA",
                "weak password",
                "Math",
                java.util.Set.of("math club", "piano"),
                2.1
        );

        System.out.println(lowGpa.toString());

        System.out.println("\nApplicant model tests complete.");
    }
}
