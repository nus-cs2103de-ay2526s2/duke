package chatbox.main.commands;

import chatbox.main.*;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;
/**
 * Executes the delete command.
 * Removes the task from the task list and then saves the updated list to storage,
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the delete command by removing the task from the list and
     * updating the storage.
     *
     * @param tasks   The list of tasks from which the task will be removed.
     * @param ui      The user interface instance (not directly used here).
     * @param storage The storage object used to save the updated task list.
     * @return A string confirmation of the removed task and the new list size.
     * @throws ChatBoxException If the provided index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        try {
            Task removed = tasks.get(index);
            tasks.delete(index);
            storage.save(tasks.getAllTasks());
            return "Noted. I've removed this task:\n  " + removed +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBoxException("Invalid task number.");
        }
    }
}