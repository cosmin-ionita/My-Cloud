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

        // Login
        Command newuserCommand = CommandFactory.getCommand("newuser");

        ParametersManager.setParameters("root rootpass root root");

        newuserCommand.execute();

        ParametersManager.flushParameters();

        Command loginCommand = CommandFactory.getCommand("login");

        ParametersManager.setParameters("root rootpass");

        loginCommand.execute();

        ParametersManager.flushParameters();

        // Create a test directory
        Command mkdirCommand = CommandFactory.getCommand("mkdir");

        ParametersManager.setParameters("folder");

        mkdirCommand.execute();

        ParametersManager.flushParameters();
    }

    @org.junit.Test
    public void testExecute() throws Exception {
        Directory currentDirectory = fileSystem.currentDirectory;

        command.execute();

        assertEquals(OutputManager.getOutput(), currentDirectory.getContent());
    }
}