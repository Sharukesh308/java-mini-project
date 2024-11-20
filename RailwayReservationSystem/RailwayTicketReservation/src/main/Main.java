package main;

import screens.SearchTrain;
import screens.BookTicket;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        setTitle("Train Ticket Booking System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create buttons for navigation
        JButton searchTrainButton = new JButton("Search Trains");
        JButton bookTicketButton = new JButton("Book Ticket");
        JButton adminPanelButton = new JButton("Admin Panel");
        JButton exitButton = new JButton("Exit");
        
        // Set bounds for buttons
        searchTrainButton.setBounds(100, 50, 200, 30);
        bookTicketButton.setBounds(100, 100, 200, 30);
        adminPanelButton.setBounds(100, 150, 200, 30);
        exitButton.setBounds(100, 200, 200, 30);

        // Add action listeners
        searchTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchTrain();
            }
        });

        bookTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trainNoStr = JOptionPane.showInputDialog(null, "Enter Train Number to Book:");
                if (trainNoStr != null) {
                    try {
                        int trainNo = Integer.parseInt(trainNoStr);
                        new BookTicket(trainNo);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Train Number.");
                    }
                }
            }
        });

        adminPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with admin login check if required
                new AdminPanel();
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

        // Add buttons to the frame
        add(searchTrainButton);
        add(bookTicketButton);
        add(adminPanelButton);
        add(exitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

