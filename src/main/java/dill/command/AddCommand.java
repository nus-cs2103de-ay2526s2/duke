package dill.command;

import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to add a new task to the list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an instance of AddCommand and initializes it with the specified task.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the task associated with this AddCommand.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Executes the addition of the task to the provided task list.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) {
        taskList.addTask(task);
        String message = UiMessages.getAddSuccess(task, taskList.getSize());
        try {
            storage.saveTasks(taskList);
            return message;
        } catch (StorageException e) {
            return message + "\n" + e.getMessage();
        }
    }
}

