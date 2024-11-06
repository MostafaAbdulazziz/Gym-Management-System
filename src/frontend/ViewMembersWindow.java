package frontend;

import backend.Member;
import backend.MemberDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewMembersWindow extends JFrame {
    private MemberDatabase memberDatabase;
    private JTable memberTable;
    private FuturisticButton backButton;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewMembersWindow(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
        setTitle("View Members");
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

        // Table column names
        String[] columnNames = {"ID", "Name", "Membership Type", "Email", "Phone Number", "Status"};

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Populate table model with member data
        ArrayList<Member> members = memberDatabase.returnAllRecords();
        for (Member member : members) {
            tableModel.addRow(member.lineRepresentation().split(","));
        }

        // Create and style the table with transparency and white font color
        memberTable = new JTable(tableModel);
        memberTable.setOpaque(false);
        memberTable.setBackground(new Color(255, 255, 255, 0)); // Fully transparent background
        memberTable.setFont(new Font("Arial", Font.PLAIN, 14));
        memberTable.setRowHeight(30);
        memberTable.setGridColor(new Color(200, 200, 200, 100)); // Slightly transparent grid

        // Header styling with transparency
        memberTable.getTableHeader().setOpaque(false);
        memberTable.getTableHeader().setBackground(new Color(70, 130, 180, 150)); // Semi-transparent blue
        memberTable.getTableHeader().setForeground(Color.WHITE);
        memberTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Center-align text in cells and set font color to white
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Make cell renderer transparent
        centerRenderer.setForeground(Color.WHITE); // Set text color to white
        for (int i = 0; i < memberTable.getColumnCount(); i++) {
            memberTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Transparent scroll pane
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button styling
        backButton = new FuturisticButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(70, 130, 180, 200)); // Semi-transparent blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());

        // Button panel
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
