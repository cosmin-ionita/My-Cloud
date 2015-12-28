package Tests;

import FileSystem.FileSystem;
import FileSystem.Directory;
import Interfaces.Command;
import Utils.CommandFactory;
import Utils.OutputManager;
import Utils.ParametersManager;

import static org.junit.Assert.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandLsTest {

    Command command = CommandFactory.getCommand("ls");
    FileSystem fileSystem = FileSystem.getFileSystem();

    @org.junit.Before
    public void setUp() throws Exception {
        ParametersManager.setParameters("");
    }

    @org.junit.Test
    public void testExecute() throws Exception {
        Directory currentDirectory = fileSystem.currentDirectory;

        command.execute();

        assertEquals(OutputManager.getOutput(), currentDirectory.getContent());
    }
}