package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/29/2015.
 */
public class CommandEcho implements Command {

    public void execute() {
        OutputManager.setOutput(ParametersManager.getParameters());
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
