package FileSystem;

import Interfaces.Command;
import SystemState.UserManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class File extends FileSystemNode {

    private boolean isBinary;

    private byte[] content;

    public File(String fileName, Permissions permissions, int dimension) {
        this.name = fileName;
        this.permissions = permissions;
        this.dimension = dimension;
        this.creationTime = new Date();
    }

    public File(File file) {
        this(file.name, file.permissions, file.dimension);

        this.creationTime = file.creationTime;
        this.content = file.content;
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

    public int getDimension() {
        return this.dimension;
    }

    public boolean canRead() {
        if(this.permissions.read && UserManager.getCurrentUserName().equals(this.permissions.userName))
            return true;
        return false;
    }

    public boolean canWrite() {
        if(this.permissions.write && UserManager.getCurrentUserName().equals(this.permissions.userName))
            return true;
        return false;
    }

    public String getContent() {
        return new String(content);
    }

    public void fill(String content) {
        this.content = content.getBytes();
    }

    public String toString() {
        return this.name;
    }
}
