package FileSystem;

import Interfaces.Command;
import Interfaces.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class Directory implements Repository{

    private List<Repository> data;

    private Permissions permissions;

    private Date creationTime;

    private int dimension;

    public void accept(Command command) {
        command.execute(this);
    }
}
