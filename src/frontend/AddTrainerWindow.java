package frontend;

import backend.Trainer;
import backend.TrainerDatabase;

import javax.swing.*;
import java.awt.*;
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
    FuturisticButton addButton;
    JPanel panel;
    JTextField specialtyField;
    JLabel specialtyLabel;
    JLabel phoneLabel;
    JTextField phoneField;
    FuturisticButton backButton;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/dumbles.jpg");

    public AddTrainerWindow(TrainerDatabase trainerDatabase) {
        this.trainerDatabase = trainerDatabase;
        setTitle("Add Trainer");
        setSize(600, 500);
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
        // Set up panel with background color
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250)); // Light background color

        // Label font and color
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(245, 245, 248); // Dark blue color

        // Create and style labels and input fields
        idLabel = new JLabel("ID:");
        styleLabel(idLabel, labelFont, labelColor, 50, 50, 100, 30);
        idField = new JFormattedTextField();
        styleTextField(idField, 150, 50, 200, 30);

        nameLabel = new JLabel("Name:");
        styleLabel(nameLabel, labelFont, labelColor, 50, 100, 100, 30);
        nameField = new JTextField();
        styleTextField(nameField, 150, 100, 200, 30);

        emailLabel = new JLabel("Email:");
        styleLabel(emailLabel, labelFont, labelColor, 50, 150, 100, 30);
        emailField = new JTextField();
        styleTextField(emailField, 150, 150, 200, 30);

        specialtyLabel = new JLabel("Specialty:");
        styleLabel(specialtyLabel, labelFont, labelColor, 50, 200, 100, 30);
        specialtyField = new JTextField();
        styleTextField(specialtyField, 150, 200, 200, 30);

        phoneLabel = new JLabel("Phone Number:");
        styleLabel(phoneLabel, labelFont, labelColor, 40, 250, 150, 30);
        phoneField = new JTextField();
        styleTextField(phoneField, 150, 250, 200, 30);

        // Style and position buttons
        addButton = new FuturisticButton("Add");
        styleButton(addButton, 150, 300, 100, 30, new Color(60, 179, 113)); // Green color

        backButton = new FuturisticButton("Back");
        styleButton(backButton, 260, 300, 100, 30, new Color(255, 69, 0)); // Red-orange color

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

    private void styleLabel(JLabel label, Font font, Color color, int x, int y, int width, int height) {
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
    }

    private void styleTextField(JTextField textField, int x, int y, int width, int height) {
        textField.setBounds(x, y, width, height);
        textField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
    }

    private void styleButton(FuturisticButton button, int x, int y, int width, int height, Color color) {
        button.setBounds(x, y, width, height);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }
}
