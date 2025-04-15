package frontend;

import backend.ClassDatabase;
import backend.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewClassesWindow extends JFrame {
    private ClassDatabase classDatabase;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewClassesWindow(ClassDatabase classDatabase) {
        this.classDatabase = classDatabase;
        setTitle("View Classes");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        
        String[] columnNames = {"ID", "Name", "Trainer ID", "Duration", "Available Seats"};

        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        ArrayList<Class> classes = classDatabase.returnAllRecords();
        for (Class classObj : classes) {
            tableModel.addRow(classObj.lineRepresentation().split(","));
        }

        
        JTable classTable = new JTable(tableModel);
        classTable.setOpaque(false);
        classTable.setBackground(new Color(255, 255, 255, 0)); 
        classTable.setFont(new Font("Arial", Font.PLAIN, 14));
        classTable.setRowHeight(30);
        classTable.setGridColor(new Color(200, 200, 200, 100)); 

        
        classTable.getTableHeader().setOpaque(false);
        classTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); 
        classTable.getTableHeader().setForeground(Color.WHITE);
        classTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        centerRenderer.setForeground(Color.WHITE); 
        for (int i = 0; i < classTable.getColumnCount(); i++) {
            classTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        JScrollPane scrollPane = new JScrollPane(classTable);
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
            new TrainerRoleWindow();
            dispose();
        });
    }
}
