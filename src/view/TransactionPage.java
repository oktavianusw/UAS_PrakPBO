package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.Connector;

public class TransactionPage extends JFrame {
    private int userId;

    public TransactionPage(int userId) {
        this.userId = userId;

        setTitle("Transaction Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Your Transactions", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        add(header, BorderLayout.NORTH);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("User ID");
        columnNames.add("User Name");
        columnNames.add("Game ID");
        columnNames.add("Game Name");
        columnNames.add("Total Price");

        Vector<Vector<Object>> data = new Vector<>();
        try {
            Connection connection = Connector.getInstance().getConnection();
            String query = "SELECT t.id, t.user_id, u.name, t.game_id, g.name, g.price " +
                    "FROM transactions t " +
                    "JOIN users u ON t.user_id = u.id " +
                    "JOIN games g ON t.game_id = g.id " +
                    "WHERE t.user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt(1));
                row.add(resultSet.getInt(2));
                row.add(resultSet.getString(3));
                row.add(resultSet.getInt(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getDouble(6));
                data.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}