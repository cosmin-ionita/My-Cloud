package FileSystem;

import Interfaces.Command;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class File extends AbstractFileSystemNode {

    private boolean isBinary;

    private byte[] content;

    public File(String fileName) {
        this.name = fileName;
    }

    public File(String fileName, Permissions permissions) {
        this.name = fileName;
        this.permissions = permissions;
    }

    public void accept(Command command) {
        command.execute(this);
    }

    public String getDetails() {
        return "File: " + this.name + " " +
                         this.dimension +
                        ((this.isBinary) ? "binary" : "text") + " " +
                         this.creationTime + " " + this.permissions;
    }

    public String toString() {
        return this.name;
    }
}
