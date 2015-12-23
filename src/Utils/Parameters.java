package Utils;

import java.util.Iterator;

/**
 * Created by Ionita Cosmin on 12/22/2015.
 */
public class Parameters{

    private static String bruteParameters;

    public static void setParameters(String parameters) {
        bruteParameters = parameters;
    }

    public static String[] getParameters() {
        return bruteParameters.split(" ");
    }
}
