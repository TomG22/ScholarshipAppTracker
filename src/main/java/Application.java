import java.util.*;

public class Application {

    public enum ApplicationStatus {
        IN_PROGRESS,
        SUBMITTED,
        REVIEWED,
    }

    private final UUID id;
    private final String essay;
    private final User author;
    private final ApplicationStatus status;
    private final boolean awarded;
    
    public Application(UUID id, String essay, User author, ApplicationStatus test, boolean awarded) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null.");
        this.id = id;

        if (essay == null || essay.isEmpty())
            throw new IllegalArgumentException("Essay cannot be null or empty.");
        this.essay = essay;

        if (author == null)
            throw new IllegalArgumentException("Author cannot be null.");
        this.author = author;

        this.status = ApplicationStatus.IN_PROGRESS;
        this.awarded = false;
    }

    public Application(String essay, User author) {
        this.id = UUID.randomUUID();

        if (essay == null || essay.isEmpty())
            throw new IllegalArgumentException("Essay cannot be null or empty.");
        this.essay = essay;

        if (author == null)
            throw new IllegalArgumentException("Author cannot be null.");
        this.author = author;

        this.status = ApplicationStatus.IN_PROGRESS;
        this.awarded = false;
    }

    public UUID getID() { return id; }
    public String getEssay() { return essay; }

    public Set<String> getMatchedKeywords(Set<String> keywords) {
        HashSet<String> matched = new HashSet<>();

        for (String word : this.essay.split("\\s+")) {
            for (String keyword : keywords) {
                if (word.equalsIgnoreCase(keyword)) {
                    matched.add(keyword);
                }
            }
        }

        return matched;
    }

    public User getAuthor() { return author; }
    public ApplicationStatus getStatus() { return status; }
    public boolean wasAwarded() { return awarded; }

    @Override
    public String toString() {
        return "Application{\n" +
                "\tID: " + id + "\n" +
                "\tEssay: " + essay + '\n' +
                "\tAuthor: " + author.getNetID() + '\n' +
                "\tStatus: " + status + '\n' +
                '}';
    }
}
