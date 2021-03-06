// Class Name: DatabaseControl
// Description: Manages operations on a SQLite of player names and high scores
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseControl {

    // Method: insert
    // It inserts score and name of the player into the database
    public static void insert(String name, int score) {
        insertPoints(name, score, delete());
    }

    // Method: insertPoints
    // This method inserts a new entry of name and score into the database using primary key id
    private static void insertPoints(String name, int score, int id) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores.db");
            c.setAutoCommit(false);
            System.out.println("Database Opened");

            stmt = c.createStatement();

            // Insert new entry
            String sql = "INSERT INTO SCORES (ID,NAME,SCORE)" +
                    "VALUES (" + id + ", '" + name + "', " + score + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Points Added");
    }

    // Method: getPoints
    //Description: returns arrays of the database's names and scores as a Pair
    public static Pair<String[], int[]> getPoints() {

        Connection c = null;
        Statement stmt = null;
        String[] names = new String[5];
        int[] scores = new int[5];
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores.db");
            createDB();
            c.setAutoCommit(false);
            System.out.println("Database Opened successfully");

            stmt = c.createStatement();

            // Read the first five entries as sorted
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE DESC LIMIT 5;");
            int i = 0;
            while (rs.next()) {
                names[i] = rs.getString("name");
                scores[i] = rs.getInt("score");
                i++;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Select successful");
        return new Pair<>(names, scores);
    }

    // Method: delete
    // This method removes the last entry from the database as well returns primary key
    private static int delete() {
        Connection c = null;
        Statement stmt = null;
        int id = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores.db");
            c.setAutoCommit(false);
            System.out.println("Database Opened ");

            stmt = c.createStatement();

            // Read the id of the lowest score
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE ASC LIMIT 1;");

            while (rs.next()) {
                id = rs.getInt("id");
                System.out.println("ID = " + id);
            }
            rs.close();

            // Remove the entry with that primary key;
            String sql = "DELETE from SCORES where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Deletion done successfully");
        return id; // Return the primary key
    }

    // Method: createDB
    // This method creates the database if it does not already exists
    private static void createDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SCORES " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " SCORE          INT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    // Method: showDB
    // this method outputs the entries in the the database
    private static void showDB() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:scores.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            // Read the first five entries ordered by score
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE DESC LIMIT 5;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("score");

                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("SCORE = " + age);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Select done successfully");
    }
}
