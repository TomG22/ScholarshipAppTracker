import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UsersDatabase {

    private static final String DB_FILE_NAME = "usersDB.csv";

    public static User getUser(UUID id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 5) continue;

                UUID currId;
                try {
                    currId = UUID.fromString(entry[0]);
                } catch (Exception ignored) {
                    continue;
                }

                if (!currId.equals(id)) continue;

                String netID = entry[1];
                String name = entry[2];
                String password = entry[3];
                User.RoleType role = User.RoleType.valueOf(entry[4]);

                return new User(currId, netID, name, password, role);
            }

        } catch (Exception e) {
            System.err.println("Error loading user: " + e.getMessage());
        }

        return null;
    }

    public static User getUser(String netID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 5) continue;

                String currNetID = entry[1];
                if (!currNetID.equals(netID)) continue;

                UUID id = UUID.fromString(entry[0]);
                String name = entry[2];
                String password = entry[3];
                User.RoleType role = User.RoleType.valueOf(entry[4]);

                return new User(id, currNetID, name, password, role);
            }

        } catch (Exception e) {
            System.err.println("Error loading user by netID: " + e.getMessage());
        }

        return null;
    }

    public static boolean authUser(String netID, String encPassword) {
        User user = getUser(netID);
        return user != null && user.getPassword().equals(encPassword);
    }

    public static boolean addUser(User user) {
        if (user == null || getUser(user.getID()) != null)
            return false;

        try (FileWriter fw = new FileWriter(DB_FILE_NAME, true);
             PrintWriter writer = new PrintWriter(fw)) {

            writer.println(
                user.getID() + "," +
                user.getNetID() + "," +
                user.getName() + "," +
                user.getPassword() + "," +
                user.getRole().name()
            );

            return true;

        } catch (IOException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeUser(UUID id) {
        if (getUser(id) == null) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME))) {
            StringBuilder out = new StringBuilder();
            String header = reader.readLine();
            if (header != null) out.append(header).append("\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] entry = line.split(",", -1);
                if (entry.length < 1) continue;

                UUID currId;
                try {
                    currId = UUID.fromString(entry[0]);
                } catch (Exception ignored) {
                    continue;
                }

                if (currId.equals(id)) continue;

                out.append(line).append("\n");
            }

            try (FileWriter fw = new FileWriter(DB_FILE_NAME)) {
                fw.write(out.toString());
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error removing user: " + e.getMessage());
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
            System.err.println("Error clearing users: " + e.getMessage());
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
            System.err.println("Error checking empty: " + e.getMessage());
            return false;
        }
    }
}
