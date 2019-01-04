package View;

import Controller.DBManager;
import Controller.EventManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreManagerForm {
    private JTable storicoOrdini;
    private JPanel panel1;
    private JButton updateHistoryButton;
    private JButton addRowButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private static DBManager dbMan;

    public StoreManagerForm() {

        updateHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EventManager.updateHistory(storicoOrdini);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StoreManagerForm");
        frame.setContentPane(new StoreManagerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1024, 768);
        dbMan = new DBManager();
    }


}
