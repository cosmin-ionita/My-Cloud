package FileSystem;

import Interfaces.Command;
import Interfaces.Repository;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class File implements Repository {

    private String fileName;

    private int dimension;

    private Date creationTime;

    private Permissions permissions;

    private boolean isBinary;

    private byte[] content;

    public void accept(Command command) {
        command.execute(this);
    }
}
