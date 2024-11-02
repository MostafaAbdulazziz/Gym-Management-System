package frontend;

import backend.Class;
import backend.ClassDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassWindow extends JFrame {
    private ClassDatabase classDatabase;

    // Declare text fields
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField trainerField;
    private JTextField durationField;
    private JTextField maxParticipantsField;

    // Declare buttons
    private JButton addButton;
    private JButton backButton;

    public AddClassWindow(ClassDatabase classDatabase) {
        this.classDatabase = classDatabase;

        // Set up the window
        setTitle("Add Class");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using null layout for custom positioning

        // Set starting x and y positions for the components
        int startX = 50; // x-position for labels
        int textFieldX = 200; // x-position for text fields
        int startY = 100; // starting y-position
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 20; // space between components

        // Class ID label and field
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setBounds(startX, startY, 100, componentHeight);
        panel.add(classIdLabel);

        classIdField = new JTextField();
        classIdField.setBounds(textFieldX, startY, componentWidth, componentHeight);
        panel.add(classIdField);

        // Class Name label and field
        JLabel classNameLabel = new JLabel("Class Name:");
        classNameLabel.setBounds(startX, startY + (componentHeight + spacing), 100, componentHeight);
        panel.add(classNameLabel);

        classNameField = new JTextField();
        classNameField.setBounds(textFieldX, startY + (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(classNameField);

        // Trainer label and field
        JLabel trainerLabel = new JLabel("Trainer:");
        trainerLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 100, componentHeight);
        panel.add(trainerLabel);

        trainerField = new JTextField();
        trainerField.setBounds(textFieldX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(trainerField);

        // Duration label and field
        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(startX, startY + 3 * (componentHeight + spacing), 100, componentHeight);
        panel.add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(textFieldX, startY + 3 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(durationField);

        // Max Participants label and field
        JLabel maxParticipantsLabel = new JLabel("Max Participants:");
        maxParticipantsLabel.setBounds(startX, startY + 4 * (componentHeight + spacing), 150, componentHeight);
        panel.add(maxParticipantsLabel);

        maxParticipantsField = new JTextField();
        maxParticipantsField.setBounds(textFieldX, startY + 4 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(maxParticipantsField);

        // Initialize buttons
        addButton = new JButton("Add");
        addButton.setBounds(startX+10, startY + 5 * (componentHeight + spacing), 150, 50);
        panel.add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(textFieldX+20, startY + 5 * (componentHeight + spacing), 150, 50);
        panel.add(backButton);

        // ActionListener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String trainer = trainerField.getText();
                String duration = durationField.getText();
                String maxParticipants = maxParticipantsField.getText();

                // Add the class to the database
                if(classDatabase.contains(classId)) {
                    JOptionPane.showMessageDialog(null,
                            "Class with ID "+classId+" already exists",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    classDatabase.insertRecord(new Class(classId, className, trainer, Integer.parseInt(duration), Integer.parseInt(maxParticipants)));
                    JOptionPane.showMessageDialog(null, "Class added successfully");
                    classDatabase.saveToFile();
                }

                // Clear the text fields
                classIdField.setText("");
                classNameField.setText("");
                trainerField.setText("");
                durationField.setText("");
                maxParticipantsField.setText("");

            }
        });

        // ActionListener for the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainerRoleWindow();
                dispose();

            }
        });

        // Add the panel to the frame
        add(panel);
    }
}
