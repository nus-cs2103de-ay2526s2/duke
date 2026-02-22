package chatbox.main;

import chatbox.main.commands.*;
import chatbox.main.tasks.Deadline;
import chatbox.main.tasks.Event;
import chatbox.main.tasks.ToDo;
import chatbox.main.commands.FindCommand;
import chatbox.main.commands.CheerCommand;

/**
 * Parses user input into commands for execution.
 * contains methods to interpret user commands and arguments.
 */
public class Parser {

    public static Command parse(String userInput) throws ChatBoxException {
        assert userInput != null : "userInput should not be null";

        String[] parts = userInput.split(" ", 2);

        assert parts.length > 0 : "Split should result in at least one part";

        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "BYE":
                return new ExitCommand();
            case "LIST":
                return new ListCommand();
            case "DELETE":
                if (parts.length < 2) {
                    throw new ChatBoxException("Please specify which task number to delete.");
                }
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "MARK":
                if (parts.length < 2) {
                    throw new ChatBoxException("Please specify which task number to mark.");
                }
                return new MarkCommand(Integer.parseInt(parts[1]) - 1, true);
            case "UNMARK":
                if (parts.length < 2) {
                    throw new ChatBoxException("Please specify which task number to unmark.");
                }
                return new MarkCommand(Integer.parseInt(parts[1]) - 1, false);
            case "TODO":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBoxException("The description of a todo cannot be empty.");
                }
                return new AddCommand(new ToDo(parts[1]));
            case "DEADLINE":
                if (parts.length < 2 || !parts[1].contains(" /by ")) {
                    throw new ChatBoxException("Deadlines need a description and /by date.");
                }
                String[] dParts = parts[1].split(" /by ");
                assert dParts.length == 2 : "Deadline split failed";
                return new AddCommand(new Deadline(dParts[0], dParts[1]));
            case "EVENT":
                if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                    throw new ChatBoxException("Events need a description, /from, and /to.");
                }
                String[] eParts = parts[1].split(" /from ");
                assert eParts.length == 2 : "Event /from split failed";

                String[] times = eParts[1].split(" /to ");
                assert times.length == 2 : "Event /to split failed";

                return new AddCommand(new Event(eParts[0], times[0], times[1]));
            case "FIND":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBoxException("The keyword for find cannot be empty.");
                }
                return new FindCommand(parts[1].trim());
            case "CHEER":
                return new CheerCommand();
            default:
                throw new ChatBoxException("I'm sorry, but I don't know what that means :-(");
        }
    }
}