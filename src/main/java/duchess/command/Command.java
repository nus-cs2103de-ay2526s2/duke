package duchess.command;

import java.io.IOException;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a generic user command.
 */
public abstract class Command {
    /**
     * Returns true if the command should end the main program, else false.
     *
     * @return boolean representing if the command should terminate the main program
     */
    public boolean isTerminatingCommand() {
        return false;
    }

    /**
     * Abstract generic execute method for all commands to complete their specified actions.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if commands do not receive their expected number of arguments
     * @throws InvalidArgumentException if commands do not receive their expected arguments in the correct format
     */
    public abstract String execute(TaskList tasks, Storage storage)
            throws MissingArgumentException, InvalidArgumentException, IOException;
}


