package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyGameController {
    public boolean buyGame(int userId, int gameId) {
        Connection connection = Connector.getInstance().getConnection();
        if (connection == null) {
            System.err.println("No database connection");
            return false;
        }

        String query = "INSERT INTO transactions (user_id, game_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return false;
        }
    }
}