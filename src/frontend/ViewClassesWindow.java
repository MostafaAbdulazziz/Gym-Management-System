package frontend;

import backend.ClassDatabase;
import backend.Class; // Make sure to import the Class model
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewClassesWindow extends JFrame {
    private ClassDatabase classDatabase; // Store the database reference

    public ViewClassesWindow(ClassDatabase classDatabase) {
        // Set up the window
        this.classDatabase = classDatabase;
        setTitle("View Classes");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel for the table
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create column names
        String[] columnNames = {"ID", "Name", "Trainer ID", "Duration", "Available Seats"};

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes the table non-editable
            }
        };

        // Populate the table model with class data
        ArrayList<Class> classes = classDatabase.returnAllRecords();
        for (Class classObj : classes) {
            // Assuming Class has appropriate getters for its fields
            tableModel.addRow(classObj.lineRepresentation().split(","));
        }

        // Create the table with the model
        JTable classTable = new JTable(tableModel);
        classTable.setFillsViewportHeight(true);
        classTable.setPreferredScrollableViewportSize(new Dimension(1000, 600)); // Set the table size

        // Add the table to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(classTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create the Back button
        JButton backButton = new JButton("Back");
        backButton.setSize(150, 50);
        backButton.addActionListener(e -> {
            // Code to handle going back
           new TrainerRoleWindow(); // Open the TrainerRoleWindow

            dispose(); // Close the current window
        });

        // Add the button to the bottom center of the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);
    }
}
