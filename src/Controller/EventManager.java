package Controller;

import ModelPackage.DBManager;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static DBManager dbMan = new DBManager();
    private static PasswordEncryptionService passEnc = new PasswordEncryptionService();

    public static void loginClick(String text, char[] passwordField1Text) {
        //char[] encryptedPass = passEnc.
    }

    public static void addUserClick(String username, char[] password, String userType) {
        List<List> user = new ArrayList<>();
        user.add(new ArrayList<String>());
        try {
            byte[] salt = passEnc.generateSalt();
            user.get(0).add(username);
            user.get(0).add(new String(passEnc.getEncryptedPassword(password.toString(), salt)));
            user.get(0).add(userType);
            user.get(0).add(salt.toString());
            dbMan.insert("logins", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
