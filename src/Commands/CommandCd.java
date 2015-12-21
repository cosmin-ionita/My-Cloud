package Commands;

import Interfaces.Command;
import Interfaces.Repository;

/**
 * Created by Ionita Cosmin on 12/21/2015.
 */
public class CommandCd implements Command {

    public void execute(Repository repository) {
            repository.accept(this);
    }
}
