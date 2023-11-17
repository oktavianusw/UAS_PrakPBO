package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.LoginController;
import model.User;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private User user;

    public LoginPage() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                LoginController controller = new LoginController();
                user = controller.login(email, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Logged in successfully!");
                    new GameListMenu(user);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Failed to login!");
                }
            }
        });
        panel.add(loginButton);
    }
}