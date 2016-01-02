package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

import java.util.regex.Pattern;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandLs implements Command {

    private String recursiveOutput = "";
    private String regexOutput = "";
    private boolean regexMode = false;

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();

        if (ParametersManager.noParameters()) {
            this.execute(fileSystem.currentDirectory);
        } else {
            if (!isRegex(ParametersManager.getParameters())) {
                Repository systemNode = fileSystem.getSystemNode(ParametersManager.getParameters());

                this.execute(systemNode);

                if (ParametersManager.isRecursiveOption())
                    OutputManager.setOutput(recursiveOutput);

                ParametersManager.flushParameters();
            } else {
                handleRegexMode(fileSystem);
            }
        }
    }

    private void handleRegexMode(FileSystem fileSystem) {
        Pattern regex = Pattern.compile(ParametersManager.getParameters());
        String content = fileSystem.currentDirectory.getContent();

        regexMode = true;

        for (String node : content.split(" ")) {
            if (regex.matcher(node).matches()) {
                Repository systemNode = fileSystem.getSystemNode(node);

                this.execute(systemNode);

                OutputManager.setOutput(regexOutput);
            }
        }
    }

    private boolean isRegex(String removalNode) {
        for (int i = 0; i < removalNode.length(); i++) {
            if (!Character.isLetterOrDigit(removalNode.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(File file) {
        if (file.canRead()) {

            if (ParametersManager.allDetailsOption()) {

                if (ParametersManager.isRecursiveOption())
                    recursiveOutput += file.getDetails();
                else if (regexMode)
                    regexOutput += file.getDetails();
                else
                    OutputManager.setOutput(file.getDetails());
            } else {
                if (ParametersManager.isRecursiveOption())
                    recursiveOutput += file.toString() + " ";
                else if (regexMode)
                    regexOutput += file.toString() + " ";
                else
                    OutputManager.setOutput(file.toString());
            }
        } else {
            OutputManager.setOutput("You do not have permissions to read that file.");
        }
    }

    public void execute(Directory directory) {

        if (directory.canRead()) {
            if (ParametersManager.isRecursiveOption()) {
                handleRecursiveOption(directory);
            } else {
                if (ParametersManager.allDetailsOption()) {
                    handleAllDetailsOption(directory);
                } else {
                    handleNoOption(directory);
                }
            }
        } else {
            OutputManager.setOutput("You do not have permissions to see the contents of that directory.");
        }
    }

    private void handleNoOption(Directory directory) {

        if (regexMode)
            regexOutput += directory.getContent();
        else {
            OutputManager.setOutput(directory.getContent());
            ParametersManager.flushParameters();
        }
    }

    private void handleAllDetailsOption(Directory directory) {
        if(regexMode)
            regexOutput += directory.getDetails();

        if (!directory.isEmpty()) {
            String output = "";

            FileSystem fileSystem = FileSystem.getFileSystem();

            String content = directory.getContent();

            for (String element : content.split(" ")) {
                Repository systemNode = fileSystem.getSystemNode(directory, element);

                output += systemNode.getDetails();
            }

            if (regexMode) {
                regexOutput += output + " ";
            } else {
                OutputManager.setOutput(output);
                ParametersManager.flushParameters();
            }
        }
    }

    private void handleRecursiveOption(Directory directory) {
        if (ParametersManager.allDetailsOption())
            recursiveOutput += directory.getDetails();
        else
            recursiveOutput += directory.toString() + " ";

        if (!directory.isEmpty()) {

            String[] content = directory.getContent().split(" ");

            for (int i = 0; i < content.length; i++) {
                Repository systemNode = directory.getNode(content[i]);
                this.execute(systemNode);
            }
        }
    }
}
