package frontend;

import backend.MemberClassRegistrationDatabase;
import backend.MemberClassRegistration;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewRegistrationsWindow extends JFrame {
    private MemberClassRegistrationDatabase registrationDatabase;
    private JTable registrationTable;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewRegistrationsWindow(MemberClassRegistrationDatabase registrationDatabase) {
        this.registrationDatabase = registrationDatabase;

        setTitle("View Registrations");
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

        
        String[] columnNames = {"Member ID", "Class ID", "Registration Date"};

        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        ArrayList<MemberClassRegistration> registrations = registrationDatabase.returnAllRecords();
        for (MemberClassRegistration registration : registrations) {
            tableModel.addRow(registration.lineRepresentation().split(","));
        }

        
        registrationTable = new JTable(tableModel);
        registrationTable.setOpaque(false);
        registrationTable.setBackground(new Color(255, 255, 255, 0)); 
        registrationTable.setFont(new Font("Arial", Font.PLAIN, 14));
        registrationTable.setRowHeight(30);
        registrationTable.setGridColor(new Color(200, 200, 200, 100)); 

        
        registrationTable.getTableHeader().setOpaque(false);
        registrationTable.getTableHeader().setBackground(new Color(60, 120, 180, 150)); 
        registrationTable.getTableHeader().setForeground(Color.WHITE);
        registrationTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        centerRenderer.setForeground(Color.WHITE); 
        for (int i = 0; i < registrationTable.getColumnCount(); i++) {
            registrationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        JScrollPane scrollPane = new JScrollPane(registrationTable);
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
