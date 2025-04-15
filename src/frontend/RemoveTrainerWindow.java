package frontend;

import backend.TrainerDatabase;

import javax.swing.*;
import java.awt.*;

public class RemoveTrainerWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    FuturisticButton backButton;
    FuturisticButton removeButton;
    JTextField trainerIdField;
    JLabel trainerIdLabel;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/T.jpg");

    public RemoveTrainerWindow(TrainerDatabase trainerDatabase) {
        this.trainerDatabase = trainerDatabase;
        setTitle("Remove Trainer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        
        initComponents();
        setUpButtons();
        setVisible(true);
    }

    private void setUpButtons() {
        removeButton.addActionListener(e -> {
            String trainerId = trainerIdField.getText();

            if (trainerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Trainer ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (trainerDatabase.contains(trainerId)) {
                trainerDatabase.deleteRecord(trainerId);
                trainerDatabase.saveToFile();
                JOptionPane.showMessageDialog(this, "Trainer removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                trainerIdField.setText(""); 
            } else {
                JOptionPane.showMessageDialog(this, "Trainer with ID = " + trainerId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            new AdminRoleWindow();
            dispose();
        });
    }

    private void initComponents() {
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(226, 173, 37); 

        trainerIdLabel = new JLabel("Trainer ID:");
        trainerIdLabel.setFont(labelFont);
        trainerIdLabel.setForeground(labelColor);
        trainerIdLabel.setBounds(50, 100, 100, 30);

        
        trainerIdField = new JTextField(20);
        trainerIdField.setBounds(130, 100, 200, 30);


        
        removeButton = new FuturisticButton("Remove");
        styleButton(removeButton, 130, 160, 120, 35);

        backButton = new FuturisticButton("Back");
        styleButton(backButton, 260, 160, 120, 35);

        
        panel.add(trainerIdLabel);
        panel.add(trainerIdField);
        panel.add(removeButton);
        panel.add(backButton);

        
        add(panel);
    }

    private void styleButton(FuturisticButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
    }
}
