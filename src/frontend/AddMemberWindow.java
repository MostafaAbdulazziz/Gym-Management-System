package frontend;

import backend.Member;
import backend.MemberDatabase;

import javax.swing.*;

public class AddMemberWindow extends JFrame {
    private MemberDatabase memberDatabase;

    // Declare text fields as private members
    private JTextField nameField;
    private JTextField idField;
    private JTextField emailField;
    private JTextField membershipTypeField;
    private JTextField phoneNumberField;
    private JTextField statusField;
    private JButton addButton;
    private JButton backButton;

    public AddMemberWindow(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
        // Set up the window
        setTitle("Add Member");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using null layout for custom positioning

        // Set starting x and y positions for the components
        int startX = 50; // x-position for labels
        int textFieldX = 200; // x-position for text fields
        int startY = 150; // starting y-position
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 20; // space between components

        // Name label and field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(startX, startY, 100, componentHeight);
        panel.add(nameLabel);

        nameField = new JTextField(); // Initialize the text field
        nameField.setBounds(textFieldX, startY, componentWidth, componentHeight);
        panel.add(nameField);

        // ID label and field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(startX, startY + (componentHeight + spacing), 100, componentHeight);
        panel.add(idLabel);

        idField = new JTextField(); // Initialize the text field
        idField.setBounds(textFieldX, startY + (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(idField);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 100, componentHeight);
        panel.add(emailLabel);

        emailField = new JTextField(); // Initialize the text field
        emailField.setBounds(textFieldX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(emailField);

        // Membership Type label and field
        JLabel membershipTypeLabel = new JLabel("Membership Type:");
        membershipTypeLabel.setBounds(startX, startY + 3 * (componentHeight + spacing), 150, componentHeight);
        panel.add(membershipTypeLabel);

        membershipTypeField = new JTextField(); // Initialize the text field
        membershipTypeField.setBounds(textFieldX, startY + 3 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(membershipTypeField);

        // Phone Number label and field
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(startX, startY + 4 * (componentHeight + spacing), 100, componentHeight);
        panel.add(phoneNumberLabel);

        phoneNumberField = new JTextField(); // Initialize the text field
        phoneNumberField.setBounds(textFieldX, startY + 4 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(phoneNumberField);

        // Status label and field
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(startX, startY + 5 * (componentHeight + spacing), 100, componentHeight);
        panel.add(statusLabel);

        statusField = new JTextField(); // Initialize the text field
        statusField.setBounds(textFieldX, startY + 5 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(statusField);

        // Add button
        addButton = new JButton("Add");
        addButton.setBounds(startX, startY + 6 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(addButton);
        backButton = new JButton("Back");
        backButton.setBounds(startX+250, startY + 6 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(backButton);
        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });

        // Add action listeners for buttons here
        addButton.addActionListener(e -> {
            // Get the values from the text fields
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String membershipType = membershipTypeField.getText();
            String phoneNumber = phoneNumberField.getText();
            String status = statusField.getText();

            if(memberDatabase.contains(id)) {
                JOptionPane.showMessageDialog(this,
                        "Member with ID " + id + " already exists",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (name.isEmpty() || id.isEmpty() || email.isEmpty() || membershipType.isEmpty() || phoneNumber.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            memberDatabase.insertRecord(new Member(id, name, membershipType, email, phoneNumber, status));
            JOptionPane.showMessageDialog(this,
                    "Member with ID = "+id+" added successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            memberDatabase.saveToFile();


            // Clear the text fields
            nameField.setText("");
            idField.setText("");
            emailField.setText("");
            membershipTypeField.setText("");
            phoneNumberField.setText("");
            statusField.setText("");
        });
    }
}

