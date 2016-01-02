package Utils;

/**
 * Created by Ionita Cosmin on 12/22/2015.
 */
public class ParametersManager {

    private static boolean isRecursive = false, allDetails = false;

    private static String bruteParameters;
    private static int parameterIndex = 0;

    public static void setParameters(String parameters) {
        bruteParameters = parameters;
        setParameterIndex();
    }

    public static boolean hasPermissions() {
        if(bruteParameters.split(" ").length == 2 && !bruteParameters.split(" ")[1].equals(""))
            return true;
        return false;
    }

    public static String getPermissions() {
        return bruteParameters.split(" ")[1];
    }

    private static void setParameterIndex() {
        if(bruteParameters.contains("-a")) {
            allDetails = true;
            parameterIndex++;
        }
        if(bruteParameters.contains("-r")) {
            isRecursive = true;
            parameterIndex++;
        }
        if(bruteParameters.contains("-ar")) {
            isRecursive = true;
        }
        if(bruteParameters.contains("-POO")) {
            OutputManager.setPooOutput();
            parameterIndex++;
        }
    }

    public static String getParameters() {
        return bruteParameters.split(" ")[parameterIndex];
    }

    public static String getBruteParameters() {
        return bruteParameters;
    }

    public static void flushParameters() {
        isRecursive = false;
        allDetails = false;

        bruteParameters = "";
        parameterIndex = 0;
    }

    public static boolean isRecursiveOption() {
        return isRecursive;
    }

    public static boolean allDetailsOption() {
        return allDetails;
    }

    public static boolean noParameters() {
        if(bruteParameters.equals("") || bruteParameters.split(" ").length < parameterIndex + 1) {
            return true;
        }
        return false;
    }
}
