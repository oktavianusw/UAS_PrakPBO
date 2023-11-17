package controller;

import model.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        Connection connection = Connector.getInstance().getConnection();
        if (connection == null) {
            System.err.println("No database connection");
            return games;
        }

        String query = "SELECT * FROM games";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Game game = new Game(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getInt("price"),
                        resultSet.getString("image")
                    );
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return games;
    }

}