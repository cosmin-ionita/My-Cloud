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
        if(bruteParameters.split(" ")[1] != null && !bruteParameters.split(" ")[1].equals(""))
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
    }

    public static String getParameters() {
        return bruteParameters.split(" ")[parameterIndex];
    }

    public static void flushParameters() {
        isRecursive = false;
        allDetails = false;

        bruteParameters = "";
    }

    public static boolean isRecursiveOption() {
        return isRecursive;
    }

    public static boolean allDetailsOption() {
        return allDetails;
    }

    public static boolean noParameters() {
        if(bruteParameters.equals("")) {
            return true;
        }
        return false;
    }
}
