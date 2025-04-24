package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionProvider {
    private static final String DB_NAME = "StudentRegistration";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Mishkat0325@#";

   public static Connection getcon() throws Exception {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL + "?useSSL=false", DB_USERNAME, DB_PASSWORD);

        // Create database if not exists
        try (Statement st = con.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            st.executeUpdate("USE " + DB_NAME);
        }
        
        return DriverManager.getConnection(DB_URL + DB_NAME + "?useSSL=false", DB_USERNAME, DB_PASSWORD);
    } catch (Exception ex) {
        throw new Exception("Failed to establish database connection", ex);
    }
}

    private static boolean databaseExists(Connection con, String dbName) throws Exception {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'")) {
            return rs.next();
        }
    }

    private static void createDatabase(Connection con, String dbName) throws Exception {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + dbName);
        }
    }
}