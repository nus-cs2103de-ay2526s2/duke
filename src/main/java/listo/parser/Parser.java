package listo.parser;

import listo.command.CommandType;
import listo.exception.ListoException;
import listo.task.Deadline;
import listo.task.Event;
import listo.task.Task;
import listo.task.TaskList;
import listo.task.Todo;
import listo.ui.Ui;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the logic for parsing and executing user commands.
 * Contains static methods to process specific command types.
 */
public class Parser {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Parser() {
        // empty
    }

    /**
     * Parses the user input and executes the corresponding command.
     * Separates the command word from the arguments and delegates to specific handler methods.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the command is invalid or execution fails.
     */
    public static void parseCommand(String input, TaskList tasks, Ui ui) throws ListoException {
        if (input.trim().isEmpty()) {
            throw new ListoException("OOPS!!! Hello? 🎤 I can't hear you! Please type a command.");
        }

        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        CommandType command = getCommandType(commandWord);

        switch (command) {
            case LIST:
                ui.showList(tasks);
                break;
            case MARK:
                handleMark(arguments, tasks, ui);
                break;
            case UNMARK:
                handleUnmark(arguments, tasks, ui);
                break;
            case DELETE:
                handleDelete(arguments, tasks, ui);
                break;
            case TODO:
                handleTodo(arguments, tasks, ui);
                break;
            case DEADLINE:
                handleDeadline(arguments, tasks, ui);
                break;
            case EVENT:
                handleEvent(arguments, tasks, ui);
                break;
            case FILTER:
                handleFilter(arguments, tasks, ui);
                break;
            case FIND:
                handleFind(arguments, tasks, ui);
                break;
            case CHEER:
                ui.showCheer();
                break;
            case HELP:
                ui.showHelp();
                break;
            case BYE:
                break;
            default:
                throw new ListoException("OOPS!!! Sorry, I don't know what you mean :(");
        }
    }

    /**
     * Converts a string command word into a CommandType enum.
     *
     * @param commandWord The first word of the user input.
     * @return The corresponding CommandType, or UNKNOWN if not recognized.
     */
    private static CommandType getCommandType(String commandWord) {
        try {
            return CommandType.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Handles the 'mark' command to set a task as completed.
     *
     * @param args  The command arguments (expecting the task index).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the index is invalid or missing.
     */
    public static void handleMark(String args, TaskList tasks, Ui ui) throws ListoException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number.");
            }
            tasks.markDone(index);
            ui.showTaskMarked(tasks.getTask(index));
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number." +
                    "\nUsage: mark <task number>");
        }
    }

    /**
     * Handles the 'unmark' command to set a task as incomplete.
     *
     * @param args  The command arguments (expecting the task index).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the index is invalid or missing.
     */
    public static void handleUnmark(String args, TaskList tasks, Ui ui) throws ListoException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number.");
            }
            tasks.markNotDone(index);
            ui.showTaskUnmarked(tasks.getTask(index));
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number." +
                    "\nUsage: unmark <task number>");
        }
    }

    /**
     * Handles the 'delete' command to remove a task from the list.
     *
     * @param args  The command arguments (expecting the task index).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the index is invalid or missing.
     */
    public static void handleDelete(String args, TaskList tasks, Ui ui) throws ListoException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number.");
            }
            Task t = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showTaskDeleted(t, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number." +
                    "\nUsage: delete <task number>");
        }
    }

    /**
     * Handles the 'todo' command to add a new Todo task.
     *
     * @param args  The command arguments (task description).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the description is empty.
     */
    public static void handleTodo(String args, TaskList tasks, Ui ui) throws ListoException {
        String description = args.trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! You forgot the description of the todo task." +
                    "\nUsage: todo <description>");
        }
        Task t = new Todo(description);
        if (tasks.containsDuplicate(t)) {
            throw new ListoException("OOPS!!! This todo task already exists in your list.");
        }

        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }

    /**
     * Handles the 'deadline' command to add a new Deadline task.
     *
     * @param args  The command arguments (description and /by date).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the format is invalid or the date is missing.
     */
    public static void handleDeadline(String args, TaskList tasks, Ui ui) throws ListoException {
        if (args.isEmpty() || !args.contains("/by")) {
            throw new ListoException("OOPS!!! You forgot the description or due date of the deadline task." +
                    "\nUsage: deadline <description> /by <date>");
        }
        String[] parts = args.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! The description cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new ListoException("OOPS!!! The due date cannot be empty.");
        }

        try {
            Task t = new Deadline(description, by);

            if (tasks.containsDuplicate(t)) {
                throw new ListoException("OOPS!!! This deadline task already exists in your list.");
            }

            tasks.addTask(t);
            ui.showTaskAdded(t, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new ListoException("OOPS!!! Invalid date format. Use d/M/yyyy HHmm.");
        }
    }

    /**
     * Handles the 'event' command to add a new Event task.
     *
     * @param args  The command arguments (description, /from start, /to end).
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the format is invalid or times are missing.
     */
    public static void handleEvent(String args, TaskList tasks, Ui ui) throws ListoException {
        if (args.isEmpty() || !args.contains("/from") || !args.contains("/to")) {
            throw new ListoException("OOPS!!! You forgot the description, start and end of the event task." +
                    "\nUsage: event <desc> /from <start> /to <end>");
        }

        String[] parts = args.split("/from", 2);
        String description = parts[0].trim();

        if (parts.length < 2 || !parts[1].contains("/to")) {
            throw new ListoException("OOPS!!! You forgot the end of the event task." +
                    "\nUsage: event <desc> /from <start> /to <end>");
        }

        String[] timeParts = parts[1].split("/to", 2);
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! The description cannot be empty.");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate startDate = LocalDate.parse(from, formatter);
            LocalDate endDate = LocalDate.parse(to, formatter);

            if (endDate.isBefore(startDate)) {
                throw new ListoException("OOPS!!! 🙈 The end date cannot be before the start date. " +
                        "Time travel isn't allowed yet! ⏳");
            }
        } catch (DateTimeParseException e) {
        }

        Task t = new Event(description, from, to);

        if (tasks.containsDuplicate(t)) {
            throw new ListoException("OOPS!!! This event task already exists in your list.");
        }

        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }

    /**
     * Handles the 'filter' command to show tasks on a specific date.
     *
     * @param args  The date string to filter by.
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the date format is invalid.
     */
    public static void handleFilter(String args, TaskList tasks, Ui ui) throws ListoException {
        if (args.trim().isEmpty()) {
            throw new ListoException("OOPS!!! Please specify a date." +
                    "\nUsage: filter <date (d/M/yyyy)>");
        }
        try {
            LocalDate date = LocalDate.parse(args.trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));

            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (int i = 0; i < tasks.getSize(); i++) {
                Task t = tasks.getTask(i);
                if (t.isOccurringOn(date)) {
                    matchingTasks.add(t);
                }
            }

            ui.showTasksOnDate(matchingTasks, args.trim());
        } catch (DateTimeParseException e) {
            throw new ListoException("OOPS!!! Invalid date format. Please use d/M/yyyy.");
        }
    }

    /**
     * Handles the 'find' command to search for tasks by keyword.
     *
     * @param args  The keyword to search for.
     * @param tasks The list of tasks.
     * @param ui    The UI to display the result.
     * @throws ListoException If the search keyword is empty.
     */
    public static void handleFind(String args, TaskList tasks, Ui ui) throws ListoException {
        String keyword = args.trim();
        if (keyword.isEmpty()) {
            throw new ListoException("OOPS!!! What do you want to search for?" +
                    "\nUsage: find <task description>");
        }
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}