package FileSystem;

import Cloud.Storage.MachineId;
import Exceptions.MyInvalidPathException;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.Logger;
import SystemState.UserManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class Directory extends FileSystemNode {

    private ArrayList<Repository> data;

    private String currentPath;

    private Directory parent = null;

    public Directory(String name) {
        this.data = new ArrayList<>();
        this.name = name;
        this.dimension = 0;
    }

    public Directory(Directory directory) {
        data = new ArrayList<>(directory.data);

        name = directory.name;
        dimension = directory.dimension;
        currentPath = directory.currentPath;
        parent = directory.parent;
        creationTime = directory.creationTime;
        permissions = directory.permissions;
    }

    public void accept(Command command) {
        try {
            command.execute(this);
        } catch (MyInvalidPathException e) {
            Logger.log(e.toString());
        }
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
        if (data.size() == 0)
            return true;
        return false;
    }

    public boolean canRead() {
        String userName = UserManager.getCurrentUserName();

        if((this.permissions.read &&
                (userName.equals(this.permissions.userName) || this.name == "home") &&
                !userName.equals("guest")) ||
                userName.equals("root"))

            return true;
        return false;
    }

    public boolean canWrite() {
        String userName = UserManager.getCurrentUserName();

        if((this.permissions.write &&
                (userName.equals(this.permissions.userName) || this.name == "home") &&
                !userName.equals("guest")) ||
                userName.equals("root"))

            return true;
        return false;
    }

    public boolean canMove() {
        if(!UserManager.getCurrentUserName().equals("guest"))
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
        newDirectory.permissions = new Permissions(permissions.contains("r"),
                permissions.contains("w"),
                UserManager.getCurrentUserName());

        data.add(newDirectory);
    }

    public void addNode(Repository repository) {
        data.add(repository);
    }

    public void addFile(String fileName, int dimension) {
        data.add(new File(fileName, FileSystem.getFileSystem().currentDirectory.permissions, dimension));

        if (dimension > 0) {

            Directory directory = this;

            while (directory != null) {
                directory.dimension += dimension;

                directory = directory.getParent();
            }
        }
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
            } else if (repository.getClass().toString().split(" ")[1].equals("Cloud.Storage.MachineId")) {
                MachineId id = (MachineId) repository;

                if (id.toString().equals(nodeName))
                    return id;
            }
        }

        return null;
    }

    public boolean contains(Repository node) {
        return this.data.contains(node);
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

        return "Directory: " + this.name + " " +
                this.dimension + " " +
                format.format(this.creationTime) + " " + this.permissions + "\n";
    }

    public Directory getParent() {
        return this.parent;
    }

    public int getDimension() {
        return this.dimension;
    }

    public String toString() {
        return this.name;
    }
}
