package View;

import Controller.EventManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class ManagerForm {

    private JTable inTable;
    private JTable outTable;
    private JButton getMovementsButton;
    private JPanel panel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JTable articleTypeTable;
    private JButton getTypesButton;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField sportField;
    private JTextField materialsField;
    private JButton inserisciTipoDiMaterialeButton;
    private JButton addUser;
    private AddUserForm addForm;

    public ManagerForm() {
        getMovementsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    EventManager.getInsOuts(inTable, outTable);
                } catch (SQLException e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
        getTypesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    EventManager.getTypes(articleTypeTable);
                } catch (SQLException e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
        inserisciTipoDiMaterialeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                ArrayList articleTypes = new ArrayList();

                articleTypes.add(nameField.getText());
                articleTypes.add(descriptionField.getText());
                articleTypes.add(sportField.getText());

                String[] materialsArray = materialsField.getText().split(",");
                articleTypes.add("{" + String.join(",", materialsArray) + "}");

                try {
                    EventManager.insertArticleTypes(articleTypes);
                } catch (SQLException e1) {
                    showMessageDialog(null, e1.getMessage());
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagerForm");
        frame.setContentPane(new ManagerForm().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280, 1024);
    }
}
