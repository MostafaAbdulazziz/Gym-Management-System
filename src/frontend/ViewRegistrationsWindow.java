package frontend;

import backend.MemberClassRegistrationDatabase;
import backend.MemberClassRegistration;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewRegistrationsWindow extends JFrame {
    private MemberClassRegistrationDatabase registrationDatabase;
    private JTable registrationTable;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewRegistrationsWindow(MemberClassRegistrationDatabase registrationDatabase) {
        this.registrationDatabase = registrationDatabase;

        setTitle("View Registrations");
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
        String[] columnNames = {"Member ID", "Class ID", "Registration Date"};

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Populate table model with registration data
        ArrayList<MemberClassRegistration> registrations = registrationDatabase.returnAllRecords();
        for (MemberClassRegistration registration : registrations) {
            tableModel.addRow(registration.lineRepresentation().split(","));
        }

        // Create and style the table with transparency and white font color
        registrationTable = new JTable(tableModel);
        registrationTable.setOpaque(false);
        registrationTable.setBackground(new Color(255, 255, 255, 0)); // Fully transparent background
        registrationTable.setFont(new Font("Arial", Font.PLAIN, 14));
        registrationTable.setRowHeight(30);
        registrationTable.setGridColor(new Color(200, 200, 200, 100)); // Slightly transparent grid

        // Header styling with transparency
        registrationTable.getTableHeader().setOpaque(false);
        registrationTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); // Semi-transparent blue
        registrationTable.getTableHeader().setForeground(Color.WHITE);
        registrationTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Center-align text in cells and set font color to white
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Make cell renderer transparent
        centerRenderer.setForeground(Color.WHITE); // Set text color to white
        for (int i = 0; i < registrationTable.getColumnCount(); i++) {
            registrationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Transparent scroll pane
        JScrollPane scrollPane = new JScrollPane(registrationTable);
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
