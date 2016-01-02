package Utils;

import javax.swing.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class OutputManager {

    private static String commandOutput = "";
    private static boolean pooOutput = false;

    public static void setOutput(String output) {
        commandOutput = output;
    }

    public static boolean isOutput() {
        if(commandOutput.equals(""))
            return false;
        return true;
    }

    public static boolean isPooOutput() {
        return pooOutput;
    }

    public static void setPooOutput() {
        pooOutput = true;
    }

    public static String getOutput() {
        return commandOutput;
    }

    public static void flushOutput() {
        commandOutput = "";
        pooOutput = false;
    }
}
