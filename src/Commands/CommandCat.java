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
public class CommandCat implements Command {

    public void execute() {
        File file = (File) FileSystem.getFileSystem().currentDirectory.getNode(ParametersManager.getParameters());

        if(file.canRead())
            this.execute(file);
        else
            OutputManager.setOutput("You do not have permissions to read this file");
    }

    public void execute(Directory directory) {
        System.out.println("You cannot see the contents of a file!");
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }

    public void execute(File file) {
        OutputManager.setOutput(file.getContent());

        ParametersManager.flushParameters();
    }
}
