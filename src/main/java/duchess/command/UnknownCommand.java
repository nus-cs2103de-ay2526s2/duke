package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing an unsupported command.
 */
public class UnknownCommand extends Command {
    /**
     * Prints an error message.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Hark, I know not what that doth signify.";
    }
}
