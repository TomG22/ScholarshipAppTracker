public class ApplicantModelTest {

    public static void main(String[] args) {

        System.out.println("=== Applicant Model Tests ===");

        Applicant a = new Applicant(
                "tstudent1",
                "Test Student",
                "password123",
                "Computer Science",
                3.6
        );

        System.out.println("\nName: " + a.getName());
        System.out.println("Major: " + a.getMajor());
        System.out.println("GPA: " + a.getGpa());

        Applicant lowGpa = new Applicant(
                "lgpa01",
                "Low GPA",
                "weak password",
                "Math",
                2.1
        );

        System.out.println("\nLow GPA Applicant: " +
                lowGpa.getName() + " (GPA=" + lowGpa.getGpa() + ")");

        System.out.println("\nApplicant model tests complete.");
    }
}

