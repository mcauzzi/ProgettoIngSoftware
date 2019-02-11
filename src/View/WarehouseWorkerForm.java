package View;

import Controller.EventManager;

import javax.swing.*;
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
    private JTextField outDateField;
    private JTextField noteField;
    private JTextField storeField;
    private JButton insertOutButton;
    private JTable outTable;
    private JTextField positionIdField;
    private JTextField newPositionField;
    private JButton changePositionButton;
    private JTextField shipperField;
    private JPanel panel;
    private JTable inTable;
    private JTable orderTable;
    private JTextField orderField;
    private JLabel exitsLabel;
    private JLabel orderLabel;
    private JLabel entryLabel;

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
                ArrayList out = new ArrayList();
                try {
                    out.add(outDateField.getText());
                    out.add(Integer.parseInt(noteField.getText()));
                    out.add(storeField.getText());
                    out.add(shipperField.getText());
                    out.add(orderField.getText());
                    EventManager.insertOuts(out);

                    EventManager.getInsOuts(inTable, outTable);
                } catch (Exception e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
        changePositionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    EventManager.updatePosition(positionIdField.getText(), newPositionField.getText());
                    EventManager.getInsOuts(inTable, outTable);
                } catch (SQLException e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WarehouseWorkerForm");
        frame.setContentPane(new WarehouseWorkerForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280, 1024);
    }
}
