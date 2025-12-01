import java.util.Arrays;
import java.util.List;

public class MatchingEngineTop3Test {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        Applicant a = new Applicant(
                "Tester",
                "Computer Science",
                3.9,
                Arrays.asList("coding", "ai", "research"),
                Arrays.asList("leadership")
        );

        Scholarship s1 = new Scholarship("S1", 3.0, Arrays.asList("computer science"), Arrays.asList("coding"));
        Scholarship s2 = new Scholarship("S2", 3.0, Arrays.asList("computer science"), Arrays.asList("ai"));
        Scholarship s3 = new Scholarship("S3", 3.0, Arrays.asList("computer science"), Arrays.asList("research"));
        Scholarship s4 = new Scholarship("S4", 3.0, Arrays.asList("computer science"), Arrays.asList("coding"));

        List<Scholarship> all = Arrays.asList(s1, s2, s3, s4);

        List<MatchResult> top3 = engine.topMatches(a, all, 3);

        System.out.println("=== Top 3 Test ===");
        for (MatchResult r : top3) {
            System.out.println(r);
        }
    }
}
