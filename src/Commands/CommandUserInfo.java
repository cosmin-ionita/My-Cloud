package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.UserManager;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/29/2015.
 */
public class CommandUserInfo implements Command {

    public void execute() {
        if (ParametersManager.noParameters()) {
            OutputManager.setOutput(UserManager.getCurrentUserInfo());
        } else {
            OutputManager.setOutput(UserManager.getUserInfo(ParametersManager.getParameters()));
        }
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }

    public void execute(Directory directory) {
        System.out.println("Invalid control");
    }

    public void execute(File file) {
        System.out.println("Invalid control");
    }
}

