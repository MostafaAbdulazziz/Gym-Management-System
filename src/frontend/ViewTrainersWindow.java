package frontend;

import backend.Trainer;
import backend.TrainerDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewTrainersWindow extends JFrame {
    private TrainerDatabase trainerDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewTrainersWindow(TrainerDatabase trainerDatabase) {
        this.trainerDatabase = trainerDatabase;
        setTitle("View Trainers");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        
        String[] columnNames = {"ID", "Name", "Email", "Specialty", "Phone Number"};

        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        ArrayList<Trainer> trainersData = trainerDatabase.returnAllRecords();
        for (Trainer trainer : trainersData) {
            tableModel.addRow(trainer.lineRepresentation().split(","));
        }

        
        JTable trainerTable = new JTable(tableModel);
        trainerTable.setOpaque(false);
        trainerTable.setBackground(new Color(255, 255, 255, 0)); 
        trainerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        trainerTable.setRowHeight(30);
        trainerTable.setGridColor(new Color(200, 200, 200, 100)); 

        
        trainerTable.getTableHeader().setOpaque(false);
        trainerTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); 
        trainerTable.getTableHeader().setForeground(Color.WHITE);
        trainerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        centerRenderer.setForeground(Color.WHITE); 
        for (int i = 0; i < trainerTable.getColumnCount(); i++) {
            trainerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        JScrollPane scrollPane = new JScrollPane(trainerTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        
        FuturisticButton backButton = new FuturisticButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(60, 120, 180, 200)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        
        add(mainPanel);

        
        backButton.addActionListener(e -> {
            new AdminRoleWindow();
            dispose();
        });
    }
}
