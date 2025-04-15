package frontend;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;


public class FuturisticButton extends JButton {
    private boolean hover = false;

    public FuturisticButton(String text) {
        super(text);
        setUI(new BasicButtonUI());
        setPreferredSize(new Dimension(150, 40));  
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE); 
        setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());

        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hover = true;
                setBorder(BorderFactory.createLineBorder(Color.CYAN, 2)); 
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hover = false;
                setBorder(BorderFactory.createEmptyBorder());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        Color color1 = hover ? new Color(169, 134, 26, 236) : new Color(16, 18, 3);  
        Color color2 = hover ? new Color(230, 183, 69, 255) : new Color(46, 48, 10, 255);  

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gp);

        
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2d.setColor(new Color(0, 128, 255));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
    }
}
