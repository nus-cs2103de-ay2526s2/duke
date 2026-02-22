package chatbox.main;

/**
 * Represents a custom exception specific to the ZhengjunChatbox.
 * This is used to signal errors related to user input or task operations.
 */
public class ChatBoxException extends Exception {
    public ChatBoxException(String message) {
        super(message);
    }
}