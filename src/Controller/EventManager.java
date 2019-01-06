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

    public static void updateHistory(JTable storicoOrdini) throws SQLException {
        DefaultTableModel model;
        storicoOrdini.setModel(new DefaultTableModel());
        try {
            ResultSet rs = dbMan.customQuery("SELECT * FROM ORDINI");
            model = (DefaultTableModel) storicoOrdini.getModel();
            getQueryColumns(rs, model);

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("negozio"), Integer.toString(rs.getInt("codice")), rs.getDate("data").toString(),
                        rs.getString("tipoarticolo"), Integer.toString(rs.getInt("quantita")), rs.getBigDecimal("prezzototale").toPlainString()});
            }

        } catch (SQLException e1) {
            throw e1;
        }
    }

    public static void addOrderRow(ArrayList order) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(order);

        try {
            dbMan.insert("ordini", tuple);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void getInsOuts(JTable inTable, JTable outTable) throws SQLException {
        DefaultTableModel model;
        inTable.setModel(new DefaultTableModel());
        outTable.setModel(new DefaultTableModel());

        try {
            ResultSet rs = null;
            if (inTable != null) {
                rs = dbMan.customQuery("select * from ingressi");

                model = (DefaultTableModel) inTable.getModel();

                getQueryColumns(rs, model);

                while (rs.next()) {
                    model.addRow(new Object[]{rs.getInt("id"), rs.getDate("data"), rs.getInt("article"), rs.getInt("position")});
                }
            }

            rs = dbMan.customQuery("select * from uscite");

            model = (DefaultTableModel) outTable.getModel();

            getQueryColumns(rs, model);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getDate("data"), rs.getInt("numerobolla"),
                        rs.getString("Negozio"), rs.getString("spedizioniere"), rs.getInt("ordine")});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static void getQueryColumns(ResultSet rs, DefaultTableModel model) throws SQLException {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            model.addColumn(rs.getMetaData().getColumnName(i));
        }
    }

    public static void getTypes(JTable articleTypeTable) throws SQLException {
        DefaultTableModel model;
        articleTypeTable.setModel(new DefaultTableModel());

        try {
            ResultSet rs = dbMan.customQuery("select * from tipiarticolo");

            model = (DefaultTableModel) articleTypeTable.getModel();

            getQueryColumns(rs, model);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("nome"), rs.getString("descrizione"),
                        rs.getString("sport"), rs.getArray("materiali")});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static void insertArticleTypes(ArrayList articleTypes) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(articleTypes);

        try {
            dbMan.insert("tipiarticolo", tuple);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void insertIns(ArrayList ins) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(ins);

        try {
            dbMan.insert("ingressi", tuple);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void insertOuts(ArrayList out) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(out);

        try {
            dbMan.insert("uscite", tuple);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void updatePosition(String id, String newPosition) throws SQLException {
        String query = "UPDATE ingressi SET position=" + newPosition + "WHERE id= " + id;

        dbMan.customQuery(query);
    }
}
