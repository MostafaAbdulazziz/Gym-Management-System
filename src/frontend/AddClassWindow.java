package frontend;

import backend.Class;
import backend.ClassDatabase;
import backend.TrainerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassWindow extends JFrame {
    private ClassDatabase classDatabase;
    private TrainerDatabase trainerDatabase;


    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField trainerField;
    private JTextField durationField;
    private JTextField maxParticipantsField;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/hall.jpg");


    private FuturisticButton addButton;
    private FuturisticButton backButton;

    public AddClassWindow(ClassDatabase classDatabase, TrainerDatabase trainerDatabase) {
        this.classDatabase = classDatabase;
        this.trainerDatabase = trainerDatabase;

        setTitle("Add Class");
        setSize(1280, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
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
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(0, 0, 0);
        int startX = 50;
        int textFieldX = 200;
        int startY = 100;
        int componentWidth = 200;
        int componentHeight = 30;
        int spacing = 20;

        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setBounds(startX, startY, 100, componentHeight);
        classIdLabel.setForeground(labelColor);
        classIdLabel.setFont(labelFont);
        panel.add(classIdLabel);

        classIdField = new JTextField();
        classIdField.setBounds(textFieldX, startY, componentWidth, componentHeight);
        panel.add(classIdField);


        JLabel classNameLabel = new JLabel("Class Name:");
        classNameLabel.setBounds(startX, startY + (componentHeight + spacing), 100, componentHeight);
        classNameLabel.setForeground(labelColor);
        classNameLabel.setFont(labelFont);
        panel.add(classNameLabel);

        classNameField = new JTextField();
        classNameField.setBounds(textFieldX, startY + (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(classNameField);

        JLabel trainerLabel = new JLabel("Trainer:");
        trainerLabel.setBounds(startX, startY + 2 * (componentHeight + spacing), 100, componentHeight);
        trainerLabel.setForeground(labelColor);
        trainerLabel.setFont(labelFont);
        panel.add(trainerLabel);

        trainerField = new JTextField();
        trainerField.setBounds(textFieldX, startY + 2 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(trainerField);

        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(startX, startY + 3 * (componentHeight + spacing), 100, componentHeight);
        durationLabel.setForeground(labelColor);
        durationLabel.setFont(labelFont);
        panel.add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(textFieldX, startY + 3 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(durationField);

        JLabel maxParticipantsLabel = new JLabel("Max Participants:");
        maxParticipantsLabel.setBounds(startX, startY + 4 * (componentHeight + spacing), 150, componentHeight);
        maxParticipantsLabel.setForeground(labelColor);
        maxParticipantsLabel.setFont(labelFont);
        panel.add(maxParticipantsLabel);

        maxParticipantsField = new JTextField();
        maxParticipantsField.setBounds(textFieldX, startY + 4 * (componentHeight + spacing), componentWidth, componentHeight);
        panel.add(maxParticipantsField);

        addButton = new FuturisticButton("Add");
        addButton.setBounds(textFieldX, startY + 5 * (componentHeight + spacing), 100, 30);
        panel.add(addButton);

        backButton = new FuturisticButton("Back");
        backButton.setBounds(textFieldX + 110, startY + 5 * (componentHeight + spacing), 100, 30);
        panel.add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String trainerID = trainerField.getText();
                String duration = durationField.getText();
                String maxParticipants = maxParticipantsField.getText();

                if (classDatabase.contains(classId)) {
                    JOptionPane.showMessageDialog(null,
                            "Class with ID " + classId + " already exists",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!trainerDatabase.contains(trainerID)) {
                    JOptionPane.showMessageDialog(null,
                            "Trainer with ID " + trainerID + " does not exist",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (classId.isEmpty() || className.isEmpty() || trainerID.isEmpty() || duration.isEmpty() || maxParticipants.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validations.isValidClassId(classId)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Class ID",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validations.isValidClassName(className)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Class Name",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validations.isTrainerIdValid(trainerID)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Trainer Name",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validations.isNumberValid(duration)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Duration",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validations.isNumberValid(maxParticipants)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Max Participants",
                            "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    classDatabase.insertRecord(new Class(classId, className, trainerID, Integer.parseInt(duration), Integer.parseInt(maxParticipants)));
                    JOptionPane.showMessageDialog(null, "Class added successfully");
                    classDatabase.saveToFile();
                    if(JOptionPane.showConfirmDialog(null, "Do you want to add another class?", "Add Class", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    {
                        classIdField.setText("");
                        classNameField.setText("");
                        trainerField.setText("");
                        durationField.setText("");
                        maxParticipantsField.setText("");
                    }
                }


            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainerRoleWindow();
                dispose();

            }
        });

        add(panel);
    }
}
