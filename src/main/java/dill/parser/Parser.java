package dill.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dill.command.AddCommand;
import dill.command.CheerCommand;
import dill.command.CloneCommand;
import dill.command.Command;
import dill.command.DeleteCommand;
import dill.command.ExitCommand;
import dill.command.FindCommand;
import dill.command.HelpCommand;
import dill.command.ListCommand;
import dill.command.MarkCommand;
import dill.command.UnmarkCommand;
import dill.command.UpdateCommand;
import dill.command.ViewCommand;
import dill.exception.InvalidCommandException;
import dill.task.Deadline;
import dill.task.Event;
import dill.task.ToDo;
import dill.task.UpdateFields;

/**
 * Represents the user input interpreter of Dill.
 */
public class Parser {
    private static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(.+?)\\s+/by\\s+(.+)");
    private static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(.+?)\\s+/start\\s+(.+?)\\s+/end\\s+(.+)");
    private static final Pattern UPDATE_ARGS_FORMAT = Pattern.compile("(/.+?)\\s+(.+?)(?=\\s+(/.+?)\\s+(.+)|$)");
    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_ARGS = 1;
    private static final int PARSE_MAX_PARTS = 2;
    private static final int UPDATE_MAX_PARTS = 2;
    private static final int UPDATE_INDEX_TASKID = 0;
    private static final int UPDATE_INDEX_ARGS = 1;


    /**
     * Creates an instance of Parser.
     */
    public Parser() {}

