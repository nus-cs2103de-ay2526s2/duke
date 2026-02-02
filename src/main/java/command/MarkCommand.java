package command;

import java.util.ArrayList;

import model.Task;
import ui.Ui;

// mark
public class MarkCommand extends Command {
    public final int index;

    // Constructor
    public MarkCommand(int i) {
        this.index = i;
    }

    public boolean isInvalidIndex(int index, ArrayList<Task> tasks){
        if (index < 0 || index >= tasks.size()) {
            Ui.printString("Invalid index!");
            return true;
        }
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        if (isInvalidIndex(index, tasks)){
            return;
        }
        Task task = tasks.get(index);
        if (task.isDone) {
            Ui.printString("Already done!");
        }
        else {
            task.markAsDone();
            Ui.showMarkSuccess(task);
        }
    }
}