package Tests;

import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.CommandFactory;
import Utils.ParametersManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandRmTest {

    Command rmCommand = CommandFactory.getCommand("rm");
    String tempParameters = "";

    @Before
    public void setUp() throws Exception {
        ParametersManager.setParameters("myFolder");
        tempParameters = "myFolder";
    }

    @Test
    public void testExecute() throws Exception {
        rmCommand.execute();

        Repository rep = FileSystem.getFileSystem().currentDirectory.getNode(tempParameters);

        assertNull(rep);
    }
}