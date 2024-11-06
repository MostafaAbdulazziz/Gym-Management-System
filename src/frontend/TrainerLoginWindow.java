package frontend;

import constants.LoginCredentials;
import constants.fileNames;

import javax.swing.*;
import java.awt.*;

public class TrainerLoginWindow extends JFrame implements LoginCredentials {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private FuturisticButton loginButton;
    private FuturisticButton backButton;
    private JPanel panel; 
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/T.jpg");

    public TrainerLoginWindow() {
        
        setTitle("Trainer Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        initComponents();
        setUpButtons();

        setVisible(true);
    }

    private void initComponents() {
        
         panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250)); 

        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(218, 165, 44); 

        
        int startX = 50; 
        int startY = 50; 
        int labelWidth = 80; 
        int fieldWidth = 200; 
        int componentHeight = 30;
        int spacing = 20; 

        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(labelColor);
        usernameLabel.setBounds(startX, startY, labelWidth, componentHeight);
        panel.add(usernameLabel);

        
        usernameField = new JTextField();
        usernameField.setBounds(startX + labelWidth + 10, startY, fieldWidth, componentHeight);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        panel.add(usernameField);

        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(labelColor);
        passwordLabel.setBounds(startX, startY + componentHeight + spacing, labelWidth, componentHeight);
        panel.add(passwordLabel);

        
        passwordField = new JPasswordField();
        passwordField.setBounds(startX + labelWidth + 10, startY + componentHeight + spacing, fieldWidth, componentHeight);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        panel.add(passwordField);

        
        loginButton = new FuturisticButton("Login");
        styleButton(loginButton, startX + labelWidth + 10, startY + 2 * (componentHeight + spacing), fieldWidth, componentHeight);

        
        backButton = new FuturisticButton("Back");
        styleButton(backButton, startX + labelWidth + 10, startY + 3 * (componentHeight + spacing), fieldWidth, componentHeight);

        
        add(panel);
    }

    private void setUpButtons() {
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(TRAINER_USERNAME) && password.equals(TRAINER_PASSWORD)) {
                new TrainerRoleWindow();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
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

    private void styleButton(FuturisticButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        panel.add(button);
    }
}
