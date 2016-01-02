package Utils;

import Cloud.CloudService;
import FileSystem.FileSystem;
import Interfaces.Command;
import SystemState.Logger;
import SystemState.UserManager;

/**
 * Created by Ionita Cosmin on 12/28/2015.
 */
public class ExecutionEnvironment {

    private static String lastCommand = "";

    public static void invoke(String bruteCommand) {

        if (bruteCommand.split(" ")[0].equals("upload")) {

            ParametersManager.setParameters(bruteCommand.split(" ")[1]);
            CloudService.executeUpload();

        } else if (bruteCommand.split(" ")[0].equals("sync")) {

            ParametersManager.setParameters(bruteCommand.split(" ")[1]);
            CloudService.executeSync();

        } else {
            Command command = CommandFactory.getCommand(bruteCommand.split(" ")[0]);

            lastCommand = bruteCommand.split(" ")[0];

            // Remove command from bruteCommand
            String parameters = bruteCommand.replace(bruteCommand.split(" ")[0], "");

            // Remove the space after the command name
            if (parameters.length() > 1)
                parameters = parameters.substring(1);

            ParametersManager.flushParameters();
            ParametersManager.setParameters(parameters);

            // For invalid commands, does nothing
            if (command != null) {
                command.execute();
            } else {
                OutputManager.setOutput("Invalid command!");
            }
        }
    }

    public static boolean wasCdCommand() {
        if (lastCommand.equals("cd")) {
            return true;
        }
        return false;
    }

    public static boolean wasLoginCommand() {
        if (lastCommand.equals("login")) {
            return true;
        }
        return false;
    }

    public static boolean wasLogoutComand() {
        if (lastCommand.equals("logout")) {
            return true;
        }
        return false;
    }

    public static boolean wasEchoComand() {
        if (lastCommand.equals("echo")) {
            return true;
        }
        return false;
    }

    public static boolean wasUserInfoComand() {
        if (lastCommand.equals("userinfo")) {
            return true;
        }
        return false;
    }

    public static boolean wasLsComand() {
        if (lastCommand.equals("ls")) {
            return true;
        }
        return false;
    }

    public static boolean isPooOutput() {
        return OutputManager.isPooOutput();
    }

    public static String getCurrentFileSystemPath() {
        return FileSystem.getFileSystem().currentDirectory.getCurrentPath();
    }

    public static void storeLogs() {
        Logger.storeLogs();
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
