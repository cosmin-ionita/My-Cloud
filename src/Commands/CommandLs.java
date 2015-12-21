package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandLs implements Command {

    public void execute(Repository repository) {
        repository.accept(this);
    }

    void execute(Directory dir) {

    }

    void execute(File file) {

    }
}
