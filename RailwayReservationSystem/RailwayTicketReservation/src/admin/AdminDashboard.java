package admin;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Buttons for navigation
        JButton addTrainButton = new JButton("Add Train");
        JButton viewBookingsButton = new JButton("View Bookings");
        JButton logoutButton = new JButton("Logout");

        addTrainButton.addActionListener(e -> new AddTrain());
        viewBookingsButton.addActionListener(e -> new ViewBookings());
        logoutButton.addActionListener(e -> {
            new AdminLogin();
            dispose();
        });

        add(addTrainButton);
        add(viewBookingsButton);
        add(logoutButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
