package View;

import Controller.EventManager;
import Controller.QuantityException;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
    private JTextField quantityField;
    private JTextField articleTypeField;

    public StoreManagerForm() {

        updateHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    EventManager.updateHistory(storicoOrdini);
                } catch (SQLException e1) {
                    showMessageDialog(null, e1.getMessage());
                }
            }
        });
        addRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList order = new ArrayList();

                try {
                    order.add(storeField.getText());
                    order.add(orderCodeField.getText());
                    order.add(dateField.getText());
                    order.add(articleTypeField.getText());
                    order.add(Integer.parseInt(quantityField.getText()));
                    //order.add(Double.parseDouble(priceField.getText()) * Integer.parseInt(quantityField.getText()));

                    EventManager.addOrderRow(order);
                    EventManager.updateHistory(storicoOrdini);
                } catch (Exception e1) {
                    showMessageDialog(null, e1.getMessage());
                }
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
    }


}
