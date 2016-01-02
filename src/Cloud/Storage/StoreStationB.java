package Cloud.Storage;

import FileSystem.Directory;
import Interfaces.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class StoreStationB extends StoreStation {

    private static StoreStationB instance = new StoreStationB();

    private StoreStationB() {
        data = new HashSet<>();
        Id = new MachineId();
    }

    public static StoreStationB getInstance() {
        return instance;
    }

    public void store(Repository entry) {
        data.add(entry);
    }

    public MachineId getMachineId() {
        return Id;
    }

    public Repository search(String name) {
        Iterator it = data.iterator();
        return (Repository)it.next();
    }
}

