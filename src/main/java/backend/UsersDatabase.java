package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersDatabase {

    // Retrieve a user from the users csv table
    public static User getUser(String netId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userData.csv"))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] userEntry = line.split(",", -1);

                if (userEntry.length < 4) continue;

                String currNetId = userEntry[0];

                // Check that the name and password match
                if (currNetId.equals(netId)) {
                    String name = userEntry[1];
                    String password = userEntry[2];
                    User.RoleType role = User.RoleType.valueOf(userEntry[3]);

                    return new User(netId, name, password, role);
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
        return null;
    }

    // Authenticate a given user password
    public static boolean authUser(String netId, String encPassword) { 
        User user = getUser(netId);
        return user != null && user.getPassword().equals(encPassword);
    }

    // Add a new user to the users csv table
    public static boolean addUser(User user) {
        // Check if the user already exists
        if (getUser(user.getNetId()) != null) {
            System.err.println("User with netId " + user.getNetId() + " already exists.");
            return false;
        }

        // Append the new user to the CSV file
        try (FileWriter fileWriter = new FileWriter("userData.csv", true);
                PrintWriter writer = new PrintWriter(fileWriter)) {

            // Add the user's data as a new line in the CSV
            writer.println(user.getNetId() + "," + user.getName() + "," + user.getPassword() + "," + user.getRole().name());
            return true;

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    // Remove a user from the users csv table
    public static boolean removeUser(String netId) {
        // Check if the user already exists
        if (getUser(netId) == null) {
            return false;
        }

        try {
            // Read all users from the CSV file into a list
            BufferedReader reader = new BufferedReader(new FileReader("userData.csv"));
            StringBuilder fileContents = new StringBuilder();
            String line;

            boolean headerRead = false;
            while ((line = reader.readLine()) != null) {
                if (!headerRead) {
                    fileContents.append(line).append("\n");
                    headerRead = true;
                    continue;
                }

                String[] userEntry = line.split(",");
                if (userEntry[0].equals(netId)) {
                    continue;
                }

                fileContents.append(line).append("\n");
            }
            reader.close();

            // Rewrite the file without the removed user
            FileWriter fileWriter = new FileWriter("userData.csv");
            fileWriter.write(fileContents.toString());
            fileWriter.close();

            return true;
        } catch (IOException e) {
            System.err.println("Error removing user: " + e.getMessage());
            return false;
        }
    }

    public static void clear() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userData.csv"))) {

            // Only read the header
            String header = reader.readLine();

            if (header == null || header.trim().isEmpty()) {
                System.err.println("CSV file has no header; cannot clear properly.");
                return;
            }

            try (FileWriter writer = new FileWriter("userData.csv")) {
                writer.write(header + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error clearing database: " + e.getMessage());
        }
    }

    public static boolean isEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userData.csv"))) {

            String header = reader.readLine();

            if (header == null) {
                return false;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    return false;
                }
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error checking if database is empty: " + e.getMessage());
            return false;
        }
    }
}
