package Cloud.Storage;
import Interfaces.Repository;

import java.util.HashSet;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public abstract class StoreStation {
    protected MachineId Id;
    protected HashSet<Repository> data;
    protected int dimension;

    protected abstract void store(Repository entry);
    protected abstract Repository search(String name);
}
