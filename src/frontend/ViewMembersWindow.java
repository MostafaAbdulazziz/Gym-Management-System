package frontend;

import backend.Member;
import backend.MemberDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewMembersWindow extends JFrame {
    private MemberDatabase memberDatabase;
    private JTable memberTable;
    private FuturisticButton backButton;
    private final ImageIcon backgroundImage = new ImageIcon("src/frontend/Role.jpg");

    public ViewMembersWindow(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
        setTitle("View Members");
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

        
        String[] columnNames = {"ID", "Name", "Membership Type", "Email", "Phone Number", "Status"};

        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        ArrayList<Member> members = memberDatabase.returnAllRecords();
        for (Member member : members) {
            tableModel.addRow(member.lineRepresentation().split(","));
        }

        
        memberTable = new JTable(tableModel);
        memberTable.setOpaque(false);
        memberTable.setBackground(new Color(255, 255, 255, 0)); 
        memberTable.setFont(new Font("Arial", Font.PLAIN, 14));
        memberTable.setRowHeight(30);
        memberTable.setGridColor(new Color(200, 200, 200, 100)); 

        
        memberTable.getTableHeader().setOpaque(false);
        memberTable.getTableHeader().setBackground(new Color(70, 130, 180, 150)); 
        memberTable.getTableHeader().setForeground(Color.WHITE);
        memberTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); 
        centerRenderer.setForeground(Color.WHITE); 
        for (int i = 0; i < memberTable.getColumnCount(); i++) {
            memberTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        
        backButton = new FuturisticButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(70, 130, 180, 200)); 
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
