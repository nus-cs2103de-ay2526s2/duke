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
 * Class representing a command to mark a task as completed.
 */
public class MarkTaskCompleteCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for MarkTaskCompleteCommand class.
     *
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public MarkTaskCompleteCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the task at the specified index as completed in the specified task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if the user does not specify the index
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws MissingArgumentException, InvalidArgumentException, IOException {
        String indexAsString = commandArgs.get("/default");

        int index = Utility.parseInt(indexAsString);

        Task task = tasks.markTaskAsComplete(index);
        storage.saveTasksToFile(tasks);

        return String.format("Hark! I have marked this task as done, and 'tis well:\n%s",
                task);
    }
}
