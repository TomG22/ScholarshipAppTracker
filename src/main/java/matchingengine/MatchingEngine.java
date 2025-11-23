package backend;

import java.util.*;

/**
 * MatchingEngine – Implements the scholarship matching algorithm.
 *
 * ===== ME KEY POINTS (Mapped to SRS Requirements) =====
 *
 * ME-001 – Eligibility Filtering
 *      • GPA check
 *      • Major eligibility
 *      • Required keyword compliance
 *
 * ME-002 – Major Compatibility
 * ME-003 – Keyword Matching
 *
 * ME-004 – Weighted Scoring Algorithm
 *      • GPA 50%  • Major 30%  • Keywords 20%
 *
 * ME-005 – Match Ranking Output (sorted high → low)
 *
 * ME-006 – Logging & Traceability
 *
 * ME-007 – Automatic Recalculation When Data Changes
 *
 * NFR-017 – Matching Performance (Simple in-memory algorithm)
 */
public class MatchingEngine {

    private static final double GPA_WEIGHT = 0.5;
    private static final double MAJOR_WEIGHT = 0.3;
    private static final double KEYWORD_WEIGHT = 0.2;

    /**
     * Match one applicant to all scholarships.
     */
    public List<MatchResult> matchApplicant(Applicant applicant,
                                            Collection<Scholarship> scholarships) {

        List<MatchResult> results = new ArrayList<>();

        for (Scholarship s : scholarships) {
            Optional<MatchResult> match = scoreMatch(applicant, s);
            match.ifPresent(results::add);
        }

        Collections.sort(results); // ME-005
        return results;
    }

    /**
     * Core scoring logic.
     */
    private Optional<MatchResult> scoreMatch(Applicant a, Scholarship s) {

        StringBuilder why = new StringBuilder();
        double score = 0.0;

        // ME-001 GPA eligibility
        if (a.getGpa() < s.getMinGpa()) {
            why.append("Rejected: GPA below requirement. ");
            return Optional.empty();
        }
        why.append("GPA OK; ");
        score += GPA_WEIGHT * normalizeGpa(a.getGpa(), s.getMinGpa());

        // ME-002 Major check
        if (!s.getEligibleMajors().isEmpty()) {
            String major = a.getMajor().toLowerCase();
            if (!s.getEligibleMajors().contains(major)) {
                why.append("Rejected: Major not eligible. ");
                return Optional.empty();
            }
            why.append("Major OK; ");
            score += MAJOR_WEIGHT;
        }

        // ME-003 Keyword matching
        Set<String> allWords = new HashSet<>();
        a.getEssayKeywords().forEach(k -> allWords.add(k.toLowerCase()));
        a.getExtracurricularKeywords().forEach(k -> allWords.add(k.toLowerCase()));

        for (String kw : s.getRequiredKeywords()) {
            if (!allWords.contains(kw.toLowerCase())) {
                why.append("Missing keyword: ").append(kw).append(". ");
                return Optional.empty();
            }
        }
        if (!s.getRequiredKeywords().isEmpty()) {
            why.append("All required keywords found; ");
            score += KEYWORD_WEIGHT;
        }

        score = Math.min(1.0, score); // clamp

        return Optional.of(new MatchResult(a, s, score, why.toString()));
    }

    private double normalizeGpa(double gpa, double minGpa) {
        if (gpa <= minGpa) return 0.0;
        double range = 4.0 - minGpa;
        return Math.min(1.0, (gpa - minGpa) / range);
    }

    public List<MatchResult> recalcMatchForApplicant(Applicant a,
                                                     Collection<Scholarship> scholarships) {
        return matchApplicant(a, scholarships);
    }
}


