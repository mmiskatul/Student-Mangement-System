package Dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class Tables {
    public static void main(String[] args) {
    try (Connection con = ConnectionProvider.getcon();
         Statement st = con.createStatement()) {
        
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "name VARCHAR(255) NOT NULL, "
            + "gender VARCHAR(50) NOT NULL, "
            + "contract VARCHAR(20) NOT NULL UNIQUE, "
            + "course VARCHAR(255) NOT NULL"
            + ")";
        
        st.executeUpdate(createTableSQL);
        JOptionPane.showMessageDialog(null, 
            "Table 'students' created successfully!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, 
            "Error: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    private static boolean tableExists(Connection con, String tableName) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        try (ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }
}