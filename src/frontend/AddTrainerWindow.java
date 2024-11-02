package frontend;

import backend.Trainer;
import backend.TrainerDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTrainerWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    JLabel idLabel;
    JFormattedTextField idField;
    JLabel nameLabel;
    JTextField nameField;
    JLabel emailLabel;
    JTextField emailField;
    JButton addButton;
    JPanel panel;
    JTextField specialtyField;
    JLabel specialtyLabel;
    JLabel phoneLabel;
    JTextField phoneField;
    JButton backButton;


    public AddTrainerWindow(TrainerDatabase trainerDatabase) {
        this.trainerDatabase = trainerDatabase;
        setTitle("Add Trainer");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();
    }

    private void setUpButtons() {
        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String specialty = specialtyField.getText();
                String phone = phoneField.getText();

                // Check if any field is empty
                if (id.isEmpty() || name.isEmpty() || email.isEmpty() || specialty.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    if (trainerDatabase.contains(id)) {
                        JOptionPane.showMessageDialog(null, "Trainer with ID " + id + " already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    trainerDatabase.insertRecord(new Trainer(id, name, email, specialty, phone));
                    JOptionPane.showMessageDialog(null, "Trainer added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    trainerDatabase.saveToFile();

                    // Clear fields
                    idField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    specialtyField.setText("");
                    phoneField.setText("");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainerDatabase.saveToFile();
                dispose();
                new AdminRoleWindow();
            }
        });
    }

    private void initComponents() {
        // Create input fields and labels
        idLabel = new JLabel("ID:");
        idField = new JFormattedTextField();
        idField.setColumns(20);

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        specialtyLabel = new JLabel("Specialty:");
        specialtyField = new JTextField(20);

        phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);

        // Add button
        addButton = new JButton("Add");
        backButton = new JButton("Back");

        // Panel to hold components
        panel = new JPanel();
        panel.setLayout(null);

        // Position components on the middle-left side
        int startX = 50; // x-position for labels
        int fieldX = 150; // x-position for text fields
        int startY = 150; // Starting y-position for the first label
        int componentHeight = 30; // Height of each label and text field
        int spacing = 20; // Space between each row

        // Set bounds for labels and fields
        idLabel.setBounds(startX, startY, 100, componentHeight);
        idField.setBounds(fieldX, startY, 200, componentHeight);

        nameLabel.setBounds(startX, startY + componentHeight + spacing, 100, componentHeight);
        nameField.setBounds(fieldX, startY + componentHeight + spacing, 200, componentHeight);

        emailLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 100, componentHeight);
        emailField.setBounds(fieldX, startY + 2 * (componentHeight + spacing), 200, componentHeight);

        specialtyLabel.setBounds(startX, startY + 3 * (componentHeight + spacing), 100, componentHeight);
        specialtyField.setBounds(fieldX, startY + 3 * (componentHeight + spacing), 200, componentHeight);

        phoneLabel.setBounds(startX, startY + 4 * (componentHeight + spacing), 100, componentHeight);
        phoneField.setBounds(fieldX, startY + 4 * (componentHeight + spacing), 200, componentHeight);

        // Set bounds for the add button and position it below the fields
        addButton.setBounds(startX+200, startY + 5 * (componentHeight + spacing), 100, componentHeight);
        backButton.setBounds(startX+100, startY + 5 * (componentHeight + spacing), 100, componentHeight);
        // Add components to the panel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(specialtyLabel);
        panel.add(specialtyField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(addButton);
        panel.add(backButton);

        // Add panel to frame
        add(panel);
        setVisible(true);
    }
}
