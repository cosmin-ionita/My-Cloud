package Interfaces;

import FileSystem.Directory;
import FileSystem.File;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public interface Command {
    void execute();
    void execute(File file);
    void execute(Directory directory);
    void execute(Repository repository);
}
