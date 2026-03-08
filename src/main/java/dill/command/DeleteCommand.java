package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to remove a task from the list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of DeleteCommand and initializes it with the specified task.
     *
     * @param taskIndex The index of the task to be removed from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the removal of a task from the task list.
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
            Task removed = taskList.deleteTask(taskIndex);
            message += UiMessages.getDeleteSuccess(removed, taskList.getSize());
            storage.saveTasks(taskList);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(UiMessages.getTaskIdNotFound());
        } catch (StorageException e) {
            return message + "\n" + e.getMessage();
        }
    }
}
