package screens;

import javax.swing.*;

import database.ConnectionManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class BookTicket extends JFrame {
    private JTextField trainNoField, passengerNameField, emailField, seatCountField;

    public BookTicket(int trainNo) {
        setTitle("Book Ticket");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Fields for booking
        trainNoField = new JTextField(String.valueOf(trainNo));
        trainNoField.setEditable(false);
        passengerNameField = new JTextField();
        emailField = new JTextField();
        seatCountField = new JTextField();

        add(new JLabel("Train No:"));
        add(trainNoField);
        add(new JLabel("Passenger Name:"));
        add(passengerNameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Number of Seats:"));
        add(seatCountField);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(e -> bookTicket());
        add(new JLabel());
        add(bookButton);

        setVisible(true);
    }

    private void bookTicket() {
        int trainNo = Integer.parseInt(trainNoField.getText());
        String passengerName = passengerNameField.getText().trim();
        String email = emailField.getText().trim();
        int numberOfSeats;

        try {
            numberOfSeats = Integer.parseInt(seatCountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid seat count.");
            return;
        }

        if (passengerName.isEmpty() || email.isEmpty() || numberOfSeats <= 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.");
            return;
        }

        try (Connection conn = ConnectionManager.getConnection()) {
            // Check seat availability
            String checkSeatsQuery = "SELECT available_seats FROM Train WHERE train_no = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSeatsQuery);
            checkStmt.setInt(1, trainNo);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");

                if (availableSeats < numberOfSeats) {
                    JOptionPane.showMessageDialog(this, "Not enough seats available.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Train not found.");
                return;
            }

            // Deduct seats and book ticket
            String bookTicketQuery = "INSERT INTO Ticket (train_no, passenger_name, email, number_of_seats, booking_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement bookStmt = conn.prepareStatement(bookTicketQuery);
            bookStmt.setInt(1, trainNo);
            bookStmt.setString(2, passengerName);
            bookStmt.setString(3, email);
            bookStmt.setInt(4, numberOfSeats);
            bookStmt.setDate(5, new java.sql.Date(new Date().getTime()));

            bookStmt.executeUpdate();

            String updateSeatsQuery = "UPDATE Train SET available_seats = available_seats - ? WHERE train_no = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSeatsQuery);
            updateStmt.setInt(1, numberOfSeats);
            updateStmt.setInt(2, trainNo);

            updateStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Ticket booked successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while booking ticket: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new BookTicket(12345); // Replace with example train number
    }
}
