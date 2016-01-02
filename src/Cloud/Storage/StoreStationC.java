package Cloud.Storage;

import FileSystem.Directory;
import Interfaces.Repository;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class StoreStationC extends StoreStation {

    private static StoreStationC instance = new StoreStationC();

    private StoreStationC() {
        data = new HashSet<>();
        Id = new MachineId();
    }

    public static StoreStationC getInstance() {
        return instance;
    }

    public void store(Repository entry) {
        data.add(entry);
    }

    public Repository search(String name) {
        for (Iterator it = data.iterator(); it.hasNext(); ) {
            Directory element = (Directory) it.next();

            if (element.toString().equals(name)) {
                return element;
            }
        }
        return null;
    }
}

