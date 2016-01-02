package Commands;

import Exceptions.MyPathTooLongException;
import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.Logger;
import SystemState.UserManager;
import Utils.OutputManager;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandPwd implements Command {

    public void execute() {
        try {
            this.execute(FileSystem.getFileSystem().currentDirectory);
        }catch(MyPathTooLongException exception) {
            Logger.log(exception.toString());
        }
    }

    public void execute(File file) {
        System.out.println("File is not a directory!");
    }

    public void execute(Directory directory) throws MyPathTooLongException{
        if(directory.getCurrentPath().length() > 255)
            throw new MyPathTooLongException(directory, "", UserManager.getCurrentUserName(), new Date());
        else {
            OutputManager.setOutput(directory.getCurrentPath());
        }
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }
}
