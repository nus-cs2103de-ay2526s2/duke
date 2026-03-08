package bob;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Ui class to handle interaction with user
 * For CLI, messages printed directly to console
 * For GUI, messages are captured internally and returned
 * as strings
 *
 */
public class Ui {
    private Scanner scanner;
    private final boolean isCapture;
    private final StringBuilder out;
    private static final String lineDivider = "------------------------------------";

    /**
     * CLI Ui
     */
    public Ui() {
        this(false);
    }

    /**
     * Create Ui instance
     *
     * @param isCapture true means GUI
     *                  false means CLI
     */
    public Ui(boolean isCapture) {
        this.isCapture = isCapture;

        if (isCapture) {
            this.out = new StringBuilder();
            this.scanner = null;
        } else {
            this.out = null;
            this.scanner = new Scanner(System.in);
        }
    }
    /**
     *  Welcome message with BOB logo when the program starts
     */
    public void showWelcome() {
        String logo = " ____   ___   ____\n"
                + "|  ) / _ \\ |  )\n"
                + "|  _ \\| | |   _ \\\n"
                + "| |_) | |_|  |_) |\n"
                + "|____/ \\___/ |____/\n";
        append("Hello from\n" + logo +
                lineDivider + "\n"
                + "Hi! I'm BOB\n"
                + "What can I do for you today?\n\n");
    }

    /**
     * Display goodbye message after user exits
     */
    public void showGoodbye() {
        append(lineDivider + "\n"
                + "Bye. See you soon!\n\n"
                + lineDivider + "\n" );
    }

    /**
     * Reads a command from the user
     * @return the command entered by the user
     */
    public String readCommand () {
        return scanner.nextLine();
    }

    /**
     * Displays message to the user
     * @param message the message to display
     */
    public void showMessage (String message) {
        append(lineDivider + "\n"
                + message + "\n"
                + lineDivider + "\n" );
    }

    /**
     * Displays error message to the user with the specific error
     * @param error the error message to display
     */
    public void showError (String error) {
        append(lineDivider + "\n"
                + "Error: " + error + "\n"
                + lineDivider + "\n" );
    }

    /**
     * Display all tasks in the task list
     * @param tasks the ArrayList of tasks to display
     */
    public void showTaskList (ArrayList<Task> tasks) {
        append(lineDivider);
        for (int i = 0; i <tasks.size(); i++) {
            append((i + 1) + ". " + tasks.get(i));
        }
        append(lineDivider);
    }

    /**
     * Display confirmation after task is added
     * @param task the task added
     * @param taskCount the total number of tasks in the list
     */
    public void showTaskAdded (Task task, int taskCount) {
        append(lineDivider + "\n"
                + "Got it. I've added this task: \n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n"
                + lineDivider + "\n" );
    }

    /**
     * Display confirmation after task is deleted
     * @param task the task deleted
     * @param taskCount the total number of tasks in the list
     */
    public void showTaskDeleted (Task task, int taskCount) {
        append(lineDivider + "\n"
                + "Alright. I've removed this task: \n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n"
                + lineDivider + "\n" );
    }

    /**
     * Display a confirmation message for task marked
     * @param task the task marked as done
     */
    public void showTaskMarked(Task task) {
        append(lineDivider + "\n"
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + lineDivider + "\n" );
    }

    /**
     * Display a confirmation message for task unmarked
     * @param task the task marked as not done
     */
    public void showTaskUnmarked(Task task) {
        append(lineDivider + "\n"
                + "Ok, I've marked this task as not done yet:\n"
                + task + "\n"
                + lineDivider + "\n");
    }

    /**
     * Displays list of tasks that match the keyword
     * @param matches the list of matching tasks
     */
    public void showFindResults(ArrayList<Task> matches) {
        append("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i ++){
            append((i + 1) + "." + matches.get(i));
        }
    }

    /**
     * Display cheer quote
     * @param quote the quote to display
     */
    public void showCheer(String quote) {
        append(lineDivider + "\n"
                + quote + "\n"
                + lineDivider + "\n");
    }

    /**
     * Append helper method
     * @param s the string to be appended
     */
    private void append(String s) {
        if (isCapture) {
            out.append(s).append(System.lineSeparator());
        }
        else {
            System.out.println(s);
        }
    }

    /**
     * Returns captured output for GUI
     *
     * @return accumulated output string
     */
    public String getOutputAndClear() {
        if (!isCapture){
            return "";
        }
        String result = out.toString();
        out.setLength(0);
        return result;
    }



}
