import java.io.*;
import java.util.*;

public class ReviewersDatabase {

    private static final String DB_FILE_NAME = "reviewersDB.csv";

    public static boolean addReviewer(Reviewer r) {
        if (r == null || getReviewer(r.getID()) != null)
            return false;

        boolean fileExists = new File(DB_FILE_NAME).exists();

        try (FileWriter fw = new FileWriter(DB_FILE_NAME, true);
             PrintWriter writer = new PrintWriter(fw)) {

            if (!fileExists) writer.println(getHeader());

            writer.println(serializeReviewer(r));
            return true;

        } catch (IOException e) {
            System.err.println("Error adding reviewer: " + e.getMessage());
            return false;
        }
    }

    public static Reviewer getReviewer(UUID id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 5) continue;

                UUID currID = UUID.fromString(values[0]);
                if (!currID.equals(id)) continue;

                return deserializeReviewer(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading reviewer: " + e.getMessage());
        }
        return null;
    }

    public static Reviewer getReviewerByNetID(String netID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 5) continue;

                String currID = values[1];
                if (!currID.equals(netID)) continue;

                return deserializeReviewer(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading reviewer: " + e.getMessage());
        }
        return null;
    }

    public static boolean removeReviewer(UUID id) {
        try {
            File inputFile = new File(DB_FILE_NAME);
            File tempFile = new File("temp_" + DB_FILE_NAME);

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                String header = reader.readLine();
                if (header != null) writer.println(header);

                String line;
                while ((line = reader.readLine()) != null) {
                    if (!UUID.fromString(line.split(",", -1)[0]).equals(id)) {
                        writer.println(line);
                    }
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) return false;
            return true;

        } catch (IOException e) {
            System.err.println("Error removing reviewer: " + e.getMessage());
            return false;
        }
    }

    private static String getHeader() {
        return "ID,NetID,Name,Password,Majors";
    }

    private static String serializeReviewer(Reviewer r) {
        return String.join(",",
                r.getID().toString(),
                r.getNetID(),
                r.getName(),
                r.getPassword(),
                String.join(";", r.getMajors())
        );
    }

    private static Reviewer deserializeReviewer(String[] values) {
        UUID id = UUID.fromString(values[0]);
        String netID = values[1];
        String name = values[2];
        String password = values[3];
        Set<String> majors = new HashSet<>(Arrays.asList(values[4].split(";")));
        return new Reviewer(id, netID, name, password, majors);
    }
}

