package FileSystem;

import Interfaces.Repository;
import SystemState.UserManager;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/22/2015.
 */
public class FileSystem {

    public Directory currentDirectory = new Directory("home");

    private static FileSystem fileSystem = new FileSystem();

    private FileSystem() {
        currentDirectory.setCurrentPath("/home");
        currentDirectory.permissions = new Permissions(true, true, UserManager.getCurrentUserName());
        currentDirectory.creationTime = new Date();
    }

    public void restoreFileSystem() {
        fileSystem = new FileSystem();
    }

    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    public Repository getSystemNode(String nodeName) {
        return currentDirectory.getNode(nodeName);
    }

    public Repository getSystemNode(Directory directory, String nodeName) {
        return directory.getNode(nodeName);
    }
}
