package command;

import java.util.List;

import exception.CustomException;
import model.Task;
import model.TaskList;
import ui.Ui;

/**
 * Marks the task as done.
 */
public class MarkCommand extends Command {
    public final int index;

    /**
     * Constructs new MarkCommand.
     * @param i task index in taskList
     */
    public MarkCommand(int i) {
        this.index = i;
    }

    /**
     * Checks if the task index is valid in TaskList.
     * @param index task index in TaskList
     * @param tasks TaskList
     * @return true if invalid index
     */
    public boolean isInvalidIndex(int index, List<Task> tasks) {
        return index < 0 || index >= tasks.size();
    }

    /**
     * Run isInvalidIndex to check if index is valid.
     * Marks the task as done and print task status to show changes.
     * Warns the user if the task has already been done.
     * @return out String to be printed in GUI
     */
    @Override
    public String execute() throws CustomException {
        if (isInvalidIndex(index, TaskList.getTasks())) {
            throw new CustomException("Invalid Index!");
        }
        String out;
        Task task = TaskList.getTask(index);

        if (task.isDone()) {
            out = Ui.getMessageWithIndentation("Already done!");
        } else {
            task.markAsDone();
            out = Ui.getMessageWithBotName(" Nice! I've marked this task as done:");
            out += Ui.getMessageWithIndentation(task.toString());
        }

        return out;
    }
}