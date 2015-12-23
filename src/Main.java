import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.Parameters;

public class Main {

    public static void main(String[] args) {
        String command = "mkdir myFolder";

        // parsing steps

        Command myCommand = CommandFactory.getCommand("mkdir");

        FileSystem fileSystem = FileSystem.getFileSystem();

        Parameters.setParameters("myFolder");

        myCommand.execute();




        String command3 = "mkdir myFolder2";

        // parsing steps

        Command myCommand3 = CommandFactory.getCommand("mkdir");

        FileSystem fileSystem2 = FileSystem.getFileSystem();

        Parameters.setParameters("myFolder2");

        myCommand.execute(fileSystem2.currentDirectory);


        String command2 = "ls";

        // parsing steps

        Parameters.setParameters("");
        Command myCommand2 = CommandFactory.getCommand("ls");

        myCommand2.execute();


    }
}
