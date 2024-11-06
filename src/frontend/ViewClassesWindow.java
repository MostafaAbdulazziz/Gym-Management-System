package frontend;

import backend.ClassDatabase;
import backend.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewClassesWindow extends JFrame {
    private ClassDatabase classDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewClassesWindow(ClassDatabase classDatabase) {
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
        String[] columnNames = {"ID", "Name", "Trainer ID", "Duration", "Available Seats"};

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes all cells non-editable
            }
        };

        // Populate table model with class data
        ArrayList<Class> classes = classDatabase.returnAllRecords();
        for (Class classObj : classes) {
            tableModel.addRow(classObj.lineRepresentation().split(","));
        }

        // Create and style the table with transparency and white font color
        JTable classTable = new JTable(tableModel);
        classTable.setOpaque(false);
        classTable.setBackground(new Color(255, 255, 255, 0)); // Fully transparent background
        classTable.setFont(new Font("Arial", Font.PLAIN, 14));
        classTable.setRowHeight(30);
        classTable.setGridColor(new Color(200, 200, 200, 100)); // Slightly transparent grid

        // Header styling with transparency
        classTable.getTableHeader().setOpaque(false);
        classTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); // Semi-transparent blue
        classTable.getTableHeader().setForeground(Color.WHITE);
        classTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Center-align text in cells and set font color to white
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Make cell renderer transparent
        centerRenderer.setForeground(Color.WHITE); // Set text color to white
        for (int i = 0; i < classTable.getColumnCount(); i++) {
            classTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Transparent scroll pane
        JScrollPane scrollPane = new JScrollPane(classTable);
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
            new TrainerRoleWindow();
            dispose();
        });
    }
}
