package frontend;

import backend.Member;
import backend.MemberDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewMembersWindow extends JFrame {
    private MemberDatabase memberDatabase;
    private JTable memberTable; // Table to display member data
    private JButton backButton; // Button to go back

    public ViewMembersWindow(MemberDatabase memberDatabase) {
        // Set up the window
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
        // Create a panel for the table and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create column names
        String[] columnNames = {"ID", "Name", "Membership Type", "Email", "Phone Number", "Status"};

        // Create a table model and override the isCellEditable method
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Populate the table model with member data
        ArrayList<Member> members = memberDatabase.returnAllRecords();
        for (Member member : members) {
            tableModel.addRow(member.lineRepresentation().split(","));
        }

        // Create the table with the model and set the preferred size
        memberTable = new JTable(tableModel);
        memberTable.setPreferredScrollableViewportSize(new Dimension(1000, 600));
        memberTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(memberTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create the back button
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50)); // Set button size

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        // Add the button panel to the bottom of the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(panel);

        // Set up action for back button
        backButton.addActionListener(e -> {
           new TrainerRoleWindow();
            dispose(); // Close the current window
        });
    }
}
