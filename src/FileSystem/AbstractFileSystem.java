package FileSystem;

import Interfaces.Repository;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public abstract class AbstractFileSystem implements Repository {
    protected String name;
    protected Permissions permissions;
    protected Date creationTime;
    protected int dimension;
}
