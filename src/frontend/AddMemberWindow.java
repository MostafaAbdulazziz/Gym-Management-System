package frontend;

import backend.Member;
import backend.MemberDatabase;

import javax.swing.*;
import java.awt.*;

public class AddMemberWindow extends JFrame {
    private MemberDatabase memberDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Member.jpg");

    private JTextField nameField;
    private JTextField idField;
    private JTextField emailField;
    private JTextField membershipTypeField;
    private JTextField phoneNumberField;
    private JTextField statusField;
    private FuturisticButton addButton;
    private FuturisticButton backButton;

    public AddMemberWindow(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
        
        setTitle("Add Member");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); 

        
        int startX = 50;
        int textFieldX = 200;
        int startY = 150;
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 20;

        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(startX, startY, 100, componentHeight);
        nameLabel.setForeground(Color.WHITE); 
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(textFieldX, startY, componentWidth, componentHeight);
        panel.add(nameField);

        
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(startX, startY + (componentHeight + spacing), 100, componentHeight);
        idLabel.setForeground(Color.WHITE);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(textFieldX, startY + (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(idField);

        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 100, componentHeight);
        emailLabel.setForeground(Color.WHITE);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(textFieldX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(emailField);

        
        JLabel membershipTypeLabel = new JLabel("Membership Type:");
        membershipTypeLabel.setBounds(startX, startY + 3 * (componentHeight + spacing), 150, componentHeight);
        membershipTypeLabel.setForeground(Color.WHITE);
        panel.add(membershipTypeLabel);

        membershipTypeField = new JTextField();
        membershipTypeField.setBounds(textFieldX, startY + 3 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(membershipTypeField);

        
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(startX, startY + 4 * (componentHeight + spacing), 100, componentHeight);
        phoneNumberLabel.setForeground(Color.WHITE);
        panel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(textFieldX, startY + 4 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(phoneNumberField);

        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(startX, startY + 5 * (componentHeight + spacing), 100, componentHeight);
        statusLabel.setForeground(Color.WHITE);
        panel.add(statusLabel);

        statusField = new JTextField();
        statusField.setBounds(textFieldX, startY + 5 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(statusField);

        
        addButton = new FuturisticButton("Add");
        addButton.setBounds(startX, startY + 6 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(addButton);

        
        backButton = new FuturisticButton("Back");
        backButton.setBounds(startX + 250, startY + 6 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(backButton);

        
        add(panel);
    }

    private void setUpButtons() {
        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String membershipType = membershipTypeField.getText();
            String phoneNumber = phoneNumberField.getText();
            String status = statusField.getText();

            if (memberDatabase.contains(id)) {
                JOptionPane.showMessageDialog(this,
                        "Member with ID " + id + " already exists",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
           else if (name.isEmpty() || id.isEmpty() || email.isEmpty() || membershipType.isEmpty() || phoneNumber.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            else if (!Validations.isNameValid(name)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid name, must not contain digits",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            else if (!Validations.isValidMemberId(id)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid member ID, must start with 'M' and be 4 characters long",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            else if (!Validations.isEmailValid(email)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid email, must contain '@' and '.'",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            else if (!Validations.isPhoneValid(phoneNumber)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid phone number, must be 10 digits long",
                        "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!Validations.isNameValid(membershipType)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid membership type, must not contain digits",
                        "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!Validations.isNameValid(status)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid status, must not contain digits",
                        "Error", JOptionPane.ERROR_MESSAGE);


            } else {
                memberDatabase.insertRecord(new Member(id, name, membershipType, email, phoneNumber, status));
                JOptionPane.showMessageDialog(this,
                        "Member with ID = " + id + " added successfully",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                memberDatabase.saveToFile();

                
                nameField.setText("");
                idField.setText("");
                emailField.setText("");
                membershipTypeField.setText("");
                phoneNumberField.setText("");
                statusField.setText("");
            }
        });
    }
}
