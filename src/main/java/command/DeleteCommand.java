package command;

import java.util.ArrayList;

import model.Task;
import ui.Ui;

// Delete
public class DeleteCommand extends Command {
    public final int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }

    public boolean isInvalidIndex(int index, ArrayList<Task> tasks){
        if (index < 0 || index >= tasks.toArray().length) {
            Ui.printString("\nInvalid index");
            return true;
        }
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks) {
        if (isInvalidIndex(index, tasks)) {
            return;
        }
        Task deleteTask = tasks.get(index);
        Ui.printDeleteTask(deleteTask);
        Ui.printString(deleteTask.toString());
        tasks.remove(deleteTask);
    }

}