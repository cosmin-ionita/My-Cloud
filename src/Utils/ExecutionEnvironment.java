package Utils;

import FileSystem.FileSystem;
import Interfaces.Command;
import SystemState.UserManager;

/**
 * Created by Ionita Cosmin on 12/28/2015.
 */
public class ExecutionEnvironment {

    private static String lastCommand = "";

    public static void invoke(String bruteCommand) {
        Command command = CommandFactory.getCommand(bruteCommand.split(" ")[0]);

        lastCommand = bruteCommand.split(" ")[0];

        // Remove command from bruteCommand
        String parameters = bruteCommand.replace(bruteCommand.split(" ")[0], "");

        // Remove the space after the command name
        if(parameters.length() > 1)
           parameters = parameters.substring(1);

        ParametersManager.setParameters(parameters);

        // For invalid commands, does nothing
        if(command != null) {
            command.execute();
        }
    }

    public static boolean wasCdCommand() {
        if(lastCommand.equals("cd")) {
            return true;
        }
        return false;
    }

    public static boolean wasLoginCommand() {
        if(lastCommand.equals("login")) {
            return true;
        }
        return false;
    }

    public static String getCurrentFileSystemPath() {
        return FileSystem.getFileSystem().currentDirectory.getCurrentPath();
    }

    public static String getCurrentUser() {
        return UserManager.getCurrentUserName();
    }

    public static boolean hasOutput() {
        return OutputManager.isOutput();
    }

    public static String showResult() {
        return OutputManager.getOutput();
    }

    public static void flushOutput() {
        OutputManager.flushOutput();
    }
}
