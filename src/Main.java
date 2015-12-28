import Interfaces.Command;
import Utils.CommandFactory;
import Utils.OutputManager;
import Utils.ParametersManager;

public class Main {

    public static void main(String[] args) {

        /*Command myCommand = CommandFactory.getCommand("mkdir");

        ParametersManager.setParameters("myFolder");

        myCommand.execute();


        ParametersManager.setParameters("myFolder2");

        myCommand.execute();


        Command lsCommand = CommandFactory.getCommand("ls");

        lsCommand.execute();

        System.out.println(OutputManager.getOutput());

        Command touchCommand = CommandFactory.getCommand("touch");


        ParametersManager.setParameters("file.txt");

        touchCommand.execute();

        lsCommand.execute();

        System.out.println(OutputManager.getOutput());


        Command pwdCommand = CommandFactory.getCommand("pwd");

        pwdCommand.execute();

        System.out.println(OutputManager.getOutput());



        Command cdCommand = CommandFactory.getCommand("cd");

        ParametersManager.setParameters("myFolder");

        cdCommand.execute();


        ParametersManager.setParameters("file2.txt");

        touchCommand.execute();


        lsCommand.execute();

        System.out.println(OutputManager.getOutput());

        pwdCommand.execute();

        System.out.println(OutputManager.getOutput());



        ParametersManager.setParameters("../");

        cdCommand.execute();

        lsCommand.execute();

        System.out.println(OutputManager.getOutput());
        */


        String s = "rw";

        System.out.println(!s.equals(""));

    }
}
