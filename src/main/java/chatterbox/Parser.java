package chatterbox;

import chatterbox.command.AddDeadlineCommand;
import chatterbox.command.AddEventCommand;
import chatterbox.command.AddTodoCommand;
import chatterbox.command.CheerCommand;
import chatterbox.command.Command;
import chatterbox.command.DeleteCommand;
import chatterbox.command.FindCommand;
import chatterbox.command.HelpCommand;
import chatterbox.command.ListCommand;
import chatterbox.command.MarkCommand;
import chatterbox.command.UnmarkCommand;
import chatterbox.command.ByeCommand;

/**
 * Parses user input into executable Command objects.
 * Handles all migrated commands (non-mutating, add, and index-mutating).
 */
public class Parser {

    /**
     * Parses the user's input into a Command.
     *
     * @param input Raw user input.
     * @return A Command representing the user's request.
     * @throws ChatterBotException If the input is invalid.
     */
    public static Command parse(String input) throws ChatterBotException {
        assert input != null : "Parser input cannot be null";
        String trimmed = input.trim();


        // -----------------------------
        // GoodBye command
        // -----------------------------
        if (trimmed.equals("bye")) {
            return new ByeCommand();
        }

        // -----------------------------
        // Non-mutating commands
        // -----------------------------
        if (trimmed.equals("list")) {
            return new ListCommand();
        }

        if (trimmed.equals("cheer")) {
            return new CheerCommand();
        }

        if (trimmed.equals("help")) {
            return new HelpCommand();
        }

        if (trimmed.equals("find")) {
            throw new ChatterBotException(Errors.FIND_EMPTY);
        }

        if (trimmed.startsWith("find ")) {
            String keyword = trimmed.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new ChatterBotException(Errors.FIND_EMPTY);
            }
            return new FindCommand(keyword);
        }

        // -----------------------------
        // Add commands
        // -----------------------------

        if (trimmed.equals("todo")) {
            throw new ChatterBotException(Errors.TODO_EMPTY);
        }
        if (trimmed.startsWith("todo ")) {
            String desc = trimmed.substring(5).trim();
            if (desc.isEmpty()) {
                throw new ChatterBotException(Errors.TODO_EMPTY);
            }
            return new AddTodoCommand(desc);
        }

        if (trimmed.equals("deadline")) {
            throw new ChatterBotException(Errors.DEADLINE_EMPTY);
        }
        if (trimmed.startsWith("deadline ")) {
            int byPos = trimmed.indexOf(" /by ");
            if (byPos == -1) {
                throw new ChatterBotException(Errors.DEADLINE_FORMAT);
            }

            String desc = trimmed.substring(9, byPos).trim();
            String by = trimmed.substring(byPos + 5).trim();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new ChatterBotException(Errors.DEADLINE_FORMAT);
            }

            if (DateTimeUtility.tryParseUserDateTime(by) == null) {
                throw new ChatterBotException(Errors.INVALID_DATE_FORMAT);
            }

            return new AddDeadlineCommand(desc, by);
        }

        if (trimmed.equals("event")) {
            throw new ChatterBotException(Errors.EVENT_EMPTY);
        }
        if (trimmed.startsWith("event ")) {
            int fromPos = trimmed.indexOf(" /from ");
            int toPos = trimmed.indexOf(" /to ");
            if (fromPos == -1 || toPos == -1 || toPos <= fromPos) {
                throw new ChatterBotException(Errors.EVENT_FORMAT);
            }

            String desc = trimmed.substring(6, fromPos).trim();
            String from = trimmed.substring(fromPos + 7, toPos).trim();
            String to = trimmed.substring(toPos + 5).trim();

            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new ChatterBotException(Errors.EVENT_FORMAT);
            }

            if (DateTimeUtility.tryParseUserDateTime(from) == null
                    || DateTimeUtility.tryParseUserDateTime(to) == null) {
                throw new ChatterBotException(Errors.INVALID_DATE_FORMAT);
            }

            return new AddEventCommand(desc, from, to);
        }

        // -----------------------------
        // Index-mutating commands
        // -----------------------------

        if (trimmed.equals("mark")) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
        if (trimmed.startsWith("mark ")) {
            int index = parseRequiredIndex(trimmed, "mark ");
            return new MarkCommand(index);
        }

        if (trimmed.equals("unmark")) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
        if (trimmed.startsWith("unmark ")) {
            int index = parseRequiredIndex(trimmed, "unmark ");
            return new UnmarkCommand(index);
        }

        if (trimmed.equals("delete")) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
        if (trimmed.startsWith("delete ")) {
            int index = parseRequiredIndex(trimmed, "delete ");
            return new DeleteCommand(index);
        }

        throw new ChatterBotException(Errors.UNKNOWN);
    }

    /**
     * Parses a required integer index from the input.
     *
     * @param trimmed Full trimmed input.
     * @param prefix  Command prefix (e.g., "mark ").
     * @return Parsed 1-based index.
     * @throws ChatterBotException If the index is invalid.
     */
    private static int parseRequiredIndex(String trimmed, String prefix)
            throws ChatterBotException {

        assert trimmed != null : "Trimmed input cannot be null";
        assert prefix != null : "Prefix cannot be null";
        assert trimmed.startsWith(prefix) : "Trimmed input must start with matching prefix";

        String numPart = trimmed.substring(prefix.length()).trim();
        if (numPart.isEmpty()) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }

        try {
            int index = Integer.parseInt(numPart);
            if (index <= 0) {
                throw new ChatterBotException(Errors.INVALID_INDEX);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new ChatterBotException(Errors.INVALID_INDEX);
        }
    }
}