package chatterbox.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final boolean exit;
    private final boolean error;

    /**
     * Creates a CommandResult with feedback and no exit.
     *
     * @param feedbackToUser Message to display.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Creates a CommandResult with feedback and exit flag.
     *
     * @param feedbackToUser Message to display.
     * @param exit           True if application should exit.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this(feedbackToUser, exit, false);
    }

    /**
     * Creates a CommandResult with feedback, exit flag, and error flag.
     *
     * @param feedbackToUser Message to display.
     * @param exit           True if application should exit.
     * @param error          True if this result represents an error.
     */
    public CommandResult(String feedbackToUser, boolean exit, boolean error) {
        this.feedbackToUser = feedbackToUser;
        this.exit = exit;
        this.error = error;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isError() {
        return error;
    }
}