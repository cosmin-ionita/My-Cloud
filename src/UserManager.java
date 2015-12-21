import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class UserManager {

    private static UserManager currentUser = new UserManager ();

    private static String userName;
    private static String name;
    private static String surname;

    private static Date creationTime;
    private static Date lastLoginTime;

    private UserManager() {

    }

    public static UserManager getCurrentUser() {
        return currentUser;
    }

    public static void createUser(String newName,
                                  String userSurname,
                                  String newUserName,

                                  Date userCreationTime,
                                  Date userLastLoginTime) {
        name = newName;
        surname = userSurname;
        userName = newUserName;

        creationTime = userCreationTime;
        lastLoginTime = userLastLoginTime;
    }

    public String getUserInfo() {
        return userName + "\n" +
                name + "\n" +
                surname + "\n" +
                creationTime.toString() + "\n" +
                lastLoginTime.toString() + "\n";
    }
}
