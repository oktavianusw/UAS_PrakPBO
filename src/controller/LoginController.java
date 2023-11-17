package controller;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public User login(String email, String password) {
        User user = null;
        Connection connection = Connector.getInstance().getConnection();
        if (connection == null) {
            System.err.println("No database connection");
            return null;
        }

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return user;
    }
}