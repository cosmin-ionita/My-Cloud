package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandTouch implements Command {

    public void execute(){
        FileSystem fileSystem = FileSystem.getFileSystem();

        if(fileSystem.currentDirectory.canWrite()) {
            this.execute((Repository)fileSystem.currentDirectory);
        } else {
            OutputManager.setOutput("You do not have permissions to write in this directory.");
        }
    }

    public void execute(Repository repository){
        repository.accept(this);
    }

    public void execute(Directory directory) {
        String[] parameters = ParametersManager.getBruteParameters().split(" ");

        if(parameters.length == 2) {
            directory.addFile(parameters[0], Integer.parseInt(parameters[1]));
        } else {
            directory.addFile(parameters[0], 0);
        }

        ParametersManager.flushParameters();
    }

    public void execute(File file) {
        System.out.println("Cannot touch in a File context");
    }
}
