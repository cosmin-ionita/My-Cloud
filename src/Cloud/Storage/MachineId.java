package Cloud.Storage;

import Interfaces.Command;
import Interfaces.Repository;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class MachineId implements Repository{

    public void accept(Command command) {
        System.out.println("Invalid control");
    }

    public String getDetails() {
        System.out.println("Invalid control");

        return null;
    }

    public String toString() {
        return "MachineId";
    }
}
