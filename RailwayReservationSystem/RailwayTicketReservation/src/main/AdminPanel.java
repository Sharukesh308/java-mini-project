package main;

import javax.swing.*;

import admin.AddTrain;
import admin.AdminLogin;
import admin.ViewBookings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame {

    public AdminPanel() {
        // Set up the frame
        setTitle("Admin Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Create buttons for various admin actions
        JButton loginButton = new JButton("Admin Login");
        JButton manageTrainButton = new JButton("Manage Trains");
        JButton viewBookingsButton = new JButton("View Bookings");
        JButton logoutButton = new JButton("Logout");
        JButton exitButton = new JButton("Exit");

        // Button actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show login screen
                new AdminLogin();
                dispose();
            }
        });

        manageTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show add train screen
                new AddTrain();
                dispose();
            }
        });

        viewBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show view bookings screen
                new ViewBookings();
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the login screen again
                new AdminLogin();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Add buttons to the panel
        add(loginButton);
        add(manageTrainButton);
        add(viewBookingsButton);
        add(logoutButton);
        add(exitButton);

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}

