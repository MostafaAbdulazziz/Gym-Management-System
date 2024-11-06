package frontend;

import backend.ClassDatabase;
import backend.MemberClassRegistrationDatabase;
import backend.MemberDatabase;

import javax.swing.*;
import java.awt.*;

public class CancelRegistrationWindow extends JFrame {
    private FuturisticButton cancelRegistrationButton;
    private FuturisticButton backButton;
    private JTextField memberIdField;
    private JTextField classIdField;
    private MemberClassRegistrationDatabase registrationDatabase;
    private ClassDatabase classDatabase;
    private MemberDatabase memberDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/dumbles.jpg");

    public CancelRegistrationWindow(MemberClassRegistrationDatabase registrationDatabase, MemberDatabase memberDatabase, ClassDatabase classDatabase) {
        this.registrationDatabase = registrationDatabase;
        this.memberDatabase = memberDatabase;
        this.classDatabase = classDatabase;

        setTitle("Cancel Registration");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set font for labels
        Font labelFont = new Font("Arial", Font.BOLD, 16); // Larger, bold font

        // Member ID Label and Field
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setForeground(Color.WHITE); // Adjust color for visibility
        memberIdLabel.setFont(labelFont); // Apply font to member ID label
        memberIdField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(memberIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(memberIdField, gbc);

        // Class ID Label and Field
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setForeground(Color.WHITE); // Adjust color for visibility
        classIdLabel.setFont(labelFont); // Apply font to class ID label
        classIdField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(classIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(classIdField, gbc);

        // Cancel Registration Button
        cancelRegistrationButton = new FuturisticButton("Cancel Registration");
        cancelRegistrationButton.setPreferredSize(new Dimension(200, 40));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(cancelRegistrationButton, gbc);

        // Back Button
        backButton = new FuturisticButton("Logout");
        backButton.setPreferredSize(new Dimension(200, 40));

        gbc.gridy = 3;
        panel.add(backButton, gbc);

        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        cancelRegistrationButton.addActionListener(e -> {
            String memberId = memberIdField.getText();
            String classId = classIdField.getText();
            if (memberId.isEmpty() || classId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            } else if (!memberDatabase.contains(memberId)) {
                JOptionPane.showMessageDialog(this, "Member ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!classDatabase.contains(classId)) {
                JOptionPane.showMessageDialog(this, "Class ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!registrationDatabase.contains(memberId + "-" + classId)) {
                JOptionPane.showMessageDialog(this, "Member is not registered for this class.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                registrationDatabase.deleteRecord(memberId + "-" + classId);
                JOptionPane.showMessageDialog(this, "The member with ID " + memberId + " has been unregistered from the class with ID " + classId + ".");
                registrationDatabase.saveToFile();
                classDatabase.getRecord(classId).setAvailableSeats(classDatabase.getRecord(classId).getAvailableSeats() + 1);
                classDatabase.saveToFile();
            }
            dispose();
        });

        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });
    }
}
