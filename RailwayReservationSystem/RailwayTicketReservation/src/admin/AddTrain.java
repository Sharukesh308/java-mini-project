package admin;

import javax.swing.*;

import database.ConnectionManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddTrain extends JFrame {
    private JTextField trainNoField, trainNameField, sourceField, destinationField, seatsField;

    public AddTrain() {
        setTitle("Add Train");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Input fields
        trainNoField = new JTextField();
        trainNameField = new JTextField();
        sourceField = new JTextField();
        destinationField = new JTextField();
        seatsField = new JTextField();

        add(new JLabel("Train No:"));
        add(trainNoField);
        add(new JLabel("Train Name:"));
        add(trainNameField);
        add(new JLabel("Source:"));
        add(sourceField);
        add(new JLabel("Destination:"));
        add(destinationField);
        add(new JLabel("Seats Available:"));
        add(seatsField);

        // Add button
        JButton addButton = new JButton("Add Train");
        addButton.addActionListener(e -> addTrain());
        add(new JLabel());
        add(addButton);

        setVisible(true);
    }

    private void addTrain() {
        String trainNo = trainNoField.getText();
        String trainName = trainNameField.getText();
        String source = sourceField.getText();
        String destination = destinationField.getText();
        String seats = seatsField.getText();

        if (trainNo.isEmpty() || trainName.isEmpty() || source.isEmpty() || destination.isEmpty() || seats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        try (Connection conn = ConnectionManager.getConnection()) {
            String query = "INSERT INTO Train (train_no, train_name, source, destination, available_seats) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(trainNo));
            stmt.setString(2, trainName);
            stmt.setString(3, source);
            stmt.setString(4, destination);
            stmt.setInt(5, Integer.parseInt(seats));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Train added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding train: " + ex.getMessage());
        }
    }
}
