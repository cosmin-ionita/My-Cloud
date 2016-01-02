package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandMkdir implements Command {

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();

        if(fileSystem.currentDirectory.canWrite()){
            this.execute((Repository)fileSystem.currentDirectory);
        } else {
            OutputManager.setOutput("You do not have permissions to modify this directory");
        }
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(Directory directory) {
        if(ParametersManager.hasPermissions())
            directory.addDirectory(ParametersManager.getParameters(), ParametersManager.getPermissions());
        else
            directory.addDirectory(ParametersManager.getParameters());

        ParametersManager.flushParameters();
    }

    public void execute(File file) {
        System.out.println("You cannot create a directory in a File context");
    }
}
