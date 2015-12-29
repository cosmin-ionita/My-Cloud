package Utils;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class OutputManager {

    private static String commandOutput = "";

    public static void setOutput(String output) {
        commandOutput = output;
    }

    public static boolean isOutput() {
        if(commandOutput.equals(""))
            return false;
        return true;
    }

    public static String getOutput() {
        return commandOutput;
    }

    public static void flushOutput() {
        commandOutput = "";
    }
}
