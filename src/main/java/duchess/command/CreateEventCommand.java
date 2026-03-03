package duchess.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskList;

/**
 * Class representing a command to create an event.
 */
public class CreateEventCommand extends Command {
    public static final Set<String> DELIMITERS = Set.of("/default", "/from", "/to");
    private final Map<String, String> commandArgs;

    /**
     * Constructor for CreateEventCommand class.
     *
     * @param commandArgs a map of delimiter-argument pairs specifying the name, start and end dates of the task
     */
    public CreateEventCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name, start and end dates from the command and creates an Event task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if the user does not specify the name, start or end dates of the task
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws InvalidArgumentException, MissingArgumentException, IOException {
        String name = commandArgs.get("/default");
        String startDateAsString = commandArgs.get("/from");
        String endDateAsString = commandArgs.get("/to");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Hark, the task's name must not be barren!");
        }

        LocalDate startDate = Utility.parseDate(startDateAsString);
        LocalDate endDate = Utility.parseDate(endDateAsString);

        if (startDate.isAfter(endDate)) {
            throw new InvalidArgumentException("Hark! The day of inception may not succeed the day of conclusion!");
        }

        Task event = new Event(name, startDate, endDate);

        tasks.addTask(event);
        storage.saveTasksToFile(tasks);

        return String.format("Hark! I have appended this task:\n%s\nNow, thou hast %d task(s) upon thy scroll.",
                event, tasks.getSize());
    }
}
