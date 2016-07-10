package Tests;

import FileSystem.FileSystem;
import Interfaces.Command;
import Utils.CommandFactory;
import Utils.OutputManager;
import Utils.ParametersManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandCdTest {

    Command cdCommand = CommandFactory.getCommand("cd");
    String pwdPath = "";

    private void login() {
        Command newuserCommand = CommandFactory.getCommand("newuser");

        ParametersManager.setParameters("root rootpass root root");

        newuserCommand.execute();

        ParametersManager.flushParameters();

        Command loginCommand = CommandFactory.getCommand("login");

        ParametersManager.setParameters("root rootpass");

        loginCommand.execute();

        ParametersManager.flushParameters();
    }

    @Before
    public void setUp() throws Exception {
        login();

        Command pwdCommand = CommandFactory.getCommand("pwd");

        pwdCommand.execute();

        pwdPath = OutputManager.getOutput();
    }

    @Test
    public void testExecute() throws Exception {
        cdCommand.execute();

        assertEquals(pwdPath, FileSystem.getFileSystem().currentDirectory.getCurrentPath());
    }
}