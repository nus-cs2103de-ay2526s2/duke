package command;

import java.util.ArrayList;

import model.Task;
import ui.Ui;

// unmark
public class UnmarkCommand extends MarkCommand {

    // Constructor
    public UnmarkCommand(int i) {
        super(i);
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printString("Invalid index!");
            return;
        }
        Task task = tasks.get(index);
        if (!task.isDone) {
            Ui.printString("Already not done");
        } else {
            task.unmarkAsDone();
            Ui.showUnmarkSuccess(task);
        }
    }


}