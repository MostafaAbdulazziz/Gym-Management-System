package frontend;

import backend.TrainerDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTrainerWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    JButton backButton;
    JButton removeButton;
    JTextField trainerIdField;
    JLabel trainerIdLabel;

    public RemoveTrainerWindow(TrainerDatabase trainerDatabase) {
        this.trainerDatabase = trainerDatabase;
        setTitle("Remove Trainer");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();
        setVisible(true);
    }

    private void setUpButtons() {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trainerId = trainerIdField.getText();


                if(trainerId.isEmpty()) {
                    JOptionPane.showMessageDialog(RemoveTrainerWindow.this, "Please enter a Trainer ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (trainerDatabase.contains(trainerId)) {
                    JOptionPane.showMessageDialog(RemoveTrainerWindow.this, "Trainer removed successfully!");
                    trainerIdField.setText(""); // Clear the field after successful removal
                    trainerDatabase.deleteRecord(trainerId);
                    trainerDatabase.saveToFile();
                } else {
                    JOptionPane.showMessageDialog(RemoveTrainerWindow.this, "Trainer with ID "+trainerIdField.getText()+" not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            new AdminRoleWindow();
            dispose();
        });
    }

    private void initComponents() {
        // Create label and text field for Trainer ID input
        trainerIdLabel = new JLabel("Trainer ID:");
        trainerIdField = new JTextField(20);

        // Create "Remove" and "Back" buttons
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        // Set action listeners for buttons


        backButton.addActionListener(e -> dispose()); // Close the window on "Back" button click

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Position components on the middle-left side
        int startX = 50; // x-position for label and buttons
        int startY = 150; // Starting y-position
        int componentHeight = 30; // Height of each label, text field, and button
        int spacing = 20; // Space between each row

        // Set bounds for each component
        trainerIdLabel.setBounds(startX, startY, 100, componentHeight);
        trainerIdField.setBounds(startX + 100, startY, 200, componentHeight);

        removeButton.setBounds(startX, startY + componentHeight + spacing, 100, componentHeight);
        backButton.setBounds(startX + 120, startY + componentHeight + spacing, 100, componentHeight);

        // Add components to the panel
        panel.add(trainerIdLabel);
        panel.add(trainerIdField);
        panel.add(removeButton);
        panel.add(backButton);

        // Add panel to frame
        add(panel);
    }
}
