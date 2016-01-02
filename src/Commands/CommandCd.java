package Commands;

import Exceptions.MyInvalidPathException;
import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.UserManager;
import SystemState.Logger;
import Utils.OutputManager;
import Utils.ParametersManager;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandCd implements Command {
    Directory currentDirectoryAlias;

    public void execute() {
       try {
           if(FileSystem.getFileSystem().currentDirectory.canMove())
                this.execute(FileSystem.getFileSystem().currentDirectory);
           else
                OutputManager.setOutput("You cannot move in the fileSystem because you are a guest!");
       }
       catch(MyInvalidPathException exception) {
            Logger.log(exception.toString());
       }
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(Directory directory) throws MyInvalidPathException{

        currentDirectoryAlias = FileSystem.getFileSystem().currentDirectory;

        String[] pathTokens = ParametersManager.getParameters().split("/");

        try {
            for (String token : pathTokens) {

                if (token.equals(".."))
                    moveBackwards();
                else
                    moveForwards(token);
            }
        } catch (MyInvalidPathException exception) {
            throw exception;
        }

        ParametersManager.flushParameters();
    }

    private void moveForwards(String directoryName) throws MyInvalidPathException{
        Directory currentDirectory = FileSystem.getFileSystem().currentDirectory;

        Directory newDirectory = (Directory) currentDirectory.getNode(directoryName);

        if (newDirectory != null) {
            FileSystem.getFileSystem().currentDirectory = newDirectory;
        } else {
            FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;

            throw new MyInvalidPathException(currentDirectoryAlias,
                                             ParametersManager.getParameters(),
                                             UserManager.getCurrentUserName(),
                                             new Date());

        }
    }

    private void moveBackwards() throws MyInvalidPathException{

        Directory currentDirectory = FileSystem.getFileSystem().currentDirectory;

        if (currentDirectory.getParent() != null) {
            FileSystem.getFileSystem().currentDirectory = currentDirectory.getParent();
        } else {
            FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;

            throw new MyInvalidPathException(currentDirectoryAlias,
                                             ParametersManager.getParameters(),
                                             UserManager.getCurrentUserName(),
                                             new Date());
        }
    }

    public void execute(File file) {
        System.out.println("You cannot change current directory to a file!");
    }
}
