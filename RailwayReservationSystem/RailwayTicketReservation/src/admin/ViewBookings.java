package admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.ConnectionManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBookings extends JFrame {

    public ViewBookings() {
        setTitle("View Bookings");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table for bookings
        JTable bookingTable = new JTable(new DefaultTableModel(new Object[]{"Booking ID", "Train No", "Passenger Name", "Email", "Seats", "Date"}, 0));
        JScrollPane scrollPane = new JScrollPane(bookingTable);

        add(scrollPane, BorderLayout.CENTER);

        loadBookings((DefaultTableModel) bookingTable.getModel());

        setVisible(true);
    }

    private void loadBookings(DefaultTableModel model) {
        try (Connection conn = ConnectionManager.getConnection()) {
            String query = "SELECT * FROM Ticket";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ticket_id"),
                        rs.getInt("train_no"),
                        rs.getString("passenger_name"),
                        rs.getString("email"),
                        rs.getInt("number_of_seats"),
                        rs.getDate("booking_date")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + ex.getMessage());
        }
    }
}
