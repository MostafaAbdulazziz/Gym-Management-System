package frontend;

import constants.LoginCredentials;
import constants.fileNames;

import javax.swing.*;

public class TrainerLoginWindow extends JFrame implements LoginCredentials {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private JPanel panel; // Panel to hold components

    public TrainerLoginWindow() {
        // Set up the window
        setTitle("Trainer Login");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        // Create a new panel
        panel = new JPanel();
        panel.setLayout(null); // Using null layout for custom positioning

        // Set starting x and y positions for the components
        int startX = 200; // x-position for all components
        int startY = 200; // starting y-position
        int componentWidth = 200;
        int componentHeight = 50;
        int spacing = 20; // space between components

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(startX, startY, componentWidth, componentHeight);
        panel.add(usernameLabel);

        // Username field
        usernameField = new JTextField();
        usernameField.setBounds(startX, startY + componentHeight + spacing, componentWidth, componentHeight);
        panel.add(usernameField);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(startX, startY + 3 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(startX, startY + 4 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(loginButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(startX, startY + 5 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(backButton);

        // Add the panel to the frame
        add(panel);
    }

    private void setUpButtons() {
        loginButton.addActionListener(e -> {
            // Add functionality for trainer login
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Check if the username and password are correct
            if (username.equals(TRAINER_USERNAME) && password.equals(TRAINER_PASSWORD))
            {
                new TrainerRoleWindow();
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "Invalid username or password. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
                    });
        backButton.addActionListener(e -> {
            new MainWindow();
            dispose();
        });
    }


}
