package frontend;

import backend.ClassDatabase;
import backend.MemberClassRegistrationDatabase;
import backend.MemberDatabase;
import constants.fileNames;

import javax.swing.*;
import java.awt.*;

public class TrainerRoleWindow extends JFrame implements fileNames {
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Trainer.jpg");

    private FuturisticButton addMemberButton;
    private FuturisticButton viewMembersButton;
    private FuturisticButton addClassButton;
    private FuturisticButton viewClassesButton;
    private FuturisticButton registerMemberButton;
    private FuturisticButton cancelRegistrationButton;
    private FuturisticButton viewRegistrationsButton;
    private FuturisticButton logoutButton;

    public TrainerRoleWindow() {
        // Set up the window
        setTitle("Trainer Role");
        setSize(1280, 853);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        memberDatabase = new MemberDatabase(MEMBER_FILENAME);
        classDatabase = new ClassDatabase(CLASS_FILENAME);
        registrationDatabase = new MemberClassRegistrationDatabase(REGISTRATION_FILENAME);
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void setUpButtons() {
        addMemberButton.addActionListener(e -> {
            new AddMemberWindow(memberDatabase);
            dispose();
        });
        viewMembersButton.addActionListener(e -> {
            new ViewMembersWindow(memberDatabase);
            dispose();
        });
        addClassButton.addActionListener(e -> {
            new AddClassWindow(classDatabase);
            dispose();
        });
        viewClassesButton.addActionListener(e -> {
            new ViewClassesWindow(classDatabase);
            dispose();
        });
        registerMemberButton.addActionListener(e -> {
            new RegisterMemberWindow(memberDatabase, classDatabase, registrationDatabase);
            dispose();
        });
        cancelRegistrationButton.addActionListener(e -> {
            new CancelRegistrationWindow(registrationDatabase, memberDatabase, classDatabase);
            dispose();
        });
        viewRegistrationsButton.addActionListener(e -> {
            new ViewRegistrationsWindow(registrationDatabase);
            dispose();
        });
        logoutButton.addActionListener(e -> {
            memberDatabase.saveToFile();
            classDatabase.saveToFile();
            registrationDatabase.saveToFile();
            new MainWindow();
            dispose();
        });
    }

    private void initComponents() {
        // Main panel with background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Initialize buttons for trainer actions
        addMemberButton = new FuturisticButton("Add Member");
        viewMembersButton = new FuturisticButton("View Members");
        addClassButton = new FuturisticButton("Add Class");
        viewClassesButton = new FuturisticButton("View Classes");
        registerMemberButton = new FuturisticButton("Add Member to Class");
        cancelRegistrationButton = new FuturisticButton("Cancel Registration");
        viewRegistrationsButton = new FuturisticButton("View Registrations");
        logoutButton = new FuturisticButton("Logout");

        // Position buttons with absolute positioning
        int startX = 100;
        int startY = 100;
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;

        addMemberButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        viewMembersButton.setBounds(startX, startY + (buttonHeight + spacing), buttonWidth, buttonHeight);
        addClassButton.setBounds(startX, startY + 2 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        viewClassesButton.setBounds(startX, startY + 3 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        registerMemberButton.setBounds(startX, startY + 4 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        cancelRegistrationButton.setBounds(startX, startY + 5 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        viewRegistrationsButton.setBounds(startX, startY + 6 * (buttonHeight + spacing), buttonWidth, buttonHeight);
        logoutButton.setBounds(startX, startY + 7 * (buttonHeight + spacing), buttonWidth, buttonHeight);

        // Add buttons to the panel
        panel.add(addMemberButton);
        panel.add(viewMembersButton);
        panel.add(addClassButton);
        panel.add(viewClassesButton);
        panel.add(registerMemberButton);
        panel.add(cancelRegistrationButton);
        panel.add(viewRegistrationsButton);
        panel.add(logoutButton);

        // Add panel to frame
        add(panel);
    }
}
