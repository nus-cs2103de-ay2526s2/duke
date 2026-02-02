package command;

import java.util.ArrayList;

import model.Task;
import ui.Ui;

// bye
public class ByeCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks) {
        Ui.printExitMessage();   // Prints goodbye message in UI
    }
}