package frontend;

import backend.ClassDatabase;
import backend.MemberClassRegistrationDatabase;
import backend.MemberDatabase;

import javax.swing.*;

public class CancelRegistrationWindow extends JFrame {
    private JButton cancelRegistrationButton;
    private JButton backButton;
    private JTextField memberIdField;
    private JTextField classIdField;
    private MemberClassRegistrationDatabase registrationDatabase;
    private ClassDatabase classDatabase;
    private MemberDatabase memberDatabase;

    public CancelRegistrationWindow(MemberClassRegistrationDatabase registrationDatabase, MemberDatabase memberDatabase, ClassDatabase classDatabase) {
        this.registrationDatabase = registrationDatabase;
        this.memberDatabase = memberDatabase;
        this.classDatabase = classDatabase;

        setTitle("Cancel Registration");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel for layout management
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using null layout for absolute positioning

        // Set starting x, y position and size variables for the fields
        int startX = 50; // x-position for labels and fields
        int startY = 100; // starting y-position for fields
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 40; // space between components

        // Member ID Label and Field
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setBounds(startX, startY, 100, componentHeight);
        panel.add(memberIdLabel);

        memberIdField = new JTextField();
        memberIdField.setBounds(startX + 100, startY, componentWidth, componentHeight);
        panel.add(memberIdField);

        // Class ID Label and Field
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setBounds(startX, startY + spacing, 100, componentHeight);
        panel.add(classIdLabel);

        classIdField = new JTextField();
        classIdField.setBounds(startX + 100, startY + spacing, componentWidth, componentHeight);
        panel.add(classIdField);

        // Cancel Registration Button
        cancelRegistrationButton = new JButton("Cancel Registration");
        cancelRegistrationButton.setBounds(startX, startY + 2 * spacing + 20, componentWidth, 50);
        panel.add(cancelRegistrationButton);

        // Back Button
        backButton = new JButton("Logout");
        backButton.setBounds(startX, startY + 3 * spacing + 30, componentWidth, 50);
        panel.add(backButton);

        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        cancelRegistrationButton.addActionListener(e -> {
            // Add functionality for cancelling registration
            String memberId = memberIdField.getText();
            String classId = classIdField.getText();
            if(memberId.isEmpty() || classId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            } else if (!memberDatabase.contains(memberId)) {
                JOptionPane.showMessageDialog(this, "Member ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!classDatabase.contains(classId)) {
                JOptionPane.showMessageDialog(this, "Class ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (!registrationDatabase.contains(memberId+"-"+ classId)) {
                JOptionPane.showMessageDialog(this, "Member is not registered for this class.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                registrationDatabase.deleteRecord(memberId+"-"+ classId);
                JOptionPane.showMessageDialog(this, "The member with ID " + memberId + " has been unregistered from the class with ID " + classId + ".");
                registrationDatabase.saveToFile();
                classDatabase.getRecord(classId).setAvailableSeats(classDatabase.getRecord(classId).getAvailableSeats() + 1);
                classDatabase.saveToFile();
            }


            dispose();
        });

        backButton.addActionListener(e -> {
            // Add functionality for logging out
            new TrainerRoleWindow();
            dispose();
        });
    }
}
