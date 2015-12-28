package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandTouch implements Command {

    public void execute(){
        FileSystem fileSystem = FileSystem.getFileSystem();
        this.execute((Repository)fileSystem.currentDirectory);
    }

    public void execute(Repository repository){
        repository.accept(this);
    }

    public void execute(Directory directory) {
        directory.addFile(ParametersManager.getParameters());

        ParametersManager.flushParameters();
    }

    public void execute(File file) {
        System.out.println("Cannot touch in a File context");
    }
}
