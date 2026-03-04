package command;

import exception.CustomException;
import model.Task;
import model.TaskList;
import ui.Ui;

/**
 * DeleteCommand takes in index of the task
 * Deletes the task from TaskList
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * checks if index is valid in TaskList
     */
    public boolean isInvalidIndex(int index) {
        if (index < 0 || index >= TaskList.getSize()) {
            return true;
        }
        return false;
    }


    /**
     * runs isInvalid
     * deletes task from taskList
     * @return out String message to the user
     */
    @Override
    public String execute() throws CustomException {
        String out;
        if (isInvalidIndex(index)) {
            throw new CustomException("Invalid index");
        }
        Task deleteTask = TaskList.getTask(index);
        out = Ui.getMessageWithBotName(String.format(" I have removed this task: %s\n", deleteTask.getTaskName()));
        out += Ui.getMessageWithIndentation(deleteTask.toString());
        TaskList.remove(deleteTask);
        return out;
    }

}