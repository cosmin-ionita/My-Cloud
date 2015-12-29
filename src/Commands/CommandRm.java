package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.CommandFactory;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/26/2015.
 */
public class CommandRm implements Command {

    private boolean isRecursive = false;
    private String parametersAlias;
    private String removalNode;
    private Directory currentDirectoryAlias;

    public void execute() {
        this.execute(FileSystem.getFileSystem().currentDirectory);
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(Directory directory) {
        this.saveState();

        this.moveToRemovalDirectory();

        this.deleteNode();

        this.restoreState();
    }

    private void deleteNode() {
        ParametersManager.setParameters((this.isRecursive ? "-r" : "") + parametersAlias);

        Repository node = FileSystem.getFileSystem().currentDirectory.getNode(removalNode);

        if (node.getClass().toString().split(" ")[1].equals("FileSystem.Directory")) {
            Directory directory = (Directory) node;

            if(directory.isEmpty()) {
                FileSystem.getFileSystem().currentDirectory.deleteNode(removalNode);
            }
            else if(!directory.isEmpty() && isRecursive == true) {
                FileSystem.getFileSystem().currentDirectory.deleteNode(removalNode);
            }
            else {
                //TODO throw exception
            }

        } else if (node.getClass().toString().split(" ")[1].equals("FileSystem.File")) {
            FileSystem.getFileSystem().currentDirectory.deleteNode(removalNode);
        }
    }

    private void saveState() {
        currentDirectoryAlias = FileSystem.getFileSystem().currentDirectory;
        parametersAlias = ParametersManager.getParameters();

        if(ParametersManager.isRecursiveOption())
            this.isRecursive = true;
    }

    private void restoreState() {
        FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;
    }

    private void moveToRemovalDirectory() {
        Command cdCommand = CommandFactory.getCommand("cd");

        ParametersManager.setParameters(this.getCdParameter());

        cdCommand.execute();
    }

    private String getCdParameter() {
        String cdParameter = "";
        String[] parameters = parametersAlias.split("/");

        removalNode = parameters[parameters.length - 1];

        for(int i = 0; i<parameters.length - 1; i++) {
            cdParameter += parameters[i] + "/";
        }

        return cdParameter;
    }

    public void execute(File file) {

    }
}
