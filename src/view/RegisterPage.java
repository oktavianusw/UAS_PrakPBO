package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import controller.RegisterController;

public class RegisterPage extends JFrame {
    private JTextField emailField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JRadioButton genderRadioButton;
    private Date birthdayDate;
    private JFileChooser photoFileChooser;
    private JButton registerButton;
    private JButton backButton;

    public RegisterPage() {
        setTitle("Register");
        setSize(400, 300);
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

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 50, 165, 25);
        panel.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        genderRadioButton = new JRadioButton("Male");
        genderRadioButton.setBounds(10, 110, 80, 25);
        panel.add(genderRadioButton);

        Date birthday = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthdayString = formatter.format(birthday);

        JTextField birthdayTextField = new JTextField(birthdayString);
        birthdayTextField.setBounds(100, 140, 165, 25);
        panel.add(birthdayTextField);

        photoFileChooser = new JFileChooser();
        photoFileChooser.setBounds(10, 170, 255, 25);
        panel.add(photoFileChooser);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 200, 80, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                boolean isMale = genderRadioButton.isSelected();
                File photoFile = photoFileChooser.getSelectedFile();

                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Password must be at least 8 characters!");
                } else {
                    RegisterController controller = new RegisterController();
                    boolean success = controller.register(email, name, password, isMale, birthday, photoFile);

                    if (success) {
                        JOptionPane.showMessageDialog(RegisterPage.this, "Registered successfully!");
                        new LoginPage();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(RegisterPage.this, "Failed to register!");
                    }
                }
            }
        });
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(100, 200, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LandingPage();
                dispose();
            }
        });
        panel.add(backButton);
    }
}