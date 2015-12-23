package Commands;

import FileSystem.AbstractFileSystem;
import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.Parameters;

import java.time.Duration;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandMkdir implements Command {

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();
        this.execute(fileSystem.currentDirectory);
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(Directory directory) {
        directory.addDirectory(Parameters.getParameters()[0]);

        Parameters.setParameters("");
    }

    public void execute(File file) {
        System.out.println("You cannot create a directory in a File context");
    }
}
