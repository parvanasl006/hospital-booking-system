package ui;

import database.DatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField, phoneField, addressField;
    private JPasswordField passwordField;
    private JComboBox<String> genderCombo;
    private JSpinner dateSpinner;

    public RegisterFrame() {
        setTitle("Register - Hospital Appointment System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Patient Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Name
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        panel.add(phoneField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(genderCombo, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(20);
        panel.add(addressField, gbc);

        // Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 30));
        registerButton.addActionListener(e -> handleRegister());

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        buttonsPanel.add(registerButton);
        buttonsPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(buttonsPanel, gbc);

        add(panel);
    }

    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String phone = phoneField.getText();
        String gender = (String) genderCombo.getSelectedItem();
        String address = addressField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO patients (name, email, password, phone, gender, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, phone);
            stmt.setString(5, gender);
            stmt.setString(6, address);
            stmt.setString(7, "2000-01-01"); // Default DOB

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginFrame().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
