package FileSystem;

import Interfaces.Repository;

/**
 * Created by Ionita Cosmin on 12/22/2015.
 */
public class FileSystem {

    public Repository currentDirectory = new Directory("home");

    private static FileSystem fileSystem = new FileSystem();

    private FileSystem() {

    }

    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    public Repository getSystemNode(String nodeName) {
        Directory currentDir = (Directory)currentDirectory;

        return currentDir.getNode(nodeName);
    }
}
