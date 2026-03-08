package bob;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Parser class
 * Handles parsing for Bob
 */
public class Parser {

    /**
     * parse with helper methods
     * @param input the input from the user
     * @param tasks the task list to perform the operations on
     * @param ui the user interface for display messages
     * @param storage the storage manager for saving tasks
     */
    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        String lowerInput = input.toLowerCase();

        if (lowerInput.equals("list")){
            ui.showTaskList(tasks.getTasks());

        } else if (lowerInput.startsWith("mark ")) {
            handleMark(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("unmark ")) {
            handleUnmark(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("todo")) {
            handleTodo(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("deadline")) {
            handleDeadline(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("event")) {
            handleEvent(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("delete")) {
            handleDelete(input, tasks, ui, storage);

        } else if (lowerInput.startsWith("find")) {
            handleFind(input, tasks, ui);

        } else if (input.trim().equalsIgnoreCase("cheer")) {
            handleCheer(ui, storage);

        }
        else if (lowerInput.equals("help")) {
            handleHelp(ui);

        } else {
            Task task = new Task(input);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showMessage("added: " + task);
        }

    }

    /**
     * Handle the mark command and mark as done
     * @param input the input string with task number
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage manager
     */
    private static void handleMark(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                ui.showError("Task number is out of range please try again.");
            } else {
                tasks.markTask(taskNumber);
                storage.save(tasks.getTasks());
                ui.showTaskMarked(tasks.getTask(taskNumber));
            }
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number. ");
        }
    }

    /**
     * Handles unmark command to mark as not done
     * @param input the input string containing the task number
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage manager
     */
    private static void handleUnmark(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                ui.showError("Task number is out of range please try again.");
            } else {
                tasks.unmarkTask(taskNumber);
                storage.save(tasks.getTasks());
                ui.showTaskUnmarked(tasks.getTask(taskNumber));
            }

        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number.");
        }
    }

    /**
     *  Handles todo command
     * @param input the input containing task
     * @param tasks the task list
     * @param ui the user interface
     * @param storage storage manager
     */
    private static void handleTodo(String input, TaskList tasks, Ui ui, Storage storage) {
        String taskDescription = input.substring(4).trim();
        if (taskDescription.isEmpty()){
            ui.showError("Please fill up the task todo.");
        }
        else{
            Task task = new ToDo(taskDescription);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task,tasks.size());
        }
    }

    /**
     * Handles deadline command
     * @param input the task input
     * @param tasks the task list
     * @param ui the user interface
     * @param storage storage manager
     */
    private static void handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length != 2) {
            ui.showError("Deadline format is wrong.\n"
                    + "Try: deadline <task> /by yyyy-mm-dd");
        } else {
            String taskDescription = parts[0].trim();
            String dateString = parts[1].trim();
            // Check for empty task or date
            if (taskDescription.isEmpty() || dateString.isEmpty()) {
                ui.showError("Task description or deadline is empty. Please fill it up.");
            } else {
                try {
                    // Convert date string to localdate object and validates format
                    LocalDate.parse(dateString);
                    // If valid then create a new deadline task
                    Task task = new Deadline(taskDescription, dateString);
                    tasks.addTask(task);
                    storage.save(tasks.getTasks());
                    ui.showTaskAdded(task, tasks.size());
                } catch (DateTimeException e) {
                    ui.showError("Invalid date format! Please use yyyy-mm-dd format (e.g. 2019-10-15)");
                }
            }
        }

    }

    /**
     * Handles event command
     * @param input the input task from user
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage manager
     */
    private static void handleEvent(String input, TaskList tasks, Ui ui, Storage storage) {
        String[] parts = input.substring(5).split(" /from | /to ");
        if (parts.length != 3){
            ui.showError("Event format is wrong.\n"
            + "Try: event <task> /from yyyy-mm-dd /to yyyy-mm-dd");
        }
        else {
            String taskDescription = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();

            if(taskDescription.isEmpty() || start.isEmpty() || end.isEmpty()) {
                ui.showError("Task description or start or end time should not be empty.");
            }
            else{
                try {
                    // Convert start string to localdate object and validates format
                    LocalDate.parse(start);
                    // Convert end string to localdate object and validates format
                    LocalDate.parse(end);
                    // If both dates are valid then create a new event task
                    Task task = new Event(taskDescription, start, end);
                    tasks.addTask(task);
                    storage.save(tasks.getTasks());
                    ui.showTaskAdded(task, tasks.size());
                } catch (DateTimeException e) {
                    ui.showError("Invalid date/time format! Please use yyyy-mm-dd (e.g. 2019-10-15)");
                }
            }
        }
    }

    /**
     * Handles delete command
     * @param input the input number
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage manager
     */
    private static void handleDelete(String input, TaskList tasks, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            ui.showError("Please enter a valid number");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            // Check if tasknumber out of range
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                ui.showError("Task number is out of range please try again.");
            }
            else {
                Task deletedTask = tasks.deleteTask(taskNumber);
                storage.save(tasks.getTasks());
                ui.showTaskDeleted(deletedTask, tasks.size());
            }
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid task number.");
        }
    }

    /**
     * Handles the find command and display tasks that match
     *
     * @param input the full user input
     * @param tasks the task list
     * @param ui the user interface
     */
    private static void handleFind(String input, TaskList tasks, Ui ui) {
        if (input.length() <= 5) {
            ui.showError("Please enter a keyword");
            return;
        }
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            ui.showError("Please provide a keyword.");
        }
        // Use findtask helper method to check for matching keywords
        ArrayList<Task> matches = tasks.findTasks(keyword);
        if (matches.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showFindResults(matches);
        }
    }

    /**
     * Handle cheer command
     * @param ui the user interface
     * @param storage the storage manager
     */
    private static void handleCheer(Ui ui, Storage storage) {
        String quote = storage.getRandomCheerQuote();
        ui.showCheer(quote);
    }


    /**
     * Handle help command
     * Gives list of all possible commands
     * @param ui the user interface
     */
    private static void handleHelp(Ui ui) {
        ui.showMessage(
                "Here are the commands you can use:\n"
                + "1. list\n"
                + " Shows all tasks. \n\n"
                + "2. todo <description> \n"
                + " Adds a todo task. \n\n"
                + "3. deadline <description> /by <yyyy-mm-dd> \n"
                + " Adds a deadline task. \n\n"
                + "4. event <description> /from <yyyy-mm-dd> / to <yyyy-mm-dd> \n"
                + " Adds an event task. \n\n"
                + "5. mark <task number> \n"
                + " Marks a task as done. \n\n"
                + "6. unmark <task number> \n"
                + " Marks a task as not done. \n\n"
                + "7. delete <task number> \n"
                + " Deletes a task. \n\n"
                + "8. find <keyword> \n"
                + " Finds tasks matching the keyword. \n\n"
                + "9. cheer\n"
                + " Shows a motivational quote. \n\n"
                + "10. bye\n"
                + " Exits Bob."
        );
    }
}
