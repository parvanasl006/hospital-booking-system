package ui;

import database.DatabaseConnection;
import models.Appointment;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PatientDashboard extends JFrame {
    private int patientId;
    private JTable appointmentsTable;
    private DefaultTableModel tableModel;

    public PatientDashboard(int patientId) {
        this.patientId = patientId;
        setTitle("Patient Dashboard - Hospital Appointment System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Top Panel - Welcome & Buttons
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(70, 130, 180));
        topPanel.setPreferredSize(new Dimension(900, 60));

        JLabel welcomeLabel = new JLabel("Welcome Patient ID: " + patientId);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);

        JButton bookButton = new JButton("Book Appointment");
        bookButton.addActionListener(e -> new BookAppointmentFrame(patientId).setVisible(true));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        topPanel.add(welcomeLabel);
        topPanel.add(bookButton);
        topPanel.add(logoutButton);

        // Center Panel - Appointments Table
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Appointment ID", "Doctor", "Date", "Time", "Reason", "Status"});
        appointmentsTable = new JTable(tableModel);
        appointmentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(appointmentsTable);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Your Appointments:"), BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel - Actions
        JPanel bottomPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadAppointments());

        JButton cancelButton = new JButton("Cancel Appointment");
        cancelButton.addActionListener(e -> cancelAppointment());

        bottomPanel.add(refreshButton);
        bottomPanel.add(cancelButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        loadAppointments();
    }

    private void loadAppointments() {
        tableModel.setRowCount(0);
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT a.appointment_id, d.name, a.appointment_date, a.appointment_time, a.reason, a.status " +
                          "FROM appointments a JOIN doctors d ON a.doctor_id = d.doctor_id " +
                          "WHERE a.patient_id = ? ORDER BY a.appointment_date DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("appointment_id"),
                    rs.getString("name"),
                    rs.getString("appointment_date"),
                    rs.getString("appointment_time"),
                    rs.getString("reason"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelAppointment() {
        int selectedRow = appointments
