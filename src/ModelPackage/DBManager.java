package ModelPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    private Connection c;
    private Statement st;
    private ResultSet rs;

    public DBManager() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://192.168.1.42:5433/ShopDB",
                            "IngSof", "prova123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        try {
            DatabaseMetaData dbmd = c.getMetaData();
            try (ResultSet tables = dbmd.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (tables.next()) {
                    System.out.println(tables.getString("TABLE_NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insert(String DBName, List<List> tuples) {
        try {
            List<String> colNames = new ArrayList();
            st = c.createStatement();

            String query = "INSERT INTO " + DBName + " (";
            colNames = getColumnNames(DBName);
            for (int i = 0; i < colNames.size(); i++) {
                if (i != colNames.size() - 1)
                    query += colNames.get(i) + ", ";
                else
                    query += colNames.get(i);
            }

            query += ") VALUES ";
            for (int i = 0; i < tuples.size(); i++) {
                query += "(";
                for (int j = 0; j < tuples.get(i).size(); j++) {
                    if (j != tuples.get(i).size() - 1)
                        query += "'" + tuples.get(i).get(j) + "'" + ", ";
                    else
                        query += "'" + tuples.get(i).get(j) + "'";
                }
                if (i != tuples.size() - 1)
                    query += "),\n";
            }
            query += ");\n";
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void customQuery(String query) {
        try {
            st = c.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    void remove(String DBName, List<List> tuples) {

    }

    List getColumnNames(String DBName) {
        List<String> colNames = new ArrayList();

        try {
            st = c.createStatement();
            rs = st.executeQuery("SELECT * FROM " + DBName);
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                colNames.add(rs.getMetaData().getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return colNames;
    }

    public <T> List get(String TabName) {
        List<ArrayList> tuples = new ArrayList<ArrayList>();

        try {
            st = c.createStatement();
            rs = st.executeQuery("SELECT * FROM " + TabName);

            while (rs.next()) {
                tuples.add(new ArrayList());
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    tuples.get(tuples.size() - 1).add(rs.getObject(i));
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return tuples;
    }
}
