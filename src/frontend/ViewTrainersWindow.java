package frontend;

import backend.Trainer;
import backend.TrainerDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewTrainersWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    JButton backButton;

    public ViewTrainersWindow(TrainerDatabase trainerDatabase) {

        this.trainerDatabase = trainerDatabase;
        setTitle("View Trainers");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new AdminRoleWindow();
            dispose();
        });
        backButton.setBounds(600, 600, 100, 50);
        this.add(backButton);
        // Column headers
        String[] columnNames = {"ID", "Name", "Email", "Specialty", "Phone Number"};

        // Get data from TrainerDatabase (assume it returns a list of trainers)
        ArrayList<Trainer> trainersData = trainerDatabase.returnAllRecords(); // Adjust this method based on your backend

        // Populate table data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Trainer trainer : trainersData) {
            tableModel.addRow(trainer.lineRepresentation().split(","));
        }

        // Create JTable with the model and set it to be transparent
        JTable table = new JTable(tableModel);
        table.setOpaque(true);
        table.setBackground(new Color(35, 64, 190, 134));
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(true);

        // Set table size
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setFillsViewportHeight(true);

        // Create a JScrollPane to enable row scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);

        // Center the table within the frame
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.add(scrollPane);

        // Add the panel to the frame
        add(tablePanel, BorderLayout.CENTER);
        setVisible(true);
    }

}
