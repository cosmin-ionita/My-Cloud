package Commands;

import Exceptions.MyNotEmptyDirectoryException;
import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.Logger;
import SystemState.UserManager;
import Utils.CommandFactory;
import Utils.OutputManager;
import Utils.ParametersManager;

import java.util.Date;
import java.util.regex.Pattern;

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
        if (directory.canWrite()) {
            this.saveState();

            this.moveToRemovalDirectory();

            try {
                this.deleteNode();
            } catch (MyNotEmptyDirectoryException exception) {
                Logger.log(exception.toString());
            }

            this.restoreState();

        } else {
            OutputManager.setOutput("You do not have permissions to modify that file/directory");
        }
    }

    private void deleteNode() throws MyNotEmptyDirectoryException {
        ParametersManager.setParameters((this.isRecursive ? "-r" : "") + parametersAlias);

        Directory currentDirectory = FileSystem.getFileSystem().currentDirectory;

        if (!isRegex(removalNode)) {
            deleteNodeHandler(currentDirectory, removalNode);
        } else {
            Pattern regex = Pattern.compile(removalNode);
            String content = currentDirectory.getContent();

            for(String node : content.split(" ")) {
                if (regex.matcher(node).matches()) {
                    deleteNodeHandler(currentDirectory, node);
                }
            }
        }
    }

    private void deleteNodeHandler(Directory currentDirectory, String nodeName) throws MyNotEmptyDirectoryException{
        Repository node = currentDirectory.getNode(nodeName);

        if (node.getClass().toString().split(" ")[1].equals("FileSystem.Directory")) {
            Directory directory = (Directory) node;

            if (directory.isEmpty()) {
                currentDirectory.deleteNode(nodeName);
            } else if (!directory.isEmpty() && isRecursive) {
                currentDirectory.deleteNode(nodeName);
            } else {
                OutputManager.setOutput("The directory you want to remove is not empty!");
                throw new MyNotEmptyDirectoryException(directory, UserManager.getCurrentUserName(), new Date());
            }

        } else if (node.getClass().toString().split(" ")[1].equals("FileSystem.File")) {
            currentDirectory.deleteNode(nodeName);
        }
    }

    private boolean isRegex(String removalNode) {
        for(int i = 0; i<removalNode.length(); i++) {
            if(!Character.isLetterOrDigit(removalNode.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private void saveState() {
        currentDirectoryAlias = FileSystem.getFileSystem().currentDirectory;
        parametersAlias = ParametersManager.getParameters();

        if (ParametersManager.isRecursiveOption())
            this.isRecursive = true;
    }

    private void restoreState() {
        FileSystem.getFileSystem().currentDirectory = currentDirectoryAlias;
    }

    private void moveToRemovalDirectory() {
        Command cdCommand = CommandFactory.getCommand("cd");

        ParametersManager.flushParameters(); // prepare parameters manager for the cd command

        ParametersManager.setParameters(this.getCdParameter());

        cdCommand.execute();
    }

    private String getCdParameter() {
        String cdParameter = "";
        String[] parameters = parametersAlias.split("/");

        removalNode = parameters[parameters.length - 1];

        for (int i = 0; i < parameters.length - 1; i++) {
            cdParameter += parameters[i] + "/";
        }

        return cdParameter;
    }

    public void execute(File file) {
        System.out.println("Invalid control!");
    }
}
