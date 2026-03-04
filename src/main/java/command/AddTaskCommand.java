package command;

import model.Task;
import model.TaskList;

/**
 * Adds new task into tasklist
 */
public class AddTaskCommand extends Command {
    public final Task task;

    /**
     * constructs new Task
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * adds task to tasklist
     * prints the task status
      */
    @Override
    public String execute() {
        TaskList.addTask(task);
        String out = String.format(" I have added a new task: %s\n", task.getTaskName());
        out = out + task;
        return out;
    }
}