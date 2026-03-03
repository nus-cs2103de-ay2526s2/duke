package duchess.command;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to find outstanding tasks.
 */
public class FindOutstandingCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default");
    private final Map<String, String> commandArgs;

    public FindOutstandingCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Finds outstanding tasks at the specified date.
     *
     * <p>
     * Task must be uncompleted and end after the specified date.
     * Todos are never outstanding.
     * </p>
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if no date is provided
     * @throws InvalidArgumentException if the date provided is not a valid date
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws MissingArgumentException, InvalidArgumentException {
        String afterDateAsString = commandArgs.get("/default");

        LocalDate afterDate = Utility.parseDate(afterDateAsString);
        TaskList outstandingTasks = tasks.getOutstandingTasks(afterDate);

        if (outstandingTasks.isEmpty()) {
            return String.format("Verily, no tasks remain outstanding past %s!",
                    Utility.formatDate(afterDate));
        }

        return String.format("Hark, yon tasks of import that yet linger on thy scroll:\n%s",
                outstandingTasks);
    }
}
