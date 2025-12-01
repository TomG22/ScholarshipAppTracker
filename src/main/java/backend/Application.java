package backend;

import java.util.*;

public class Application {

    private final String essay;
    private final User author;
    // state? (submitted, in progress, awarded)
    
    public Application(String essay, User author) {

        if (essay == null || essay.isEmpty())
            throw new IllegalArgumentException("Essay cannot be null or empty.");
        this.essay = essay;

        if (author == null)
            throw new IllegalArgumentException("Author cannot be null.");
        this.author = author;
    }

    public String getEssay() {
        return essay;
    }

    public Set<String> getMatchedKeywords(Set<String> keywords) {
        HashSet<String> matched = new HashSet<String>();

        for (String word : this.essay.split("\\s+")) {
            String lowerWord = word;
            for (String keyword : keywords) {
                if (lowerWord.equals(keyword)) {
                    matched.add(lowerWord);
                }
            }
        }

        return matched;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Application{\n" +
                "\tessay=" + essay + '\n' +
                "\tauthor=" + author + '\n' +
                '}';
    }
}
