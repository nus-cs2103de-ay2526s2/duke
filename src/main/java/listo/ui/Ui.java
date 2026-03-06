package listo.ui;

import listo.task.Task;
import listo.task.TaskList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Handles all user interaction, including reading input and printing messages.
 */
public class Ui {
    private ArrayList<String> cheers;

    /**
     * Initializes the UI.
     */
    public Ui() {
        loadCheers();
    }

    /**
     * Prints any number of messages to the user.
     * This method uses Java Varargs (String... messages).
     *
     * @param messages The messages to be printed, each on a new line.
     */
    public void showToUser(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showToUser(message);
    }

    /**
     * Confirms that a task has been successfully added.
     *
     * @param t     The task that was added.
     * @param count The new total number of tasks.
     */
    public void showTaskAdded(Task t, int count) {
        showToUser("Got it. I've added this task:",
                "  " + t.toString(),
                "Now you have " + count + " tasks in the list.");
    }

    /**
     * Confirms that a task has been deleted.
     *
     * @param t     The task that was removed.
     * @param count The remaining number of tasks in the list.
     */
    public void showTaskDeleted(Task t, int count) {
        showToUser("Noted. I've removed this task:",
                "  " + t.toString(),
                "Now you have " + count + " tasks in the list.");
    }

    /**
     * Confirms that a task has been marked as done.
     *
     * @param t The task that was marked.
     */
    public void showTaskMarked(Task t) {
        showToUser("Nice! I've marked this task as done:", "  " + t.toString());
    }

    /**
     * Confirms that a task has been marked as not done.
     *
     * @param t The task that was unmarked.
     */
    public void showTaskUnmarked(Task t) {
        showToUser("OK, I've marked this task as not done yet:", "  " + t.toString());
    }

    /**
     * Displays all tasks currently in the list.
     * Refactored to use varargs.
     *
     * @param tasks The TaskList object containing the tasks.
     */
    public void showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            showToUser("Take a break! There's no tasks to be done for now.");
            return;
        }

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Things to do:");
        for (int i = 0; i < tasks.getSize(); i++) {
            messages.add((i + 1) + "." + tasks.getTask(i).toString());
        }

        showToUser(messages.toArray(new String[0]));
    }

    /**
     * Prints the list of tasks found by a keyword search.
     * Refactored to use varargs.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showFoundTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            showToUser("No matching tasks found.");
            return;
        }

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                messages.add((i + 1) + "." + tasks.getTask(i));
            } catch (Exception e) {
                messages.add("Error printing task.");
            }
        }

        showToUser(messages.toArray(new String[0]));
    }

    /**
     * Displays the list of tasks found for a specific date.
     *
     * @param tasks The list of matching tasks.
     * @param dateInput The date string used for the search.
     */
    public void showTasksOnDate(ArrayList<Task> tasks, String dateInput) {
        if (tasks.isEmpty()) {
            showToUser("No tasks found on " + dateInput);
            return;
        }

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Here are the tasks on " + dateInput + ":");
        for (int i = 0; i < tasks.size(); i++) {
            messages.add((i + 1) + "." + tasks.get(i).toString());
        }

        showToUser(messages.toArray(new String[0]));
    }

    /**
     * Loads cheering messages from the data/cheer.txt file.
     */
    private void loadCheers() {
        cheers = new ArrayList<>();
        try {
            java.io.InputStream inputStream = getClass().getResourceAsStream("/cheer.txt");

            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        cheers.add(line);
                    }
                }
            }
        } catch (Exception e) {
            cheers.add("Good job!");
            cheers.add("Well done!");
        }
    }

    /**
     * Prints a random encouraging quote to the user.
     */
    public void showCheer() {
        if (cheers.isEmpty()) {
            showToUser("Keep up the good work!");
        } else {
            Random rand = new Random();
            String randomCheer = cheers.get(rand.nextInt(cheers.size()));
            showToUser(randomCheer);
        }
    }

    /**
     * Displays the help message with a list of all available commands.
     */
    public void showHelp() {
        showToUser(
                "Here are the commands I understand:",
                "1. todo <description> - Adds a todo task.",
                "2. deadline <description> /by <date> - Adds a deadline.",
                "3. event <description> /from <start> /to <end> - Adds an event.",
                "4. list - Shows all tasks.",
                "5. mark <index> - Marks a task as done.",
                "6. unmark <index> - Marks a task as not done.",
                "7. delete <index> - Removes a task.",
                "8. filter <date> - Finds deadlines/events on a specific date.",
                "9. find <keyword> - Searches for tasks by keyword.",
                "10. cheer - Get a motivational message from me! \uD83C\uDF89",
                "11. help - Shows this list of commands.",
                "12. bye - Exits the application."
        );
    }
}