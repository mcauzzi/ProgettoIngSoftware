package View;

import Controller.DBManager;
import Controller.EventManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class storeManagerForm {
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

    public storeManagerForm() {

        updateHistoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EventManager.updateHistory(storicoOrdini);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("storeManagerForm");
        frame.setContentPane(new storeManagerForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1024, 768);
        dbMan = new DBManager();
    }


}
