package Utils;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class OutputManager {

    private static String commandOutput = "";

    public static void setOutput(String output) {
        commandOutput = output;
    }

    public static String getOutput() {
        return commandOutput;
    }
}
