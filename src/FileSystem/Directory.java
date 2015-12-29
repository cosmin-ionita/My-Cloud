package FileSystem;

import Interfaces.Command;
import Interfaces.Repository;
import SystemState.UserManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class Directory extends AbstractFileSystemNode {

    private ArrayList<Repository> data;

    private String currentPath;

    private Directory parent = null;

    public Directory(String name) {
        this.data = new ArrayList<>();
        this.name = name;
    }

    public void accept(Command command) {
        command.execute(this);
    }

    public void deleteNode(String nodeName) {
        Repository node = this.getNode(nodeName);
        this.data.remove(node);
    }

    public void setCurrentPath(String path) {
        this.currentPath = path;
    }

    public String getCurrentPath() {
        return this.currentPath;
    }

    public boolean isEmpty() {
        if(data.size() == 0)
            return true;
        return false;
    }

    public void addDirectory(String directoryName) {
        Directory newDirectory = new Directory(directoryName);

        newDirectory.parent = this;
        newDirectory.creationTime = new Date();
        newDirectory.currentPath = this.currentPath + "/" + directoryName;
        newDirectory.permissions = new Permissions(true, true, UserManager.getCurrentUserName());

        data.add(newDirectory);
    }

    public void addDirectory(String directoryName, String permissions) {
        Directory newDirectory = new Directory(directoryName);

        newDirectory.parent = this;
        newDirectory.creationTime = new Date();
        newDirectory.currentPath = this.currentPath + "/" + directoryName;
        newDirectory.permissions = new Permissions( permissions.contains("r"),
                                                    permissions.contains("w"),
                                                    UserManager.getCurrentUserName());
        data.add(newDirectory);
    }

    public void addFile(String fileName) {
        data.add(new File(fileName, FileSystem.getFileSystem().currentDirectory.permissions));
    }

    public Repository getNode(String nodeName) {

        for (Repository repository : data) {

            if (repository.getClass().toString().split(" ")[1].equals("FileSystem.Directory")) {
                Directory directory = (Directory) repository;

                if (directory.name.equals(nodeName))
                    return directory;

            } else if (repository.getClass().toString().split(" ")[1].equals("FileSystem.File")) {
                File file = (File) repository;

                if (file.name.equals(nodeName))
                    return file;
            }
        }

        return null;
    }

    public String getContent() {
        String content = "";

        for (int i = 0; i < data.size(); i++) {
            content += data.get(i) + " ";
        }
        return content;
    }

    public String getDetails() {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return  "Directory: " + this.name + " " +
                this.dimension + " " +
                format.format(this.creationTime) + " " + this.permissions + "\n";
    }

    public Directory getParent() {
        return this.parent;
    }

    public String toString() {
       return this.name;
    }
}
