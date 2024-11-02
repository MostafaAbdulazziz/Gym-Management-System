package frontend;

import backend.ClassDatabase;
import backend.MemberClassRegistrationDatabase;
import backend.MemberDatabase;
import backend.TrainerDatabase;
import constants.fileNames;

import javax.swing.*;

public class TrainerRoleWindow extends JFrame implements fileNames {
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;

    private JButton addMemberButton;
    private JButton viewMembersButton;
    private JButton addClassButton;
    private JButton viewClassesButton;
    private JButton registerMemberButton;
    private JButton cancelRegistrationButton;
    private JButton viewRegistrationsButton;
    private JButton logoutButton;

    public TrainerRoleWindow() {
        // Set up the window
        setTitle("Trainer Role");
        setSize(1300, 700);
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
            // Add functionality for adding a member
            new AddMemberWindow(memberDatabase);
            dispose();
        });
        viewMembersButton.addActionListener(e -> {
            // Add functionality for viewing members
            new ViewMembersWindow(memberDatabase);
            dispose();
        });
        addClassButton.addActionListener(e -> {
            // Add functionality for adding a class
            new AddClassWindow(classDatabase);
            dispose();
        });
        viewClassesButton.addActionListener(e -> {
            // Add functionality for viewing classes
            new ViewClassesWindow(classDatabase);
            dispose();});
        registerMemberButton.addActionListener(e -> {
            // Add functionality for registering a member for a class
            new RegisterMemberWindow(memberDatabase, classDatabase, registrationDatabase);
            dispose();
        });
        cancelRegistrationButton.addActionListener(e -> {
            // Add functionality for cancelling a registration
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
        // Create buttons for trainer actions with specified labels and sizes
        addMemberButton = new JButton("Add Member");
        viewMembersButton = new JButton("View Members");
        addClassButton = new JButton("Add Class");
        viewClassesButton = new JButton("View Classes");
        registerMemberButton = new JButton("Register Member for Class");
        cancelRegistrationButton = new JButton("Cancel Registration");
        viewRegistrationsButton = new JButton("View Registrations");
        logoutButton = new JButton("Logout");

        // Panel for buttons layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Positioning buttons on the middle-left of the frame
        int startX = 100; // x-position for buttons
        int startY = 100; // Starting y-position
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20; // Space between each button

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
