package duchess.command;

import duchess.parser.Utility;

/**
 * Enum representing the different types of commands.
 */
public enum CommandType {
    BYE("bye"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    TODO("todo"),
    UNMARK("unmark"),
    UNKNOWN(""),
    DELETE("delete"),
    OUTSTANDING("outstanding"),
    FIND("find"),
    CHEER("cheer"),
    HELP("help");

    private final String commandString;

    /**
     * Constructor for CommandType enum.
     *
     * @param commandString the string representation of the command
     */
    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the command type of the input.
     *
     * @param input the input string
     * @return the command type of the input
     */
    public static CommandType getCommandType(String input) {
        if (Utility.isInvalidString(input)) {
            return UNKNOWN;
        }

        for (CommandType type : CommandType.values()) {
            if (type.commandString.startsWith(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
