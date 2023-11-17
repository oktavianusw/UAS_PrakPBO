package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Game;
import model.User;
import controller.BuyGameController;
import controller.GameController;

public class GameListMenu extends JFrame {
    private User user;
    private GameController gameController;

    public GameListMenu(User user) {
        this.user = user;
        this.gameController = new GameController();

        setTitle("Game List");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionPage(user.getId());
            }
        });
        panel.add(transactionsButton, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(0, 2));

        java.util.List<Game> games = gameController.getAllGames();

        for (Game game : games) {
            JPanel gameCard = new JPanel();
            gameCard.setLayout(new BoxLayout(gameCard, BoxLayout.Y_AXIS));

            ImageIcon gameImage = new ImageIcon(game.getImagePath());
            Image image = gameImage.getImage();
            Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            gameImage = new ImageIcon(newimg);
            JLabel imageLabel = new JLabel(gameImage);
            gameCard.add(imageLabel);

            JTextField nameField = new JTextField(game.getName());
            gameCard.add(nameField);

            JTextField genreField = new JTextField(game.getGenre());
            gameCard.add(genreField);

            JTextField priceField = new JTextField(String.valueOf(game.getPrice()));
            gameCard.add(priceField);

            JButton buyButton = new JButton("Buy Game");
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BuyGameController buyGameController = new BuyGameController();
                    boolean success = buyGameController.buyGame(user.getId(), game.getId());

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Purchase successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Purchase failed!");
                    }
                }
            });
            gameCard.add(buyButton);

            gamePanel.add(gameCard);
        }

        panel.add(gamePanel, BorderLayout.CENTER);
    }
}