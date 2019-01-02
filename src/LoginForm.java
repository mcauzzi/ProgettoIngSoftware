import Controller.EventManager;
import Controller.InvalidPasswordException;
import Controller.InvalidUserException;
import ModelPackage.UserType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.JOptionPane.showMessageDialog;

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
                String acctType = null;
                try {
                    acctType = EventManager.loginClick(userNameField.getText(), passwordField1.getPassword());
                } catch (Exception | InvalidUserException | InvalidPasswordException e1) {
                    showMessageDialog(null, e1.getMessage());
                    return;
                }

                switch (UserType.valueOf(acctType).name()) {
                    case "warehouse_worker":

                        break;
                    case "manager":

                        break;
                    case "store_manager":

                        break;
                    default:
                        showMessageDialog(null, "Tipo Di Utente Non Riconosciuto");
                }
            }
        });

        addUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addForm = new AddUserForm();
                AddUserForm.main(null);
            }
        });
    }
}
