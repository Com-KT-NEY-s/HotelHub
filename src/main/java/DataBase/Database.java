package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/hotelaria",
            "root", "");
        }   catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
    

