package View;

import Controller.DBManager;
import Controller.EventManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class StoreManagerForm {
    public static final String INCORRECT_PRICE = "Inserire un formato di prezzo corretto";
    public static final String INCORRECT_QUANTITY = "Inserire una quantità corretta";
    private JTable storicoOrdini;
    private JPanel panel1;
    private JButton updateHistoryButton;
    private JButton addRowButton;
    private JTextField storeField;
    private JTextField orderCodeField;
    private JTextField dateField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField articleTypeField;
    private static DBManager dbMan;

    public StoreManagerForm() {

        updateHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EventManager.updateHistory(storicoOrdini);
            }
        });
        addRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList order = new ArrayList();
                order.add(storeField.getText());
                order.add(orderCodeField.getText());
                order.add(dateField.getText());
                order.add(articleTypeField.getText());

                try {
                    order.add(Integer.parseInt(quantityField.getText()));
                } catch (NumberFormatException e1) {
                    showMessageDialog(null, INCORRECT_QUANTITY);
                }

                try {
                    order.add(Double.parseDouble(priceField.getText()) * Integer.parseInt(quantityField.getText()));
                } catch (NumberFormatException e1) {
                    showMessageDialog(null, INCORRECT_PRICE);
                }

                try {
                    EventManager.addOrderRow(order);
                } catch (Exception e1) {
                    showMessageDialog(null, e1.getMessage());
                }
                EventManager.updateHistory(storicoOrdini);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("storeManagerForm");
        frame.setContentPane(new StoreManagerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1024, 768);
        dbMan = new DBManager();
    }


}
