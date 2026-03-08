package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.userinterface.UiMessages;

/**
 * Represents a command to clone a specific task in the task list.
 */
public class CloneCommand extends Command {
    private int taskIndex;

    /**
     * Creates an instance of CloneCommand and initializes it with the specified task index.
     *
     * @param taskIndex
     */
    public CloneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the cloning of a specific task in the task list.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     * @throws ExecutionException If the provided index is out of range.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) throws ExecutionException {
        StringBuilder messageBuilder = new StringBuilder();

        try {
            Task clonedTask = taskList.getTask(taskIndex).cloneTask();
            taskList.addTask(clonedTask);
            messageBuilder.append(UiMessages.getCloneSuccess(clonedTask, taskList.getSize()));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(UiMessages.getTaskIdNotFound());
        } catch (StorageException e) {
            messageBuilder.append("\n")
                    .append(e.getMessage());
        }

        return messageBuilder.toString();
    }
}
