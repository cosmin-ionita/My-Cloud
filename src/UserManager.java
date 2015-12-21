import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class UserManager {

    private static UserManager currentUser = new UserManager ();

    private  String userName;
    private  String name;
    private  String surname;

    private  Date creationTime;
    private  Date lastLoginTime;

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
        currentUser.name = newName;
        currentUser.surname = userSurname;
        currentUser.userName = newUserName;

        currentUser.creationTime = userCreationTime;
        currentUser.lastLoginTime = userLastLoginTime;
    }

    public String getUserInfo() {

        return currentUser.userName + "\n" +
                currentUser.name + "\n" +
                currentUser.surname + "\n" +
                currentUser.creationTime.toString() + "\n" +
                currentUser.lastLoginTime.toString() + "\n";
    }
}
