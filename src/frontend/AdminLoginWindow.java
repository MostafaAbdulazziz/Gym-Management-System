package frontend;

import constants.LoginCredentials;

import javax.swing.*;

public class AdminLoginWindow  extends JFrame implements LoginCredentials{
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public AdminLoginWindow() {
        panel1 = new JPanel();
        panel1.setLayout(null);
        usernameField = new JTextField();
        usernameField.setBounds(100, 50, 200, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 200, 30);
        loginButton = new JButton("Login");

 //////////////////////////////////////////////////
        //Handle enter key press to login
//        loginButton.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    loginButton.doClick();
//                }
//            }
//
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent e) {
//            }
//        });
/////////////////////////////////////////////////////////////////////////

        loginButton.setBounds(100, 150, 100, 30);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 150, 100, 30);

        usernameField.setVisible(true);
        passwordField.setVisible(true);

        add(panel1);
        setTitle("Admin Login");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        panel1.setVisible(true);
        panel1.add(usernameField);
        panel1.add(passwordField);
        panel1.add(loginButton);
        panel1.add(cancelButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {

                new AdminRoleWindow();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Invalid username or password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
            new MainWindow();
        });
    }


}
