package Commands;

import FileSystem.Directory;
import FileSystem.File;
import Interfaces.Command;
import Interfaces.Repository;
import SystemState.Logger;
import SystemState.UserManager;
import Utils.ParametersManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/29/2015.
 */
public class CommandLogin implements Command {

    public void execute() {
        String bruteParameters = ParametersManager.getBruteParameters();

        boolean loggedUser = false;

        try {
            RandomAccessFile stream = new RandomAccessFile("Users.txt", "rw");
            String line = stream.readLine();

            while (line != null) {

                if (bruteParameters.split(" ")[0].equals(line.split(" ")[0]) &&
                        bruteParameters.split(" ")[1].equals(line.split(" ")[1])) {

                    String userName = line.split(" ")[0];
                    String name = line.split(" ")[2];
                    String surname = line.split(" ")[3];

                    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                    String creationDate = line.split(" ")[4];
                    creationDate = creationDate.replace("$", " ");

                    try {
                        Date userCreationDate = formatter.parse(creationDate);
                        Date userLastLoginDate = formatter.parse(formatter.format(new Date()));

                        UserManager.changeCurrentUser(name, surname, userName, userCreationDate, userLastLoginDate);

                        Logger.log("\r\nLogin_User: " + userName + " at " + new Date());

                        loggedUser = true;
                    }
                    catch(ParseException e) {
                        e.printStackTrace();
                    }
                }

                line = stream.readLine();
            }

            stream.close();

            if(loggedUser) {
                updateUserLastLoginDate();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUserLastLoginDate() {
        try {
            RandomAccessFile stream = new RandomAccessFile("Users.txt", "rw");
            String fileContent = "";
            String line = "";

            while(line != null) {

                line = stream.readLine();

                if(line == null)
                    continue;

                if(line.split(" ")[0].equals(ParametersManager.getBruteParameters().split(" ")[0])) {
                    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd$HH:mm:ss");

                    String lastDate = line.split(" ")[line.split(" ").length - 1];

                    line = line.substring(0, line.length() - lastDate.length());

                    line = line + formatter.format(new Date());
                }

                fileContent += line + "\r\n";
            }

            stream.seek(0);
            stream.write(fileContent.getBytes());
            stream.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(Directory directory) {
        System.out.println("Invalid control");
    }

    public void execute(File file) {
        System.out.println("Invalid control");
    }

    public void execute(Repository repository) {
        System.out.println("Invalid control");
    }
}
