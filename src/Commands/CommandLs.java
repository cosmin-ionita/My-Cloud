package Commands;

import FileSystem.AbstractFileSystem;
import FileSystem.Directory;
import FileSystem.File;
import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.Parameters;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandLs implements Command {

    public void execute() {
        FileSystem fileSystem = FileSystem.getFileSystem();

        if (Parameters.getParameters()[0].equals("")) {
            this.execute(fileSystem.currentDirectory);
        } else {
            Repository systemNode = fileSystem.getSystemNode(Parameters.getParameters()[0]);

            this.execute(systemNode);
        }
    }

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute(File file) {

    }

    public void execute(Directory directory) {
        if (Parameters.getParameters()[0] == "") {
            System.out.println(directory.printContent());
        }
    }
}
