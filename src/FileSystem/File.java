package FileSystem;

import Interfaces.Command;
import Interfaces.Repository;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class File extends AbstractFileSystem {

    private boolean isBinary;

    private byte[] content;

    public File(String fileName) {
        this.name = fileName;
    }

    public void accept(Command command) {
        command.execute(this);
    }

    public String toString() {
        return this.name;
    }
}
