package command;

import java.util.ArrayList;

import model.Task;
import ui.Ui;

// list
public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks) {
        Ui.printTasks();
    }
}