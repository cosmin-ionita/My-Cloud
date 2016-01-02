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
 * Created by Ionita Cosmin on 12/24/2015.
 */
public class CommandTouchTest {

    Command touchCommand = CommandFactory.getCommand("touch");
    String tempParameters = "";

    @Before
    public void setUp() throws Exception {
        // Login
        Command newuserCommand = CommandFactory.getCommand("newuser");

        ParametersManager.setParameters("root rootpass rootName rootName");

        newuserCommand.execute();

        ParametersManager.flushParameters();

        Command loginCommand = CommandFactory.getCommand("login");

        ParametersManager.setParameters("root rootpass");

        loginCommand.execute();

        ParametersManager.flushParameters();

        ParametersManager.setParameters("file.txt");
        tempParameters = "file.txt";
    }

    @Test
    public void testExecute() throws Exception {
        touchCommand.execute();

        Repository rep = FileSystem.getFileSystem().currentDirectory.getNode(tempParameters);

        assertNotNull(rep);
    }
}