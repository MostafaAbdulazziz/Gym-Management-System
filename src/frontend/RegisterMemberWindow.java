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
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/dumbles.jpg");
    private ClassDatabase classDatabase;
    private MemberDatabase memberDatabase;
    private MemberClassRegistrationDatabase memberClassRegistrationDatabase;

    
    private JTextField memberIdField;
    private JTextField classIdField;
    private JTextField registrationDateField;
    private FuturisticButton registerButton;
    private FuturisticButton backButton;
    private LocalDate today;

    public RegisterMemberWindow(MemberDatabase memberDatabase, ClassDatabase classDatabase, MemberClassRegistrationDatabase memberClassRegistrationDatabase) {
        this.classDatabase = classDatabase;
        this.memberDatabase = memberDatabase;
        this.memberClassRegistrationDatabase = memberClassRegistrationDatabase;

        
        setTitle("Register Member");
        setSize(500, 400);
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
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(234, 234, 243);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel memberIdLabel = new JLabel("Member ID:");
        memberIdLabel.setForeground(Color.WHITE);
        memberIdLabel.setFont(labelFont);
        memberIdField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(memberIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(memberIdField, gbc);

        
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setForeground(Color.WHITE);
        classIdLabel.setFont(labelFont);
        classIdField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(classIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(classIdField, gbc);

        
        JLabel registrationDateLabel = new JLabel("Registration Date:");
        registrationDateLabel.setForeground(labelColor); 
        registrationDateLabel.setFont(labelFont);
        today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrationDateField = new JTextField(formattedDate);
        registrationDateField.setEditable(true); 

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(registrationDateLabel, gbc);

        gbc.gridx = 1;
        panel.add(registrationDateField, gbc);

        
        registerButton = new FuturisticButton("Register");
        backButton = new FuturisticButton("Back");

        Dimension buttonSize = new Dimension(150, 40);
        registerButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        gbc.gridx = 1;
        panel.add(backButton, gbc);

        
        add(panel);
    }

    private void setUpButtons() {
        registerButton.addActionListener(e -> {
            String memberId = memberIdField.getText();
            String classId = classIdField.getText();
            String registrationDate = registrationDateField.getText();

            if (memberClassRegistrationDatabase.contains(memberId + "-" + classId)) {
                JOptionPane.showMessageDialog(this, "Member is already registered for this class.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!memberDatabase.contains(memberId)) {
                JOptionPane.showMessageDialog(this, "Member ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!classDatabase.contains(classId)) {
                JOptionPane.showMessageDialog(this, "Class ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (classDatabase.getRecord(classId).getAvailableSeats() == 0) {
                JOptionPane.showMessageDialog(this, "No Available Seats!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                
                LocalDate date = LocalDate.parse(registrationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                memberClassRegistrationDatabase.insertRecord(new MemberClassRegistration(memberId, classId, date, "Active"));
                JOptionPane.showMessageDialog(this, "Member with ID = " + memberId + " registered for class with ID = " + classId + " successfully.");
                memberClassRegistrationDatabase.saveToFile();
            }
        });

        backButton.addActionListener(e -> {
            new TrainerRoleWindow();
            dispose();
        });
    }
}
