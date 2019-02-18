package View;

import Controller.EventManager;
import ModelPackage.UserType;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddUserForm {
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton addButton;
    private JLabel usernameLabel;
    private JComboBox userTypeBox;
    private JLabel typeLabel;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("AddUserForm");
        frame.setContentPane(new AddUserForm().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    private void userTypeBoxInit() {
        for (int i = 0; i < UserType.values().length; i++) {
            userTypeBox.addItem(UserType.values()[i].name());
        }
    }

    public AddUserForm() {
        userTypeBoxInit();

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EventManager.addUserClick(usernameField.getText(), passwordField1.getPassword(), userTypeBox.getSelectedItem().toString());
            }
        });
    }

}
