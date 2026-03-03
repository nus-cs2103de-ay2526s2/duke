package duchess.command;

import java.util.Map;
import java.util.Set;

import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to find tasks by keyword.
 */
public class FindTaskCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for FindTaskCommand class.
     * @param commandArgs a map with a single delimiter-argument pair representing a keyword
     */
    public FindTaskCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Finds tasks matching the specified keyword.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if no keyword is provided
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingArgumentException {
        String keyword = commandArgs.get("/default");

        if (Utility.isInvalidString(keyword)) {
            throw new MissingArgumentException("Hark! No word be spoken!");
        }

        TaskList matchedTasks = tasks.getMatchingTasks(keyword.toLowerCase());

        if (matchedTasks.isEmpty()) {
            return "Hark! No tasks of such sort art found within!";
        }

        return String.format("Hark, the tasks that doth align within thy roster:\n%s",
                matchedTasks);
    }
}
