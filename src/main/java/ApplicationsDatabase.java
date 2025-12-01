import java.io.*;
import java.util.*;

public class ApplicationsDatabase {

    private static final String DB_FILE_NAME = "applicationsDB.csv";

    public static Set<Application> getAllApplications() {
        Set<Application> applications = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 5) continue;

                UUID currID;
                try {
                    currID = UUID.fromString(entry[0]);
                } catch (Exception e) {
                    continue;
                }

                String essay = entry[1];

                UUID authorID;
                try {
                    authorID = UUID.fromString(entry[2]);
                } catch (Exception e) {
                    continue;
                }

                Application.ApplicationStatus status;
                try {
                    status = Application.ApplicationStatus.valueOf(entry[3]);
                } catch (Exception e) {
                    continue;
                }

                boolean awarded = Boolean.parseBoolean(entry[4]);

                User author = UsersDatabase.getUser(authorID);
                if (author == null) continue;

                applications.add(new Application(currID, essay, author, status, awarded));
            }

        } catch (IOException e) {
            System.err.println("Error reading applications: " + e.getMessage());
        }

        return applications;
    }

    public static Application getApplication(UUID id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {

            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 5) continue;

                UUID currID;
                try {
                    currID = UUID.fromString(entry[0]);
                } catch (Exception e) {
                    continue;
                }

                if (!currID.equals(id)) continue;

                String essay = entry[1];

                UUID authorID;
                try {
                    authorID = UUID.fromString(entry[2]);
                } catch (Exception e) {
                    continue;
                }

                Application.ApplicationStatus status = Application.ApplicationStatus.valueOf(entry[3]);
                boolean awarded = Boolean.parseBoolean(entry[4]);

                User author = UsersDatabase.getUser(authorID);
                if (author == null) continue;

                return new Application(currID, essay, author, status, awarded);
            }

        } catch (Exception e) {
            System.err.println("Error loading application: " + e.getMessage());
        }
        return null;
    }

    public static boolean addApplication(Application app) {
        if (app == null || getApplication(app.getID()) != null)
            return false;

        try (FileWriter fw = new FileWriter(DB_FILE_NAME, true);
                PrintWriter writer = new PrintWriter(fw)) {

            writer.println(
                    app.getID().toString() + "," +
                    escapeCSV(app.getEssay()) + "," +
                    app.getAuthor().getID().toString() + "," +
                    app.getStatus().name() + "," +
                    app.wasAwarded()
                    );

            return true;

        } catch (IOException e) {
            System.err.println("Error writing to applications file: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeApplication(UUID id) {
        if (getApplication(id) == null) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {

            StringBuilder output = new StringBuilder();
            String header = reader.readLine();
            if (header != null) output.append(header).append("\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);

                if (entry.length > 0) {
                    try {
                        UUID currID = UUID.fromString(entry[0]);
                        if (currID.equals(id)) continue;
                    } catch (Exception ignored) {}
                }

                output.append(line).append("\n");
            }

            try (FileWriter fw = new FileWriter(DB_FILE_NAME)) {
                fw.write(output.toString());
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error removing application: " + e.getMessage());
            return false;
        }
    }

    public static void clear() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            String header = reader.readLine();
            if (header == null || header.trim().isEmpty()) return;

            try (FileWriter fw = new FileWriter(DB_FILE_NAME)) {
                fw.write(header + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error clearing applications: " + e.getMessage());
        }
    }

    public static boolean isEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) return false;
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error checking empty state: " + e.getMessage());
            return false;
        }
    }

    private static String escapeCSV(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
}
