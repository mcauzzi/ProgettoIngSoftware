package View;

import Controller.EventManager;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class WarehouseWorkerForm {
    private JTextField idField;
    private JTextField dateField;
    private JTextField articleField;
    private JTextField positionField;
    private JButton insertInButton;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton insertOutButton;
    private JTable outTable;
    private JTextField textField9;
    private JTextField textField10;
    private JButton changePosizionButton;
    private JTextField textField11;
    private JPanel panel;
    private JTable inTable;
    private JTable orderTable;

    public WarehouseWorkerForm() {
        try {
            EventManager.getInsOuts(inTable, outTable);
            EventManager.updateHistory(orderTable);
        } catch (SQLException e1) {
            showMessageDialog(null, e1.getMessage());
        }
        insertInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList in = new ArrayList();

                try {
                    in.add(Integer.parseInt(idField.getText()));
                    in.add(dateField.getText());
                    in.add(Integer.parseInt(articleField.getText()));
                    in.add(Integer.parseInt(positionField.getText()));

                    EventManager.insertIns(in);

                    EventManager.getInsOuts(inTable, outTable);
                } catch (Exception e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
        insertOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        changePosizionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WarehouseWorkerForm");
        frame.setContentPane(new WarehouseWorkerForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