    /**
     * Deciphers raw user input and converts them into Commands.
     * Handles syntax validation and parameter extraction.
     *
     * @param userInput The raw string input of the user.
     * @return A specific command that can be executed by Dill.
     * @throws InvalidCommandException If the input is unrecognizable or missing parameters.
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        assert userInput != null : "User input should not be null";
        String[] inputParts = userInput.trim().split("\\s+", PARSE_MAX_PARTS); // Split into command and arguments
        String cmd = inputParts[INDEX_COMMAND];
        String args = inputParts.length == PARSE_MAX_PARTS ? inputParts[INDEX_ARGS] : ""; // Input args could be empty

        switch (cmd) {
        case "bye":
            return validateBye(args);
        case "list":
            return validateList(args);
        case "help":
            return validateHelp(args);
        case "cheer":
            return validateCheer(args);
        case "mark":
            return validateMark(args);
        case "unmark":
            return validateUnmark(args);
        case "delete":
            return validateDelete(args);
        case "todo":
            return validateToDo(args);
        case "deadline":
            return validateDeadline(args);
        case "event":
            return validateEvent(args);
        case "find":
            return validateFind(args);
        case "view":
            return validateView(args);
        case "update":
            return validateUpdate(args);
        case "clone":
            return validateClone(args);
        default:
            throw new InvalidCommandException("Hmmm... that command does not seem to be in my database!\n"
                    + "Type \"help\" to see what I can do.");
        }
    }

    private static Command validateMark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Whoops! You forgot to give me a task id to mark. (e.g., mark 3)");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new MarkCommand(taskIndex);
    }

    private static Command validateUnmark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Whoops! You forgot to give me a task id to unmark. (e.g., unmark 3)");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new UnmarkCommand(taskIndex);
    }

    private static Command validateDelete(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Whoops! You forgot to give me a task id to delete. (e.g., delete 3)");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new DeleteCommand(taskIndex);
    }

    private static Command validateToDo(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException(
                    "Wait, what are we doing? Please tell me a task name! (e.g., todo study)");
        }
        return new AddCommand(new ToDo(args));
    }

    private static Command validateDeadline(String args) throws InvalidCommandException {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new InvalidCommandException("Hold up! The deadline format is a little off."
                    + " Please use: deadline <task-name> /by <yyyy-mm-dd>");
        }
        assert matcher.groupCount() == 2 : "Matcher should have 2 groups";
        String taskName = matcher.group(1);
        String rawDate = matcher.group(2);
        LocalDate date = parseDate(rawDate);
        return new AddCommand(new Deadline(taskName, date));
    }

    private static Command validateEvent(String args) throws InvalidCommandException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new InvalidCommandException("Hold up! The event format is a little off."
                    + " Please use: event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>");
        }
        assert matcher.groupCount() == 3 : "Matcher should have 3 groups";
        String taskName = matcher.group(1);
        String rawStartDate = matcher.group(2);
        String rawEndDate = matcher.group(3);
        LocalDate startDate = parseDate(rawStartDate);
        LocalDate endDate = parseDate(rawEndDate);
        if (startDate.isAfter(endDate)) {
            throw new InvalidCommandException("Wait a second... are we time travelling?"
                    + " The start date cannot be after the end date!");
        }
        return new AddCommand(new Event(taskName, startDate, endDate));
    }

    private static Command validateFind(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("What are we looking for? Please give me a keyword to search!");
        }
        return new FindCommand(args);
    }

    private static Command validateView(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Which date are we looking at? Please give me a date!");
        }
        LocalDate date = parseDate(args);
        return new ViewCommand(date);
    }

    private static Command validateUpdate(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Whoops! Which task are we updating? Please me a task id!");
        }

        String[] updateParts = args.split("\\s+", UPDATE_MAX_PARTS);
        if (updateParts.length == 1) {
            throw new InvalidCommandException("I need a bit more information to update that!"
                    + " Use a flag followed by a value. (e.g., update 3 /by 2026-03-14)");
        }

        int taskIndex = parseTaskId(updateParts[UPDATE_INDEX_TASKID]) - 1;
        UpdateFields updateFields = new UpdateFields();

        String updateArgs = updateParts[UPDATE_INDEX_ARGS];
        Matcher matcher = UPDATE_ARGS_FORMAT.matcher(updateArgs);
        while (matcher.find()) {
            String flag = matcher.group(1);
            String value = matcher.group(2);
            setUpdateFields(updateFields, flag, value);
        }
        if (updateFields.isEmpty()) {
            throw new InvalidCommandException("Looks like you forgot the new value for that field!"
                            + " Please specify it. (e.g., /by 2026-03-14)");
        }
        return new UpdateCommand(taskIndex, updateFields);
    }

    private static Command validateClone(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Which task are we cloning? Please give me a task id!");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new CloneCommand(taskIndex);
    }

    private static Command validateBye(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            throw new InvalidCommandException(
                    "No need for long goodbyes! Just type \"bye\" and you are set.");
        }
        return new ExitCommand();
    }

    private static Command validateList(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            throw new InvalidCommandException(
                    "Oops, I do not quite understand that! Did you mean just \"list\"?");
        }
        return new ListCommand();
    }

    private static Command validateHelp(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            throw new InvalidCommandException(
                    "Oops, I do not quite understand that! Did you mean just \"help\"?");
        }
        return new HelpCommand();
    }

    private static Command validateCheer(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            throw new InvalidCommandException(
                    "Oops, I do not quite understand that! Did you mean just \"cheer\"?");
        }
        return new CheerCommand();
    }

    private static int parseTaskId(String taskId) throws InvalidCommandException {
        try {
            return Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hold on! The task id must be an integer!");
        }
    }

    private static LocalDate parseDate(String date) throws InvalidCommandException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Uh oh, I cannot read that date format! Please use: yyyy-mm-dd.");
        }
    }

    private static void setUpdateFields(
            UpdateFields updateFields, String flag, String value) throws InvalidCommandException {
        switch (flag) {
        case "/name":
            updateFields.setTaskName(value);
            break;
        case "/by":
            updateFields.setByDate(parseDate(value));
            break;
        case "/start":
            updateFields.setStartDate(parseDate(value));
            break;
        case "/end":
            updateFields.setEndDate(parseDate(value));
            break;
        default:
            throw new InvalidCommandException("I cannot recognize that flag!"
                    + " My supported flags are: /name, /by, /start, /end");
        }
    }
}
