package frontend;

import backend.ClassDatabase;
import backend.MemberClassRegistration;
import backend.MemberClassRegistrationDatabase;
import backend.MemberDatabase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterMemberWindow extends JFrame {
    private ClassDatabase classDatabase;
    private MemberDatabase memberDatabase;
    private MemberClassRegistrationDatabase memberClassRegistrationDatabase;

    // Declare the text fields and labels
    private JTextField memberIdField;
    private JTextField classIdField;
    private JTextField registrationDateField;
    private JButton registerButton;
    private JButton backButton;
    LocalDate today;

    public RegisterMemberWindow(MemberDatabase memberDatabase, ClassDatabase classDatabase, MemberClassRegistrationDatabase memberClassRegistrationDatabase) {
        this.classDatabase = classDatabase;
        this.memberDatabase = memberDatabase;
        this.memberClassRegistrationDatabase = memberClassRegistrationDatabase;

        // Set up the window
        setTitle("Register Member");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using absolute positioning for custom layout

        // Set starting x, y position and size variables
        int startX = 50;
        int textFieldX = 200;
        int startY = 150;
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 20;

        // Member ID Label and Field
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(startX, startY, 100, componentHeight);
        panel.add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(textFieldX, startY, componentWidth, componentHeight);
        panel.add(memberIdField);

        // Class ID Label and Field
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setBounds(startX, startY + componentHeight + spacing, 100, componentHeight);
        panel.add(classIdLabel);

        classIdField = new JTextField();
        classIdField.setBounds(textFieldX, startY + componentHeight + spacing, componentWidth, componentHeight);
        panel.add(classIdField);

        // Registration Date Label and Field
        JLabel registrationDateLabel = new JLabel("Registration Date:");
        registrationDateLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 150, componentHeight);
        panel.add(registrationDateLabel);

        // Display current date in the format YYYY-MM-DD
         today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        registrationDateField = new JTextField(formattedDate);
        registrationDateField.setBounds(textFieldX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        registrationDateField.setEditable(false); // Make the date field non-editable
        panel.add(registrationDateField);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(textFieldX, startY + 3 * (componentHeight + spacing), 150, 40);
        panel.add(registerButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(startX, startY + 3 * (componentHeight + spacing), 150, 40);
        panel.add(backButton);

        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        // Add functionality to register and back buttons if needed
        registerButton.addActionListener(e -> {
            // Code to register member for class
            String memberId = memberIdField.getText();
            String classId = classIdField.getText();
            String registrationDate = registrationDateField.getText();
            if(memberClassRegistrationDatabase.contains(memberId+"-"+classId)) {
                JOptionPane.showMessageDialog(this, "Member is already registered for this class.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if(!memberDatabase.contains(memberId)) {
                JOptionPane.showMessageDialog(this, "Member ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if(!classDatabase.contains(classId)) {
                JOptionPane.showMessageDialog(this, "Class ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (classDatabase.getRecord(classId).getAvailableSeats() == 0) {
                JOptionPane.showMessageDialog(this, "No Available Seats!", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                memberClassRegistrationDatabase.insertRecord(new MemberClassRegistration(memberId, classId, today,"Active"));
                JOptionPane.showMessageDialog(this, "Member with ID = "+memberId+" registered for class with ID = "+classId+" successfully.");
                memberClassRegistrationDatabase.saveToFile();
            }
        });

        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });
    }
}
