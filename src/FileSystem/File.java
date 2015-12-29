package FileSystem;

import Interfaces.Command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class File extends AbstractFileSystemNode {

    private boolean isBinary;

    private byte[] content;

    public File(String fileName, Permissions permissions) {
        this.name = fileName;
        this.permissions = permissions;
        this.creationTime = new Date();
    }

    public void accept(Command command) {
        command.execute(this);
    }

    public String getDetails() {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return "File: " + this.name + " " +
                         this.dimension + " " +
                         ((this.isBinary) ? "binary" : "text") + " " +
                         format.format(this.creationTime) + " " + this.permissions + "\n";
    }

    public String toString() {
        return this.name;
    }
}
