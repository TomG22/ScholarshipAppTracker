package backend;

import java.io.*;
import java.util.*;

public class ScholarshipsDatabase {

    private static final String DB_FILE_NAME = "scholarshipsDB.csv";

    private static Set<String> parseSet(String raw) {
        Set<String> set = new HashSet<>();
        if (raw == null || raw.isEmpty()) return set;

        String[] parts = raw.split(";");
        for (String p : parts) {
            p = p.trim().toLowerCase();
            if (!p.isEmpty()) set.add(p);
        }
        return set;
    }

    public static Scholarship getScholarship(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 4) continue;

                String currName = entry[0];
                if (!currName.equals(name)) continue;

                double minGpa = Double.parseDouble(entry[1]);

                Set<String> majors = parseSet(entry[2]);
                Set<String> keywords = parseSet(entry[3]);

                return new Scholarship(currName, minGpa, majors, keywords);
            }
        } catch (Exception e) {
            System.err.println("Error loading scholarship: " + e.getMessage());
        }
        return null;
    }

    public static boolean addScholarship(Scholarship s) {
        if (getScholarship(s.getName()) != null) {
            System.err.println("Scholarship '" + s.getName() + "' already exists.");
            return false;
        }

        try (FileWriter fw = new FileWriter(DB_FILE_NAME, true);
             PrintWriter writer = new PrintWriter(fw)) {

            String majors = String.join(";", s.getEligibleMajors());
            String keywords = String.join(";", s.getRequiredKeywords());

            writer.println(s.getName() + "," +
                           s.getMinGpa() + "," +
                           majors + "," +
                           keywords);
            return true;

        } catch (IOException e) {
            System.err.println("Error writing to scholarships file: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeScholarship(String name) {
        if (getScholarship(name) == null) {
            return false;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME));
            StringBuilder output = new StringBuilder();
            String line;

            boolean headerRead = false;

            while ((line = reader.readLine()) != null) {
                if (!headerRead) {
                    output.append(line).append("\n");
                    headerRead = true;
                    continue;
                }

                String[] entry = line.split(",", -1);
                if (entry.length >= 1 && entry[0].equals(name)) {
                    continue;
                }

                output.append(line).append("\n");
            }
            reader.close();

            FileWriter fw = new FileWriter(DB_FILE_NAME);
            fw.write(output.toString());
            fw.close();

            return true;

        } catch (IOException e) {
            System.err.println("Error removing scholarship: " + e.getMessage());
            return false;
        }
    }

    public static void clear() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            String header = reader.readLine();
            if (header == null || header.trim().isEmpty()) {
                System.err.println("CSV missing header; cannot clear.");
                return;
            }

            try (FileWriter fw = new FileWriter(DB_FILE_NAME)) {
                fw.write(header + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error clearing scholarships: " + e.getMessage());
        }
    }

    public static boolean isEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {

            String header = reader.readLine();
            if (header == null) return false;

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
}

