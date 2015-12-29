package FileSystem;

import Interfaces.Repository;
import SystemState.UserManager;

/**
 * Created by Ionita Cosmin on 12/22/2015.
 */
public class FileSystem {

    public Directory currentDirectory = new Directory("home");

    private static FileSystem fileSystem = new FileSystem();

    private FileSystem() {
        currentDirectory.setCurrentPath("/home");
        currentDirectory.permissions = new Permissions(true, true, UserManager.getCurrentUserName());
    }

    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    public Repository getSystemNode(String nodeName) {
        return currentDirectory.getNode(nodeName);
    }
}
