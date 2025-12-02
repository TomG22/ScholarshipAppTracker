import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    private Scholarship parseScholarshipFromJSONObject(JSONObject json) {
        String name = json.getString("name");

        double awardAmount = 0.0;
        try {
            awardAmount = json.getDouble("awardAmount");
        } catch (Exception e) {
            try {
                awardAmount = Double.parseDouble(json.getString("awardAmount"));
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Invalid awardAmount format: " + json.getString("awardAmount"));
            }
        }

        double minGpa = json.getDouble("minGpa");

        Set<String> eligibleMajors = new HashSet<>();
        JSONArray majorsArray = json.optJSONArray("eligibleMajors");
        if (majorsArray != null) {
            for (int i = 0; i < majorsArray.length(); i++) {
                eligibleMajors.add(majorsArray.getString(i).toLowerCase());
            }
        }

        Set<String> requiredKeywords = new HashSet<>();
        JSONArray keywordsArray = json.optJSONArray("requiredKeywords");
        if (keywordsArray != null) {
            for (int i = 0; i < keywordsArray.length(); i++) {
                requiredKeywords.add(keywordsArray.getString(i).toLowerCase());
            }
        }

        return new Scholarship(
            name,
            awardAmount,
            minGpa,
            eligibleMajors,
            requiredKeywords
        );
    }

    public Set<Scholarship> parseScholarships(File file) throws IOException {
        if (file == null)
            throw new IllegalArgumentException("File cannot be null.");

        String content = Files.readString(file.toPath());
        JSONArray array = new JSONArray(content);

        Set<Scholarship> scholarships = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            scholarships.add(parseScholarshipFromJSONObject(obj));
        }

        return scholarships;
    }

    private User parseUserFromJSONObject(JSONObject json) {
        String netId = json.getString("netId");
        String name = json.getString("name");
        String password = json.getString("password");
        String roleStr = json.getString("role");
        User.RoleType role = User.RoleType.valueOf(roleStr.toUpperCase());

        return new User(netId, name, password, role);
    }

    private Application parseApplicationFromJSONObject(JSONObject json) {
        String essay = json.getString("essay");

        if (!json.has("author") || json.isNull("author")) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        JSONObject authorJson = json.getJSONObject("author");
        if (authorJson == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        User author = parseUserFromJSONObject(authorJson);

        return new Application(essay, author);
    }

    public Set<Application> parseApplications(File file) throws IOException {
        if (file == null)
            throw new IllegalArgumentException("File cannot be null.");

        String content = Files.readString(file.toPath());
        JSONArray array = new JSONArray(content);

        Set<Application> applications = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            applications.add(parseApplicationFromJSONObject(obj));
        }

        return applications;
    }
}
