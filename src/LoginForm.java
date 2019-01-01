import Controller.EventManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JTextField userNameField;
    private JButton loginButton;
    private JButton addUser;
    private EventManager man = new EventManager();
    private AddUserForm addForm;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(640, 480);
    }

    public LoginForm() {
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                man.loginClick(userNameField.getText(), passwordField1.getPassword());
            }
        });

        addUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addForm = new AddUserForm();
                addForm.main(null);
            }
        });
    }
}
