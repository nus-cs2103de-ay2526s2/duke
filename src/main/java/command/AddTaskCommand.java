package command;

import java.util.ArrayList;

import model.Task;
import model.TaskList;
import ui.Ui;

// New Task
public class AddTaskCommand extends Command {
    public final Task task;

    // Constructor
    public AddTaskCommand(Task task){
        this.task = task;
    }
    
    @Override
    public void execute(ArrayList<Task> tasks) {
        TaskList.addTask(task);
        Ui.printNewTask(task);
        Ui.printString(task.toString());
    }
}