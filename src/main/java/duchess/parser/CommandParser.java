package duchess.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import duchess.command.Command;
import duchess.command.CommandType;
import duchess.command.CreateDeadlineCommand;
import duchess.command.CreateEventCommand;
import duchess.command.CreateToDoCommand;
import duchess.command.DeleteTaskCommand;
import duchess.command.DisplayListCommand;
import duchess.command.DisplayQuoteCommand;
import duchess.command.FindOutstandingCommand;
import duchess.command.FindTaskCommand;
import duchess.command.HelpCommand;
import duchess.command.MarkTaskCompleteCommand;
import duchess.command.MarkTaskIncompleteCommand;
import duchess.command.TerminateCommand;
import duchess.command.UnknownCommand;

/**
 * CommandParser class for parsing user input into commands.
 */
public class CommandParser {

    /**
     * Returns a command from the specified input string
     *
     * @param input the user input string
     * @return a command containing its respective arguments
     */
    public static Command getCommand(String input) {
        String[] splitInput = Utility.splitIntoPair(input, " ");
        assert splitInput.length == 2;

        CommandType commandType = CommandType.getCommandType(splitInput[0].toLowerCase());

        Command command = null;
        Map<String, String> arguments;

        switch (commandType) {
        case BYE:
            command = new TerminateCommand();
            break;
        case LIST:
            command = new DisplayListCommand();
            break;
        case MARK:
            arguments = parseArguments(MarkTaskCompleteCommand.DELIMITERS, splitInput[1]);
            command = new MarkTaskCompleteCommand(arguments);
            break;
        case UNMARK:
            arguments = parseArguments(MarkTaskIncompleteCommand.DELIMITERS, splitInput[1]);
            command = new MarkTaskIncompleteCommand(arguments);
            break;
        case DELETE:
            arguments = parseArguments(DeleteTaskCommand.DELIMITERS, splitInput[1]);
            command = new DeleteTaskCommand(arguments);
            break;
        case DEADLINE:
            arguments = parseArguments(CreateDeadlineCommand.DELIMITERS, splitInput[1]);
            command = new CreateDeadlineCommand(arguments);
            break;
        case EVENT:
            arguments = parseArguments(CreateEventCommand.DELIMITERS, splitInput[1]);
            command = new CreateEventCommand(arguments);
            break;
        case TODO:
            arguments = parseArguments(CreateToDoCommand.DELIMITERS, splitInput[1]);
            command = new CreateToDoCommand(arguments);
            break;
        case OUTSTANDING:
            arguments = parseArguments(FindOutstandingCommand.DELIMITERS, splitInput[1]);
            command = new FindOutstandingCommand(arguments);
            break;
        case FIND:
            arguments = parseArguments(FindTaskCommand.DELIMITERS, splitInput[1]);
            command = new FindTaskCommand(arguments);
            break;
        case CHEER:
            command = new DisplayQuoteCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case UNKNOWN:
            command = new UnknownCommand();
            break;
        default:
            break;
        }
        return command;
    }

    /**
     * Returns the specified input as a Map with specific delimiter-argument pairs, based on the provided delimiters.
     *
     * <p>
     * Input can have delimiters that are out of order. If multiple delimiters of the same type are in the input, the
     * latest argument for that delimiter will be captured.
     * </p>
     *
     * @param delimiters the delimiters the command expects
     * @param userInput the user input string without the command type
     * @return a map containing delimiter-argument pairs
     */
    private static Map<String, String> parseArguments(Set<String> delimiters, String userInput) {
        String[] argumentComponents = userInput.split(" ");

        Map<String, String> argumentsMap = new HashMap<>();
        StringBuilder currentArgument = new StringBuilder();

        String currentDelimiter = "/default";

        for (String argument : argumentComponents) {
            if (delimiters.contains(argument)) {
                argumentsMap.put(currentDelimiter, currentArgument.toString().strip().trim());
                currentDelimiter = argument;
                currentArgument = new StringBuilder();
            } else {
                currentArgument.append(argument).append(" ");
            }
        }

        argumentsMap.put(currentDelimiter, currentArgument.toString().strip().trim());

        return argumentsMap;
    }
}
