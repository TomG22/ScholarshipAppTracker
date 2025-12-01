public class ApplicationEntry {

    private final Application application;
    private final User applicant;

    // Constructor
    public ApplicationEntry(Application application, User applicant) {

        if (application == null) {
            throw new IllegalArgumentException("Application cannot be null.");
        }

        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null.");
        }

        this.application = application;
        this.applicant = applicant;
    }

    // Getters
    public Application getApplication() {
        return application;
    }

    public User getApplicant() {
        return applicant;
    }

    @Override
    public String toString() {
        return "ApplicationEntry{\n" +
                "application=" + application + '\n' +
                ", applicant=" + applicant.getName() + '\n' +
                " (" + applicant.getNetId() + ")" + '\n' +
                '}';
    }
}
