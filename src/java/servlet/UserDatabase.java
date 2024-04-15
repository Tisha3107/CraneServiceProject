package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mastanproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    public static boolean isValidUser(String username, String password) {
        // Initialize a connection to the database
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Prepare a SQL query to retrieve user data based on the provided username
            String query = "SELECT * FROM userregister WHERE registerusername = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                // If a user with the given username is found
                if (rs.next()) {
                    String storedPassword = rs.getString("registerpassword");
                    // Compare the stored password hash with the provided password
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // No matching user found or database error
    }
}
