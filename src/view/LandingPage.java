package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;

    public LandingPage() {
        setTitle("Landing Page Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        loginButton = new JButton("Login Pengguna");
        loginButton.setBounds(10, 20, 160, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });
        panel.add(loginButton);

        registerButton = new JButton("Registrasi Pengguna Baru");
        registerButton.setBounds(10, 50, 160, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
                dispose();
            }
        });
        panel.add(registerButton);
    }

}