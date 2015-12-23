package FileSystem;

import Interfaces.Command;
import Interfaces.Repository;

import java.util.ArrayList;
/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class Directory extends AbstractFileSystem {

    private ArrayList<Repository> data;

    private Directory parent;

    public Directory(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public void accept(Command command) {
        command.execute(this);
    }

    public void addDirectory(String directoryName) {
        data.add(new Directory(directoryName)); // legare de parent
    }

    public void addFile(String fileName) {
        data.add(new File(fileName));
    }

    public Repository getNode(String nodeName) {

        for (Repository repository : data) {

            System.out.println(repository.getClass().toString().split(" ")[1]);

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

    public String printContent() {
        String content = "";

        for (int i = 0; i < data.size(); i++) {
            content += data.get(i) + " ";
        }
        return content;
    }

    public String toString() {
       return this.name;
    }
}
