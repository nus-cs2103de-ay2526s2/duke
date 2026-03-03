package duchess.command;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;

/**
 * Class representing a command to create a todo task.
 */
public class CreateToDoCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for CreateToDoCommand class.
     *
     * @param commandArgs a map with a single delimiter-argument pair specifying the name of the task
     */
    public CreateToDoCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name from the command and creates a todo task with the specified argument.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if the user does not specify the name of the task
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws MissingArgumentException, IOException {
        String name = commandArgs.get("/default");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Hark, the task's name must not be barren!");
        }

        Task toDo = new ToDo(name);

        tasks.addTask(toDo);
        storage.saveTasksToFile(tasks);

        return String.format("Hark! I have appended this task:\n%s\nNow, thou hast %d task(s) upon thy scroll.",
                toDo, tasks.getSize());
    }
}
