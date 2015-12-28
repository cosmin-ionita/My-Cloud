package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandMkdir implements Command {

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();

        this.execute((Repository)fileSystem.currentDirectory);
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
