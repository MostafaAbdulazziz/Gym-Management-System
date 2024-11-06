package frontend;

import backend.TrainerDatabase;
import constants.fileNames;

import javax.swing.*;
import java.awt.*;

public class AdminRoleWindow extends JFrame implements fileNames {
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    FuturisticButton addTrainerButton;
    FuturisticButton viewTrainersButton;
    FuturisticButton removeTrainerButton;
    FuturisticButton logoutButton;
    FuturisticButton backButton;
    TrainerDatabase trainerDatabase;

    public AdminRoleWindow() {
        // Set up the window
        setTitle("Admin Role");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();
        setUpButtons();
    }

    private void setUpButtons() {
        addTrainerButton.addActionListener(e -> {
            new AddTrainerWindow(trainerDatabase);
            setVisible(false);
        });
        viewTrainersButton.addActionListener(e -> {
            new ViewTrainersWindow(trainerDatabase);
            setVisible(false);
        });
        removeTrainerButton.addActionListener(e -> {
            new RemoveTrainerWindow(trainerDatabase);
            setVisible(false);
        });
        logoutButton.addActionListener(e -> {
            trainerDatabase.saveToFile();
            new AdminLoginWindow();
            dispose();
        });
        backButton.addActionListener(e -> {
            new AdminLoginWindow();
            dispose();
        });
    }

    private void initComponents() {
        trainerDatabase = new TrainerDatabase(this.TRAINER_FILENAME);

        // Create a main panel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Initialize buttons for admin actions
        addTrainerButton = new FuturisticButton("Add Trainer");
        viewTrainersButton = new FuturisticButton("View Trainers");
        removeTrainerButton = new FuturisticButton("Remove Trainer");
        logoutButton = new FuturisticButton("Logout");
        backButton = new FuturisticButton("Back");

        // Position buttons on the panel with specified bounds
        int startX = 50;
        int startY = 150;
        int buttonSpacing = 20;
        int buttonWidth = 200;
        int buttonHeight = 30;

        addTrainerButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        viewTrainersButton.setBounds(startX, startY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
        removeTrainerButton.setBounds(startX, startY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        logoutButton.setBounds(startX, startY + 3 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        backButton.setBounds(startX, startY + 4 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);

        // Add buttons to the panel
        panel.add(addTrainerButton);
        panel.add(viewTrainersButton);
        panel.add(removeTrainerButton);
        panel.add(logoutButton);
        panel.add(backButton);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}
