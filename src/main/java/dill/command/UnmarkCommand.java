package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of UnmarkCommand and initializes it with a specified task index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as incomplete.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public String execute(TaskList taskList, Storage storage,
            QuoteList quoteList) throws ExecutionException {
        String message = "";
        try {
            taskList.unmarkTask(taskIndex);
            message += UiMessages.getUnmarkSuccess(taskList.getTask(taskIndex));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(UiMessages.getTaskIdNotFound());
        } catch (StorageException e) {
            message += "\n" + e.getMessage();
        }
        return message;
    }
}
