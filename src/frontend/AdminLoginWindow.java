package frontend;

import constants.LoginCredentials;
import javax.swing.*;
import java.awt.*;

public class AdminLoginWindow extends JFrame implements LoginCredentials {
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private FuturisticButton loginButton;
    private FuturisticButton cancelButton;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Admin.jpg");

    public AdminLoginWindow() {
        setTitle("Admin Login");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel1.setLayout(null);
        panel1.setOpaque(false);

        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 50, 70, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setForeground(Color.WHITE);

        usernameField = new JTextField();
        usernameField.setBounds(100, 50, 200, 30);

        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 100, 70, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passwordLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 200, 30);

        
        loginButton = new FuturisticButton("Login");
        loginButton.setBounds(100, 150, 100, 30);

        cancelButton = new FuturisticButton("Cancel");
        cancelButton.setBounds(220, 150, 100, 30);

        
        panel1.add(usernameLabel);
        panel1.add(usernameField);
        panel1.add(passwordLabel);
        panel1.add(passwordField);
        panel1.add(loginButton);
        panel1.add(cancelButton);

        
        add(panel1);

        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                new AdminRoleWindow();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
            new MainWindow();
        });

        setVisible(true);
    }
}
