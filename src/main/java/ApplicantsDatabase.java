import java.io.*;
import java.util.*;

public class ApplicantsDatabase {

    private static final String DB_FILE_NAME = "applicantsDB.csv";

    public static boolean addApplicant(Applicant a) {
        if (a == null || getApplicant(a.getID()) != null)
            return false;

        boolean fileExists = new File(DB_FILE_NAME).exists();

        try (FileWriter fw = new FileWriter(DB_FILE_NAME, true);
             PrintWriter writer = new PrintWriter(fw)) {

            if (!fileExists) {
                writer.println(getHeader());
            }

            writer.println(serializeApplicant(a));
            return true;

        } catch (IOException e) {
            System.err.println("Error adding applicant: " + e.getMessage());
            return false;
        }
    }

    public static Applicant getApplicant(UUID id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            String headerLine = reader.readLine();
            if (headerLine == null) return null;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 7) continue;

                UUID currID = UUID.fromString(values[0]);
                if (!currID.equals(id)) continue;

                return deserializeApplicant(values);
            }

        } catch (IOException e) {
            System.err.println("Error reading applicant: " + e.getMessage());
        }
        return null;
    }

    public static Applicant getApplicantByNetID(String netID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            String headerLine = reader.readLine();
            if (headerLine == null) return null;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 7) continue;

                String currID = values[1];
                if (!currID.equals(netID)) continue;

                return deserializeApplicant(values);
            }

        } catch (IOException e) {
            System.err.println("Error reading applicant: " + e.getMessage());
        }
        return null;
    }

    public static boolean removeApplicant(UUID id) {
        try {
            File inputFile = new File(DB_FILE_NAME);
            File tempFile = new File("temp_" + DB_FILE_NAME);

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                String header = reader.readLine();
                if (header != null) writer.println(header);

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",", -1);
                    if (values.length < 1) continue;

                    if (!UUID.fromString(values[0]).equals(id)) {
                        writer.println(line);
                    }
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                return false;
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error removing applicant: " + e.getMessage());
            return false;
        }
    }

    private static String getHeader() {
        return "ID,NetID,Name,Password,Major,GPA,AwardBalance,Extracurriculars";
    }

    private static String serializeApplicant(Applicant a) {
        return String.join(",",
                a.getID().toString(),
                a.getNetID(),
                a.getName(),
                a.getPassword(),
                a.getMajor(),
                String.valueOf(a.getGpa()),
                String.valueOf(a.getAwardBalance()),
                String.join(";", a.getExtracurriculars())
        );
    }

    private static Applicant deserializeApplicant(String[] values) {
        UUID id = UUID.fromString(values[0]);
        String netID = values[1];
        String name = values[2];
        String password = values[3];
        String major = values[4];
        double gpa = Double.parseDouble(values[5]);
        double award = Double.parseDouble(values[6]);
        Set<String> extracurriculars = new HashSet<>(Arrays.asList(values[7].split(";")));

        return new Applicant(id, netID, name, password, major, extracurriculars, gpa, award);
    }
}

