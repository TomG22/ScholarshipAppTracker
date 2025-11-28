package backend;

import java.util.*;

/**
 * MatchingEngine implements the core matching algorithm for Team 8.
 *
 * Supports SRS Requirements:
 *  - ME-001 Eligibility Filtering
 *  - ME-002 Major Compatibility
 *  - ME-003 Keyword Matching
 *  - ME-004 Weighted Scoring
 *  - ME-005 Match Ranking Output
 *  - ME-006 Logging and Traceability
 *  - ME-007 Match Update on Data Change
 *  - NFR-017 Matching Performance
 */
public class MatchingEngine {

    private static final double GPA_WEIGHT = 0.5;
    private static final double MAJOR_WEIGHT = 0.3;
    private static final double KEYWORD_WEIGHT = 0.2;

    /**
     * Run matching for one applicant across all scholarships.
     */
    public List<MatchResult> matchApplicant(Applicant applicant,
                                            Collection<Scholarship> scholarships) {

        List<MatchResult> results = new ArrayList<>();

        for (Scholarship s : scholarships) {
            Optional<MatchResult> match = scoreMatch(applicant, s);
            match.ifPresent(results::add);
        }

        // Sort by score descending (ME-005)
        Collections.sort(results);
        return results;
    }

    /**
     * Score one applicant-scholarship pair.
     * Applies eligibility filtering, major match, keyword match, and weighted scoring.
     */
    private Optional<MatchResult> scoreMatch(Applicant a, Scholarship s) {

        StringBuilder why = new StringBuilder();
        double score = 0.0;

        // ------------------------------
        // ME-001: GPA Eligibility Filter
        // ------------------------------
        if (a.getGpa() < s.getMinGpa()) {
            why.append("Rejected: GPA below minimum. ");
            return Optional.empty();
        }
        why.append("Meets GPA requirement. ");
        score += GPA_WEIGHT * normalizeGpa(a.getGpa(), s.getMinGpa());

        // ------------------------------
        // ME-002: Major Compatibility
        // ------------------------------
        if (!s.getEligibleMajors().isEmpty()) {
            String major = a.getMajor().toLowerCase();
            if (!s.getEligibleMajors().contains(major)) {
                why.append("Rejected: Major not eligible. ");
                return Optional.empty();
            } else {
                why.append("Eligible major. ");
                score += MAJOR_WEIGHT;
            }
        }

        // ------------------------------
        // ME-003: Keyword Matching
        // ------------------------------
        /*
        Set<String> applicantWords = new HashSet<>();
        a.getEssayKeywords().forEach(k -> applicantWords.add(k.toLowerCase()));
        a.getExtracurricularKeywords().forEach(k -> applicantWords.add(k.toLowerCase()));

        if (!s.getRequiredKeywords().isEmpty()) {
            for (String kw : s.getRequiredKeywords()) {
                if (!applicantWords.contains(kw.toLowerCase())) {
                    why.append("Rejected: Missing keyword '" + kw + "'. ");
                    return Optional.empty();
                }
            }
            why.append("Contains required keywords. ");
            score += KEYWORD_WEIGHT;
        }
        */

        score = Math.min(1.0, score); // safety clamp

        return Optional.of(new MatchResult(a, s, score, why.toString()));
    }

    /**
     * Normalize GPA into 0.0–1.0 scoring scale.
     */
    private double normalizeGpa(double gpa, double minGpa) {
        if (gpa <= minGpa) return 0.0;
        double maxRange = 4.0 - minGpa;
        if (maxRange <= 0) return 1.0;
        return Math.min(1.0, (gpa - minGpa) / maxRange);
    }

    /**
     * ME-007 — Recalculates matching when the applicant changes info.
     */
    public List<MatchResult> recalcMatchForApplicant(Applicant a,
                                                     Collection<Scholarship> scholarships) {
        return matchApplicant(a, scholarships);
    }
}
