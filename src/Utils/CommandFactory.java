package Utils;

import Commands.*;
import Interfaces.Command;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandFactory {

    public static Command getCommand(String commandType) {

        if(commandType.equals("ls")) {
            return new CommandLs();
        }
        else if(commandType.equals("cd")) {
            return new CommandCd();
        }
        else if(commandType.equals("mkdir")) {
            return new CommandMkdir();
        }
        else if(commandType.equals("touch")) {
            return new CommandTouch();
        }
        else if(commandType.equals("pwd")) {
            return new CommandPwd();
        }

        return null;
    }
}
