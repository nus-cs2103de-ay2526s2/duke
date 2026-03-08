package bob;

import java.util.ArrayList;

/**
 * Seperate UI class for GUI
 */
public class GuiUi extends Ui {
    private final StringBuilder out = new StringBuilder();


    private void block(String message) {
        out.append("------------------------------------\n");
        out.append(message).append("\n");
        out.append("------------------------------------\n");
    }

    @Override
    public void showWelcome() {
        block("Hi! I'm Bob!\nWhat can I do for you today?");
    }

    @Override
    public void showGoodbye() {
        block("Bye. See you soon!");
    }

    @Override
    public void showMessage(String message) {
        block(message);
    }

    @Override
    public void showTaskList(ArrayList<Task> tasks) {
        out.append("------------------------------------\n");
        if (tasks.isEmpty()) {
            out.append("No tasks yet.\n");
        }
        else {
            for (int i = 0; i < tasks.size(); i ++) {
            out.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        out.append("------------------------------------\n");
    }

    @Override
    public void showError(String error) {
        block("Error" + error);
    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        block("Got it. I've added this task:\n"
                + task
                + "\nNow you have " + taskCount + " tasks in the list"
        );
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        block("Alright. I've removed this task:\n"
                + task
                + "\nNow you have " + taskCount + " tasks in the list"
        );
    }

    @Override
    public void showTaskMarked(Task task) {
        block("Nice! I've marked this task as done:\n" + task);
    }

    @Override
    public void showTaskUnmarked(Task task) {
        block("Ok, I've marked this task as not done yet:\n" + task);
    }

    public String getOutput() {
        String s = out.toString().trim();
        if (s.isEmpty()) {
            return " ";
        }
        return s;
    }

    @Override
    public void showFindResults(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            block("No matching tasks found.");
            return;
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        block(sb.toString().trim());
    }

    @Override
    public void showCheer(String quote) {
        block(quote);
    }


}