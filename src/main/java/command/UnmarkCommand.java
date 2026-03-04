package command;

import exception.CustomException;
import model.Task;
import model.TaskList;
import ui.Ui;

/**
 * Unmarks the task from done to not done
 */
public class UnmarkCommand extends MarkCommand {

    /**
     * Constructs new MarkCommand
     * @param taskIndex task index in taskList
     */
    public UnmarkCommand(int taskIndex) {
        super(taskIndex);
    }

    /**
     * run isInvalidIndex to check if index is valid
     * marks the task as done and print task status to show changes
     * warns the user if the task is originally not done
     * @return out String to the gui
     * @throw CustomException stating invalid index
     */
    @Override
    public String execute() throws CustomException {
        String out;
        if (index < 0 || index >= TaskList.getSize()) {
            throw new CustomException("Invalid Index!");
        }
        Task task = TaskList.getTask(index);
        if (!task.isDone()) {
            out = Ui.getMessageWithIndentation("Already not done");
        } else {
            task.unmarkAsDone();
            out = Ui.getMessageWithBotName("OK, I've marked this task as not done yet:");
            out += Ui.getMessageWithIndentation(task.toString());
        }
        return out;
    }


}