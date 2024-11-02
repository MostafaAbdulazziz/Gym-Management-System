package frontend;

import backend.MemberClassRegistrationDatabase;
import backend.MemberClassRegistration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewRegistrationsWindow extends JFrame {
    private MemberClassRegistrationDatabase registrationDatabase;
    private JTable registrationTable;
    private JButton backButton;

    public ViewRegistrationsWindow(MemberClassRegistrationDatabase registrationDatabase) {
        this.registrationDatabase = registrationDatabase;

        setTitle("View Registrations");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel for the table and button
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create column names
        String[] columnNames = {"Member ID", "Class ID", "Registration Date"};

        // Create a table model and make the table non-editable
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };

        // Populate the table model with registration data
        ArrayList<MemberClassRegistration> registrations = registrationDatabase.returnAllRecords();
        for (MemberClassRegistration registration : registrations) {
            tableModel.addRow(registration.lineRepresentation().split(",")); // Add a row with the registration data

        }

        // Create the table with the model
        registrationTable = new JTable(tableModel);
        registrationTable.setPreferredScrollableViewportSize(new Dimension(1000, 600));
        registrationTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create and add the "Back" button at the bottom center
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });
    }
}
