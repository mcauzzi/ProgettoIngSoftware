import Controller.DBManager;

import java.util.ArrayList;
import java.util.List;

public class TestConsole {

    public static void main(String[] args) {
        DBManager dbm = new DBManager();
        List<List> tuples = new ArrayList();
    }

}

/* INSERT TEST
tuples.add(new ArrayList());
tuples.add(new ArrayList());
tuples.get(0).add("CodFis01");
tuples.get(0).add("marco");
tuples.get(0).add("cauzzi");
tuples.get(0).add("mcauzzi");
tuples.get(0).add("prova123");
tuples.get(0).add("mantova");
tuples.get(0).add("346785432");
tuples.get(0).add("037682652");
tuples.get(1).add("CodFis02");
tuples.get(1).add("giovanni");
tuples.get(1).add("cauzzi");
tuples.get(1).add("gcauzzi");
tuples.get(1).add("prova1234");
tuples.get(1).add("verona");
tuples.get(1).add("346789087");
tuples.get(1).add("04582652");
dbm.insert("clients", tuples);
*/