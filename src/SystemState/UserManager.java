package SystemState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class UserManager {

    private static UserManager currentUser = new UserManager();

    private String userName;
    private String name;
    private String surname;

    private Date creationTime;
    private Date lastLoginTime;

    private UserManager() {
        userName = "guest";
        name = "";
        surname = "";
        creationTime = new Date();
        lastLoginTime = new Date();
    }

    public static UserManager getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentUserName() {
        return currentUser.userName;
    }

    public static void changeCurrentUser(String newName,
                                         String userSurname,
                                         String newUserName,

                                         Date userCreationTime,
                                         Date userLastLoginTime) {
        currentUser.name = newName;
        currentUser.surname = userSurname;
        currentUser.userName = newUserName;

        currentUser.creationTime = userCreationTime;
        currentUser.lastLoginTime = userLastLoginTime;
    }

    public static String getCurrentUserInfo() {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return  "Username: " + currentUser.userName + "\n" +
                "Name: " + currentUser.name + "\n" +
                "Surname: " + currentUser.surname + "\n" +
                "Created: " + formatter.format(currentUser.creationTime) + "\n" +
                "Last Login: " + formatter.format(currentUser.lastLoginTime) + "\n";
    }

    public static String getUserInfo(String userName) {

        String userInfo = "";

        try {
            RandomAccessFile stream = new RandomAccessFile("Users.txt", "rw");

            String line = stream.readLine();

            while (line != null) {

                if(userName.equals(line.split(" ")[0])) {
                    userInfo =  "Username: " + userName + "\n" +
                                "Name: " + line.split(" ")[2] + "\n" +
                                "Surname: " + line.split(" ")[3] + "\n" +
                                "Created: " + line.split(" ")[4].replace("$", " ") + "\n" +
                                "Last Login: " + line.split(" ")[5].replace("$", " ") + "\n";
                }

                line = stream.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return userInfo;
    }
}
