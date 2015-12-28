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
public class CommandCd implements Command {
    //TODO de analizat cazul in care ii dai cale absoluta ca parametru
    Directory currentDirectoryAlias;

    public void execute() {
        this.execute(FileSystem.getFileSystem().currentDirectory);
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(Directory directory) {

        currentDirectoryAlias = FileSystem.getFileSystem().currentDirectory;

        String[] pathTokens = ParametersManager.getParameters().split("/");

        for(String token : pathTokens) {

            if(token.equals(".."))
                moveBackwards();
            else
                moveForwards(token);
        }

        ParametersManager.flushParameters();
    }

    private void moveForwards(String directoryName) {
        Directory currentDirectory = FileSystem.getFileSystem().currentDirectory;

        Directory newDirectory = (Directory)currentDirectory.getNode(directoryName);

        if(newDirectory != null) {
            FileSystem.getFileSystem().currentDirectory = newDirectory;
        }

        else {
            FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;
            //TODO throw exception
            System.out.println("Inexistent path");
        }
    }

    private void moveBackwards() {
        Directory currentDirectory = FileSystem.getFileSystem().currentDirectory;

        if(currentDirectory.getParent() != null) {
            FileSystem.getFileSystem().currentDirectory = currentDirectory.getParent();
        }
        else {
            FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;
            //TODO throw exception
            System.out.println("Inexistent path");
        }
    }

    public void execute(File file) {
        System.out.println("You cannot change current directory to a file!");
    }
}
