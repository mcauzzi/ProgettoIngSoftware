package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class EventManager {
    private static DBManager dbMan = new DBManager();
    private static PasswordEncryptionService passEnc = new PasswordEncryptionService();

    public static String loginClick(String username, char[] passwordField1Text) throws Exception, InvalidUserException, InvalidPasswordException {
        String salt;
        String password;
        String attemptedPassword = new String(passwordField1Text);

        ResultSet rs = dbMan.customQuery("SELECT * FROM logins WHERE username = '" + username + "'");

        while (rs.next()) {
            salt = rs.getString("salt");
            password = rs.getString("password");
            String providedPasswordHash = Base64.getEncoder().encodeToString(passEnc.getEncryptedPassword(attemptedPassword, salt.getBytes()));

            if (providedPasswordHash.equals(password)) {
                return rs.getString("accounttype");
            } else {
                throw new InvalidPasswordException("Password Errata");
            }
        }

        throw new InvalidUserException("Utente Non Esistente");
    }

    public static void addUserClick(String username, char[] password, String userType) {
        List<List> user = new ArrayList<>();
        user.add(new ArrayList<>());
        try {
            byte[] salt = passEnc.generateSalt();
            String saltHash = Base64.getEncoder().encodeToString(salt);
            String hash = Base64.getEncoder().encodeToString(passEnc.getEncryptedPassword(new String(password), saltHash.getBytes()));

            user.get(0).add(username);
            user.get(0).add(hash);
            user.get(0).add(userType);
            user.get(0).add(saltHash);

            dbMan.insert("logins", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateHistory(JTable storicoOrdini) {
        DefaultTableModel model;
        try {
            ResultSet rs = dbMan.customQuery("SELECT * FROM ORDINI");
            model = (DefaultTableModel) storicoOrdini.getModel();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("negozio"), Integer.toString(rs.getInt("codice")), rs.getDate("data").toString(),
                        rs.getString("tipoarticolo"), Integer.toString(rs.getInt("quantita")), rs.getBigDecimal("prezzototale").toPlainString()});
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
