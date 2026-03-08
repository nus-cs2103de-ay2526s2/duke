package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to mark a task as complete.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of MarkCommand and initializes it with a specified task index.
     *
     * @param taskIndex The index of the task to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the task at the specified index as complete.
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
            taskList.markTask(taskIndex);
            message += UiMessages.getMarkSuccess(taskList.getTask(taskIndex));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(UiMessages.getTaskIdNotFound());
        } catch (StorageException e) {
            message += "\n" + e.getMessage();
        }
        return message;
    }
}
