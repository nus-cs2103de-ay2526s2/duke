package chatterbox;

/**
 * Wraps the chatbot's response with metadata for the GUI.
 * Contains the message text and whether it represents an error.
 */
public class BotResponse {

    private final String message;
    private final boolean error;

    /**
     * Creates a BotResponse.
     *
     * @param message The response message to display.
     * @param error   True if this response represents an error.
     */
    public BotResponse(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
