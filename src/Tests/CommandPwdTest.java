package Tests;

import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.CommandFactory;
import Utils.OutputManager;
import Utils.ParametersManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandPwdTest {

    Command pwdCommand = CommandFactory.getCommand("pwd");
    String tempParameters = "";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {
        pwdCommand.execute();

        assertEquals(OutputManager.getOutput(), FileSystem.getFileSystem().currentDirectory.getCurrentPath());
    }
}