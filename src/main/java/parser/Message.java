package parser;

import java.util.Arrays;
import exception.CustomException;

/**
 * Parses user commands into actionable parts.
 */
public class Message {
    final String message;
    public final String[] tokens;
    public final int tokenLength;
    // Constructor
    public Message(String message) {
        this.message = message;
        this.tokens = this.parseMessage();
        this.tokenLength = tokens.length;
    }

    // Parses command and extracts task index (0-based).
    public String[] parseMessage() {
        String trimmedMessage = this.message.trim().toLowerCase();
        return trimmedMessage.split(" ");
    }

    // get TaskIndex from TaskList for mark and unmark
    public int parseTaskIndex() {
        if (tokens.length <= 1) return -1;
        try {
            return Integer.parseInt(tokens[1].trim()) - 1;  // 1-based → 0-based
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // get TaskName
    // if empty -> throws exception
    public String parseTaskName() {
        int lastTaskNameIndex = tokenLength;

        // taskName stops upon seeing /by or /st
        for (int i = 1; i < tokenLength; i++) {
            if (tokens[i].equals("/by") || tokens[i].equals("/st")){
                lastTaskNameIndex = i;
                break;
            }
        }
        if (lastTaskNameIndex <= 1){
            throw new CustomException("Empty task description!");
        }
        return String.join(" ",Arrays.copyOfRange(tokens,1,lastTaskNameIndex));
    }

    public String parseBy() {
        for (int i=0; i < tokenLength - 1 ; i++) {
            if (tokens[i].equals("/by")) {
                return tokens[i+1];
            }
        }
        throw new CustomException("/by not found");
    }

    public String parseSt() {
        for (int i=0; i < tokenLength - 1 ; i++) {
            if (tokens[i].equals("/st")) {
                return tokens[i+1];
            }
        }
        throw new CustomException("/st not found");
    }

}