package frontend;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/mainWall.jpg");

    public MainWindow() {
        super("Gym Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null);

        
        JLabel buttonsLabel = new JLabel("Welcome");
        buttonsLabel.setBounds(100, 350, 300, 150);
        buttonsLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        buttonsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonsLabel.setVerticalAlignment(SwingConstants.TOP);
        buttonsLabel.setBackground(new Color(255, 255, 255, 150)); 
        buttonsLabel.setOpaque(true);
        mainPanel.add(buttonsLabel);

        
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("src/frontend/4 (1).jpg"));
        imageLabel.setBounds(100, 200, 300, 150);
        mainPanel.add(imageLabel);

        
        JButton trainerRoleButton = new JButton("Trainer Role");
        trainerRoleButton.setBounds(175, 440, 150, 50);
        trainerRoleButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        trainerRoleButton.setBackground(Color.BLACK);
        trainerRoleButton.setForeground(Color.WHITE);
        trainerRoleButton.setOpaque(true);
        mainPanel.add(trainerRoleButton);

        
        JButton adminRoleButton = new JButton("Admin Role");
        adminRoleButton.setBounds(175, 375, 150, 50);
        adminRoleButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        adminRoleButton.setBackground(Color.BLACK);
        adminRoleButton.setForeground(Color.WHITE);
        adminRoleButton.setOpaque(true);
        mainPanel.add(adminRoleButton);

        
        adminRoleButton.addActionListener(e -> {
            new AdminLoginWindow();
            dispose();
        });

        trainerRoleButton.addActionListener(e -> {
            new TrainerLoginWindow();
            dispose();
        });

        
        this.add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
