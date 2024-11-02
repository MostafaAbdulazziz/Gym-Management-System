package frontend;

import backend.TrainerDatabase;
import constants.fileNames;

import javax.swing.*;
import java.awt.*;

public class AdminRoleWindow extends JFrame implements fileNames {


    JButton addTrainerButton;
    JButton viewTrainersButton;
    JButton removeTrainerButton;
    JButton logoutButton;
    JButton backButton;
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
        // Create buttons for admin actions with specified size
        addTrainerButton = new JButton("Add Trainer");
        viewTrainersButton = new JButton("View Trainers");
        removeTrainerButton = new JButton("Remove Trainer");
        logoutButton = new JButton("Logout");
        backButton = new JButton("Back");

        // Set size for each button
        addTrainerButton.setSize(200, 75);
        viewTrainersButton.setSize(200, 75);
        removeTrainerButton.setSize(200, 75);
        logoutButton.setSize(200, 75);

        // Panel to hold buttons with absolute positioning
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Position buttons on the middle left side of the panel
        int startX = 50; // x-position (left side)
        int startY = 150; // y-position to start placing buttons in the middle
        int buttonSpacing = 20; // Space between buttons

        // Set bounds for each button
        addTrainerButton.setBounds(startX, startY, 200, 75);
        viewTrainersButton.setBounds(startX, startY + 75 + buttonSpacing, 200, 75);
        removeTrainerButton.setBounds(startX, startY + 2 * (75 + buttonSpacing), 200, 75);
        logoutButton.setBounds(startX, startY + 3 * (75 + buttonSpacing), 200, 75);
        backButton.setBounds(startX, startY + 4 * (75 + buttonSpacing), 200, 75);

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
