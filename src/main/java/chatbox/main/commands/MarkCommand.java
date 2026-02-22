package chatbox.main.commands;

import chatbox.main.ChatBoxException;
import chatbox.main.Storage;
import chatbox.main.Ui;
import chatbox.main.tasks.Task;
import chatbox.main.tasks.TaskList;

/**
 * Handles the logic for marking a task as done or not done.
 * This command updates the task status and saves the updated list to storage.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isMarked; // Renamed from isMark

    /**
     * Creates a MarkCommand to modify a task's status.
     * @param index    The zero-indexed position of the task in the list.
     * @param isMarked True to mark as done, false to unmark.
     */
    public MarkCommand(int index, boolean isMarked) {
        this.index = index;
        this.isMarked = isMarked;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBoxException {
        try {
            Task task = tasks.get(index);

            if (isMarked) {
                task.markAsDone();
            } else {
                task.unmarkAsDone();
            }

            storage.save(tasks.getAllTasks());

            if (isMarked) {
                return "Great job! I've marked this task as done:\n  " + task;
            } else {
                return "OK take your time, I've marked this task as not done yet:\n  " + task;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBoxException("Invalid task number. Could you check the list again?");
        }
    }
}