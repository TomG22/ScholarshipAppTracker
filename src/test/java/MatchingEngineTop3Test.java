import java.util.List;
import java.util.Set;

public class MatchingEngineTop3Test {

    public static void main(String[] args) {

        MatchingEngine engine = new MatchingEngine();

        Applicant a = new Applicant(
                "tester01",
                "Tester",
                "password123",
                "Computer Science",
                Set.of("coding", "ai", "research"),
                3.9
        );

        Scholarship s1 = new Scholarship(
                "S1",
                0.0,
                3.0,
                Set.of("computer science"),
                Set.of("coding")
        );

        Scholarship s2 = new Scholarship(
                "S2",
                0.0,
                3.0,
                Set.of("computer science"),
                Set.of("ai")
        );

        Scholarship s3 = new Scholarship(
                "S3",
                0.0,
                3.0,
                Set.of("computer science"),
                Set.of("research")
        );

        Scholarship s4 = new Scholarship(
                "S4",
                0.0,
                3.0,
                Set.of("computer science"),
                Set.of("coding")
        );

        List<Scholarship> all = List.of(s1, s2, s3, s4);

        List<MatchResult> top3 = engine.topMatches(a, all, 3);

        System.out.println("=== Top 3 Test ===");
        for (MatchResult r : top3) {
            System.out.println(r);
        }
    }
}

