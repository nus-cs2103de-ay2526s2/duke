package parser;

import java.util.Arrays;

import exception.CustomException;
import model.Priority;

/**
 * Parses user commands into tokens
 */
public class Message {
    public final String[] tokens;
    public final int tokenLength;
    private final String message;

    /**
     * constructs with String input message
     * @param message user input
     */
    public Message(String message) {
        this.message = message;
        this.tokens = this.parseMessage();
        this.tokenLength = tokens.length;
    }

    /**
     * Parses command and extracts task index (0-based)
     * @return tokens of parsed message
     */
    public String[] parseMessage() {
        String trimmedMessage = this.message.trim().toLowerCase();
        return trimmedMessage.split(" ");
    }

    /**
    * get TaskIndex from TaskList for mark and unmark
    * @return Task index
    */
    public int parseTaskIndex() {
        if (tokens.length <= 1) {
            return -1;
        }
        try {
            return Integer.parseInt(tokens[1].trim()) - 1; // 1-based → 0-based
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * get TaskName
     * Name ends before any of the key (/p /by /st)
     * if empty -> throws exception
     * @return Task name
     */
    public String parseTaskName() {
        int lastTaskNameIndex = tokenLength;

        // taskName stops upon seeing /by or /st
        for (int i = 1; i < tokenLength; i++) {
            if (isKey(tokens[i])) {
                lastTaskNameIndex = i;
                break;
            }
        }
        if (lastTaskNameIndex <= 1) {
            throw new CustomException("Empty task description!");
        }
        return String.join(" ", Arrays.copyOfRange(tokens, 1, lastTaskNameIndex));
    }

    private boolean isKey(String token) {
        return token.equals("/by") || token.equals("/st") || token.equals("/p");
    }

    /**
     * checks for /by and return end date/time as String
     * @return tokens for end date
     */
    public String[] parseBy() {
        for (int i = 0; i < tokenLength - 1; i++) {
            if (tokens[i].equals("/by")) {
                return Arrays.copyOfRange(tokens, i + 1, tokenLength);
            }
        }
        throw new CustomException("/by not found");
    }

    /**
     * Checks for /st and return start date/time as String.
     * @return tokens for start date
     */
    public String[] parseSt() {
        int stIndex = 0;
        int byIndex = tokenLength;
        for (int i = 0; i < tokenLength - 1; i++) {
            if (tokens[i].equals("/st")) {
                stIndex = i + 1;
            }
            if (tokens[i].equals("/by")) {
                byIndex = i;
            }
        }
        if (stIndex == 0) {
            throw new CustomException("/st not found");
        }
        return Arrays.copyOfRange(tokens, stIndex, byIndex);
    }

    /**
     * Looks through tokens for /p.
     * Token after /p is the priority
     * @return priority object
     */
    public Priority parsePriority() {
        for (int i = 0; i < tokenLength - 1; i++) {
            if (tokens[i].equals("/p")) {
                return Priority.fromString(tokens[i + 1]);
            }
        }
        return Priority.MEDIUM; // Default
    }

}