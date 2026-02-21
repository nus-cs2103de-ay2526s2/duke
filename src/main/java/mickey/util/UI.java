package mickey.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import mickey.task.Task;

/**
 * Class that handles messages displayed to the user
 */
public class UI {
    private static final String LINE = "------------------------------------------";
    private Scanner entry;

    public UI() {
        this.entry = new Scanner(System.in);
    }

    /**
     * Displays welcome logo and message
     */
    public void welcomeLogo() {
        String logo = "( o.o )\n" // animal face
                    + "  >^< ";

        System.out.println(LINE);
        System.out.println(" Hello! I'm Mickey\n" + logo + "\n");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays goodbye message
     */
    public void sayBye() {
        System.out.println(LINE);
        System.out.println(" Chat says bye. Hope to see you again soon! :D ");
        System.out.println(LINE);
    }

    /**
     * Gets user input
     *
     * @return the user input
     */
    public String userInput() {
        return entry.nextLine().trim();
    }

    /**
     * Displays a line
     */
    public void nextLine() {
        System.out.println(LINE);
    }

    /**
     * Error for invalid date format
     */
    public void showInvalidDate() {
        System.out.println("Please enter a valid date in the format dd-MM-yyyy.");
    }

    /**
     * Error for due command
     */
    public void showDueCommandError() {
        System.out.println("Ensure you use the command 'due' followed by the date in the format dd-MM-yyyy.");
    }

    /**
     * Displays the matching tasks
     *
     * @param matchResults the matching tasks
     */
    public void showKeywordResults(ArrayList<Task> matchResults) {
        System.out.println("Here are the matching tasks:");
        if (matchResults.isEmpty()) {
            System.out.println("No tasks match the keyword.");
        } else {
            for (int i = 0; i < matchResults.size(); i++) {
                System.out.println((i + 1) + "." + matchResults.get(i).toString());
            }
        }
    }

    /**
     * Show tasks due on date entered
     */
    public void showDueTasks(ArrayList<Task> dueTasks, LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        System.out.println("Here are the tasks due on " + formattedDate + ":");
        if (dueTasks.isEmpty()) {
            System.out.println("No tasks are due on " + formattedDate + ".");
        } else {
            for (int i = 0; i < dueTasks.size(); i++) {
                System.out.println((i + 1) + "." + dueTasks.get(i).toString());
            }
        }
    }

    /**
     * Displays all tasks in the list
     */
    public void allTaskList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Displays invalid task
     */
    public void showInvalidTaskNumber() {
        System.out.println("Please choose a task number within the list.");
    }

    /**
     * Displays marked task message
     */
    public void showMarked() {
        System.out.println("Niceeee! I've marked this task as done:");
    }

    /**
     * Displays unmarked task message
     */
    public void showUnmarked() {
        System.out.println("Okie, I've marked this task as not done yet:");
    }

    /**
     * Displays the cheer quote
     *
     * @param quote the quote to display
     */
    public void showCheer(String quote) {
        System.out.println(" " + quote);
    }

    /**
     * Displays deleted task message
     */
    public void showDeleted(String task, int taskCount) {
        System.out.println("Okie, I've removed this task:");
        System.out.println(" " + task);
        if (taskCount == 0) {
            System.out.println(" Now you have no tasks in the list.");
        } else if (taskCount == 1) {
            System.out.println(" Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Displays number format error
     */
    public void showNumberFormatError() {
        System.out.println("Oops, please use the task number for selection.");
    }

    /**
     * Displays empty todo error
     */
    public void showEmptyTodoError() {
        System.out.println("Please enter a description of your todo item");
    }

    /**
     * Displays message that all tasks are done
     */
    public void showNoTask() {
        System.out.println("You are done with all your tasks! Good job!");
    }

    /**
     * Displays task added message and current tasks
     */
    public void showTaskAdded(String taskString, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + taskString);
        if (taskCount == 1) {
            System.out.println(" Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Displays deadline empty error
     */
    public void showDeadlineEmptyError() {
        System.out.println("Oops please specify the item and deadline");
    }

    /**
     * Displays missing date error
     */
    public void showDeadlineMissingDateError() {
        System.out.println("Remember to enter the deadline dateee!");
    }

    /**
     * Displays no description error
     */
    public void showDeadlineNoDescriptionError() {
        System.out.println("Hmmm there seems to be no description..");
    }

    /**
     * Displays event empty error
     */
    public void showEventEmptyError() {
        System.out.println("You havent entered any event details yet!");
    }

    /**
     * Displays missing dates error
     */
    public void showEventMissingDatesError() {
        System.out.println("You havent entered any event dates yet...");
    }

    /**
     * Displays task
     */
    public void showTask(int index, String task) {
        System.out.println(index + "." + task);
    }

    /**
     * Displays marked task
     */
    public void showMarkedTask(String description, String details) {
        if (details != null) {
            System.out.println("[X] " + description + " " + details);
        } else {
            System.out.println(" [X] " + description);
        }
    }

    /**
     * Displays unmarked task
     */
    public void showUnmarkedTask(String description) {
        System.out.println(" [ ] " + description);
    }

    /**
     * Close the scanner
     */
    public void close() {
        entry.close();
    }
}
