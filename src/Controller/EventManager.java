package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class EventManager {
    private static final DBManager dbMan = new DBManager();
    private static final PasswordEncryptionService passEnc = new PasswordEncryptionService();

    //Esegue il comando di login
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

    //Aggiunge un utente all'applicazione
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

    //Riceve i dati dalla tabelle ordini li insersice in storicoOrdini
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

    //Aggiunge order alla tabella ordini
    public static void addOrderRow(ArrayList order) throws SQLException {
        ArrayList tuple = new ArrayList();

        ResultSet rs = dbMan.customQuery("SELECT a.prezzo\n" +
                "FROM articolo a JOIN tipiarticolo t on a.tipoarticolo=t.nome\n" +
                "WHERE a.tipoarticolo='" + order.get(3) + "'");
        rs.next();
        order.add(rs.getBigDecimal(1).multiply(new BigDecimal((int) order.get(4))));
        tuple.add(order);

        try {
            dbMan.insert("ordini", tuple);
        } catch (Exception e) {
            throw e;
        }
    }

    //Riceve i date dalle tabelle entrate ed uscite e le inserisce in inTable e in outTable
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

    //Aggiunge le colonne di rs al model
    private static void getQueryColumns(ResultSet rs, DefaultTableModel model) throws SQLException {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            model.addColumn(rs.getMetaData().getColumnName(i));
        }
    }

    //Acquisice i tipi degli articoli in articleTypeTable
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

    //Inserisce articleTypes nella tabella articoli
    public static void insertArticleTypes(ArrayList articleTypes) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(articleTypes.subList(0, 4));

        try {
            dbMan.insert("tipiarticolo", tuple);
            dbMan.customQuery("INSERT INTO articolo ( tipoarticolo, prezzo, dataproduzione)\n" +
                    "VALUES  ('" + articleTypes.get(0) + "', " + articleTypes.get(4) + ", '" + articleTypes.get(5) + "')");
        } catch (Exception e) {
            throw e;
        }
    }

    //Aggiunge un entrata alla tabella entrate
    public static void insertIns(ArrayList ins) throws SQLException {
        ArrayList tuple = new ArrayList();

        tuple.add(ins);

        try {
            dbMan.insert("ingressi", tuple);
        } catch (SQLException e) {
            throw e;
        }
    }

    //Aggiunge un uscita alla tabella uscite
    public static void insertOuts(ArrayList out) throws SQLException, QuantityException {
        ArrayList tuple = new ArrayList();
        ResultSet rs = dbMan.customQuery("SELECT COUNT(i.*) >= o.quantita\n" +
                "FROM ingressi i\n" +
                "       JOIN articolo a ON a.codice = i.article\n" +
                "       join ordini o on o.tipoarticolo = a.tipoarticolo\n" +
                "where o.codice =" + out.get(4) + "\n" +
                "GROUP BY o.quantita");

        rs.next();

        if (!rs.getBoolean(1)) {
            throw new QuantityException("Quantita in magazzino insufficente");
        }
        tuple.add(out);

        try {
            dbMan.insert("uscite", tuple);
        } catch (SQLException e) {
            throw e;
        }
    }

    //Aggiorna la posizione di un articolo
    public static void updatePosition(String id, String newPosition) throws SQLException {
        String query = "UPDATE ingressi SET position=" + newPosition + "WHERE id= " + id;

        dbMan.customQuery(query);
    }
}
