package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandPwd implements Command {

    public void execute() {
        this.execute(FileSystem.getFileSystem().currentDirectory);
    }

    public void execute(File file) {
        System.out.println("File is not a directory!");
    }

    public void execute(Directory directory) {
        OutputManager.setOutput(directory.getCurrentPath());
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }
}
