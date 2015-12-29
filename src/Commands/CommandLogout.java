package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.UserManager;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/29/2015.
 */
public class CommandLogout implements Command {

    public void execute() {
        UserManager.changeCurrentUser("guest", "", "", new Date(), new Date());
    }

    public void execute(Directory directory) {
        System.out.println("Invalid control");
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }

    public void execute(File file) {
        System.out.println("Invalid control");
    }
}
