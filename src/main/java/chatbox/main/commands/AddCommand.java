package chatbox.main.commands;

import chatbox.main.*;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;

/**
 * Adds a new task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes the command by first checking for duplicates. If none exist,
     * adds the task to the list, saves it to storage, and returns a confirmation.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      The user interface for displaying messages (not used directly).
     * @param storage The storage object used to persist the updated task list.
     * @return A string confirmation of the added task.
     * @throws ChatBoxException If the task already exists or saving fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        assert task != null : "Task to be added should not be null";

        // C-DetectDuplicates Feature
        if (tasks.hasDuplicate(task)) {
            throw new ChatBoxException("OOPS! You already have this exact task in your list.");
        }

        tasks.add(task);
        storage.save(tasks.getAllTasks());
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}