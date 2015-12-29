package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.ParametersManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/29/2015.
 */
public class CommandNewUser implements Command {

    public void execute() {
        String bruteParameters = ParametersManager.getBruteParameters();

        try {
            java.io.File file = new java.io.File("Users.txt");

            RandomAccessFile stream = new RandomAccessFile(file, "rw");

            Date currentTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd$HH:mm:ss");

            bruteParameters = bruteParameters + " " + format.format(currentTime) + " " + format.format(currentTime)+ "\r\n";

            stream.seek(file.length());
            stream.write(bruteParameters.getBytes());

            stream.close();
        }
        catch(FileNotFoundException exception) {
            exception.printStackTrace();
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public void execute(File file) {
        System.out.println("Invalid control");
    }

    public void execute(Directory directory) {
        System.out.println("Invalid control");
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }
}
