package FileSystem;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class Permissions {

    public boolean read;
    public boolean write;

    public String userName;

    public Permissions(boolean read, boolean write, String userName) {
        this.read = read;
        this.write = write;

        this.userName = userName;
    }
}
