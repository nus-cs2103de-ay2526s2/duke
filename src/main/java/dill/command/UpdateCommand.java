package dill.command;

import dill.exception.ExecutionException;
import dill.exception.StorageException;
import dill.quote.QuoteList;
import dill.storage.Storage;
import dill.task.Task;
import dill.task.TaskList;
import dill.task.UpdateFields;
import dill.userinterface.UiMessages;

/**
 * Represents a command to update a specific task in the list.
 */
public class UpdateCommand extends Command {
    private int taskIndex;
    private UpdateFields updateFields;

    /**
     * Creates an instance of UpdateCommand and initializes it with a specified task index and an UpdateFields object.
     *
     * @param taskIndex The index of the task to update.
     * @param updateFields The fields containing the new values to apply to the task.
     */
    public UpdateCommand(int taskIndex, UpdateFields updateFields) {
        this.taskIndex = taskIndex;
        this.updateFields = updateFields;
    }

    /**
     * Executes the update command by updating the respective fields of the specified task.
     *
     * @param taskList The list of tasks to be executed on.
     * @param storage The data storage handler for saving and loading tasks.
     * @param quoteList The list of quotes containing motivational messages.
     * @return The post-execution message to be displayed to the user.
     * @throws ExecutionException If the provided task index is out of range.
     */
    public String execute(TaskList taskList, Storage storage, QuoteList quoteList) throws ExecutionException {
        StringBuilder messageBuilder = new StringBuilder();

        try {
            Task task = taskList.getTask(taskIndex);
            String taskBefore = task.toString();
            task.updateTask(updateFields);
            messageBuilder.append(UiMessages.getTaskUpdateSuccess(taskBefore, task));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(UiMessages.getTaskIdNotFound());
        } catch (IllegalArgumentException e) {
            throw new ExecutionException(e.getMessage());
        } catch (StorageException e) {
            messageBuilder.append("\n")
                    .append(e.getMessage());
        }

        return messageBuilder.toString();
    }
}
