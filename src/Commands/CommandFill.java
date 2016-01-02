package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/31/2015.
 */
public class CommandFill implements Command {

    public void execute() {
        File file = (File)FileSystem.getFileSystem().currentDirectory.getNode(ParametersManager.getBruteParameters().split(" ")[0]);

        if(file.canWrite())
            this.execute(file);
        else
            OutputManager.setOutput("You do not have permissions to modify this file");
    }

    public void execute(Directory directory) {
        System.out.println("Invalid control");
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }

    public void execute(File file) {
        file.fill(ParametersManager.getBruteParameters().substring(file.toString().length()));

        ParametersManager.flushParameters();
    }
}
