package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandCd implements Command {

    public void execute(Repository repository) {
        repository.accept(this);
    }

    public void execute() {

    }

    public void execute(Directory directory) {

    }

    public void execute(File file) {
        System.out.println("You cannot change current directory to a file!");
    }

}
