package Commands;

import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.OutputManager;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandLs implements Command {

    private String recursiveOutput = "";

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();

        if (ParametersManager.noParameters()) {
            this.execute((Repository) fileSystem.currentDirectory);
        } else {
            Repository systemNode = fileSystem.getSystemNode(ParametersManager.getParameters());

            this.execute(systemNode);

            if (ParametersManager.isRecursiveOption())
                OutputManager.setOutput(recursiveOutput);

            ParametersManager.flushParameters();
        }
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(File file) {
        if (ParametersManager.allDetailsOption()) {

            if (ParametersManager.isRecursiveOption())
                recursiveOutput += file.getDetails();
            else
                OutputManager.setOutput(file.getDetails());
        } else {
            if (ParametersManager.isRecursiveOption())
                recursiveOutput += file.toString() + " ";
            else
                OutputManager.setOutput(file.toString());
        }
    }

    public void execute(Directory directory) {

        if (ParametersManager.isRecursiveOption()) {

            if (ParametersManager.allDetailsOption())
                recursiveOutput += directory.getDetails();
            else
                recursiveOutput += directory.toString() + " ";

            if (!directory.isEmpty()) {

                String[] content = directory.getContent().split(" ");

                for(int i = 0; i<content.length; i++) {
                    System.out.println(content[i]);
                }

                for (int i = 0; i < content.length; i++) {
                    Repository systemNode = directory.getNode(content[i]);
                        this.execute(systemNode);
                }
            }
        } else {
            if (ParametersManager.allDetailsOption()) {

                String output = "";

                FileSystem fileSystem = FileSystem.getFileSystem();

                String content = directory.getContent();

                for (String element : content.split(" ")) {
                    Repository systemNode = fileSystem.getSystemNode(element);
                    output += systemNode.getDetails();
                }

                OutputManager.setOutput(output);
                ParametersManager.flushParameters();

            } else {
                OutputManager.setOutput(directory.getContent());
                ParametersManager.flushParameters();
            }
        }
    }
}
