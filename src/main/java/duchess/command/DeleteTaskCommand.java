package duchess.command;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.Task;
import duchess.task.TaskList;

/**
 * Class representing a command to delete a task from a task list.
 */
public class DeleteTaskCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for DeleteTaskCommand class.
     *
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public DeleteTaskCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Deletes the task at the specified index in the specified task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if no list index is provided
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws MissingArgumentException, InvalidArgumentException, IOException {
        String indexAsString = commandArgs.get("/default");

        int index = Utility.parseInt(indexAsString);

        Task task = tasks.removeTask(index);
        storage.saveTasksToFile(tasks);

        return String.format("Verily marked. I have stricken this labour from the rolls:\n%s\n"
                        + "Now doth thy hand hold %d task(s) within the ledger.",
                task, tasks.getSize());
    }
}
