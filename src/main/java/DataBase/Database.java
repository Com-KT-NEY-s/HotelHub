package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class to manage database connections.
public class Database {

    // Method to establish and return a connection to the database.
    public static Connection getConnection() {
        try {
            // Attempt to connect to the database using MariaDB's JDBC driver.
            return DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/hotelaria", // Database URL
                    "root", // Database username
                    "" // Database password
            );
        } catch (SQLException ex) {
            // Log the exception in case of connection failure.
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Return null if the connection fails.
    }
}
