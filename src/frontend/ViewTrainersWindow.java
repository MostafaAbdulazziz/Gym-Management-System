package frontend;

import backend.Trainer;
import backend.TrainerDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewTrainersWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

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
        // Main panel with background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Email", "Specialty", "Phone Number"};

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Populate table model with trainer data
        ArrayList<Trainer> trainersData = trainerDatabase.returnAllRecords();
        for (Trainer trainer : trainersData) {
            tableModel.addRow(trainer.lineRepresentation().split(","));
        }

        // Create and style the table with transparency and white font color
        JTable trainerTable = new JTable(tableModel);
        trainerTable.setOpaque(false);
        trainerTable.setBackground(new Color(255, 255, 255, 0)); // Fully transparent background
        trainerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        trainerTable.setRowHeight(30);
        trainerTable.setGridColor(new Color(200, 200, 200, 100)); // Slightly transparent grid

        // Header styling with transparency
        trainerTable.getTableHeader().setOpaque(false);
        trainerTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); // Semi-transparent blue
        trainerTable.getTableHeader().setForeground(Color.WHITE);
        trainerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Center-align text in cells and set font color to white
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Make cell renderer transparent
        centerRenderer.setForeground(Color.WHITE); // Set text color to white
        for (int i = 0; i < trainerTable.getColumnCount(); i++) {
            trainerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Transparent scroll pane
        JScrollPane scrollPane = new JScrollPane(trainerTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button styling
        FuturisticButton backButton = new FuturisticButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(60, 120, 180, 200)); // Semi-transparent blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());

        // Back button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Transparent to show background
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Back button action
        backButton.addActionListener(e -> {
            new AdminRoleWindow();
            dispose();
        });
    }
}
