package listo.command;

/**
 * Represents the various commands that the user can execute.
 * Used to map user input strings to specific actions.
 */
public enum CommandType {
    /** Command to list all tasks. */
    LIST,
    /** Command to mark a task as done. */
    MARK,
    /** Command to unmark a task as not done. */
    UNMARK,
    /** Command to add a todo task. */
    TODO,
    /** Command to add a deadline task. */
    DEADLINE,
    /** Command to add an event task. */
    EVENT,
    /** Command to delete a task. */
    DELETE,
    /** Command to filter tasks by date. */
    FILTER,
    /** Command to find tasks by keyword. */
    FIND,
    /** Command to display a random cheering message. */
    CHEER,
    /** Command to list all available commands. */
    HELP,
    /** Command to exit the application. */
    BYE,
    /** Represents an unrecognized command. */
    UNKNOWN // Used for invalid commands
}