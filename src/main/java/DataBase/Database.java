package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    // Method to establish and return a database connection
    public static Connection getConnection() {
        try {
            // Attempt to connect to the database with the specified credentials
            return DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/hotelaria", // Database URL
                "root", // Username
                "" // Password (empty in this case)
            );  
        } catch(SQLException ex) {
            // Log any SQL exceptions that occur
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return null if connection fails
        return null;
    }
}
