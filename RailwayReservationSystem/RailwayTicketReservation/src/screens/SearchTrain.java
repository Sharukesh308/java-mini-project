package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.ConnectionManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchTrain extends JFrame {
    private JTextField sourceField, destinationField;
    private JTable trainTable;

    public SearchTrain() {
        setTitle("Search Train");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Train"));

        sourceField = new JTextField();
        destinationField = new JTextField();

        searchPanel.add(new JLabel("Source:"));
        searchPanel.add(sourceField);
        searchPanel.add(new JLabel("Destination:"));
        searchPanel.add(destinationField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchTrains());
        searchPanel.add(new JLabel());
        searchPanel.add(searchButton);

        // Train table
        trainTable = new JTable(new DefaultTableModel(new Object[]{"Train No", "Train Name", "Source", "Destination", "Available Seats"}, 0));
        JScrollPane scrollPane = new JScrollPane(trainTable);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void searchTrains() {
        String source = sourceField.getText().trim();
        String destination = destinationField.getText().trim();

        if (source.isEmpty() || destination.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Source and Destination cannot be empty!");
            return;
        }

        try (Connection conn = ConnectionManager.getConnection()) {
            String query = "SELECT train_no, train_name, source, destination, available_seats FROM Train WHERE source = ? AND destination = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, source);
            stmt.setString(2, destination);

            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) trainTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("train_no"),
                        rs.getString("train_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getInt("available_seats")
                });
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No trains found for the selected route.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while searching trains: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new SearchTrain();
    }
}
