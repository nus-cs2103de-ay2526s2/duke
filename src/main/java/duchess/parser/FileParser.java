package duchess.parser;

import java.time.LocalDate;
import java.util.Set;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskType;
import duchess.task.ToDo;

/**
 * FileParser class for parsing tasks from a file.
 */
public class FileParser {
    private static final int TODO_NUMBER_OF_COMPONENTS = 2;
    private static final int DEADLINE_NUMBER_OF_COMPONENTS = 3;
    private static final int EVENT_NUMBER_OF_COMPONENTS = 4;
    private static final Set<String> VALID_COMPLETION_MARKERS = Set.of("1", "0");

    public static Task getTask(String rawTask) throws MissingArgumentException, InvalidArgumentException {
        String[] splitInput = Utility.splitIntoPair(rawTask, " \\| ");
        assert splitInput.length == 2;

        TaskType taskType = getTaskType(splitInput[0].toUpperCase());

        String[] taskComponents = splitInput[1].split(" \\| ");

        if (!VALID_COMPLETION_MARKERS.contains(taskComponents[0])) {
            throw new InvalidArgumentException("Invalid task completion marker!");
        }

        boolean isComplete = taskComponents[0].equals("1");

        if (taskComponents.length < 2) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        assert taskComponents.length >= 2;
        String name = taskComponents[1];

        if (Utility.isInvalidString(name)) {
            throw new InvalidArgumentException("Invalid task name!");
        }

        Task task = null;

        switch(taskType) {
        case TODO:
            if (taskComponents.length != TODO_NUMBER_OF_COMPONENTS) {
                throw new MissingArgumentException(String.format("Expected %d arguments for ToDo, received %d",
                        TODO_NUMBER_OF_COMPONENTS, taskComponents.length));
            }

            assert taskComponents.length == 2;
            task = new ToDo(name, isComplete);
            break;
        case EVENT:
            if (taskComponents.length != EVENT_NUMBER_OF_COMPONENTS) {
                throw new MissingArgumentException(String.format("Expected %d arguments for Event, received %d",
                        TODO_NUMBER_OF_COMPONENTS, taskComponents.length));
            }

            assert taskComponents.length == 4;
            String startDateAsString = taskComponents[2];
            String endDateAsString = taskComponents[3];

            LocalDate startDate = Utility.parseDate(startDateAsString);
            LocalDate endDate = Utility.parseDate(endDateAsString);

            task = new Event(name, startDate, endDate, isComplete);
            break;
        case DEADLINE:
            if (taskComponents.length != DEADLINE_NUMBER_OF_COMPONENTS) {
                throw new MissingArgumentException(String.format("Expected %d arguments for Deadline, received %d",
                        TODO_NUMBER_OF_COMPONENTS, taskComponents.length));
            }

            assert taskComponents.length == 3;
            String deadlineAsString = taskComponents[2];
            LocalDate deadline = Utility.parseDate(deadlineAsString);

            task = new Deadline(name, deadline, isComplete);
            break;
        case UNKNOWN:
            throw new InvalidArgumentException("Unknown task type!");
        default:
            break;
        }

        return task;
    }

    private static TaskType getTaskType(String input) {
        return TaskType.getTaskType(input);
    }
}
