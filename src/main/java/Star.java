package seedu.star;

import command.Command;
import exception.CustomException;
import javafx.application.Platform;
import logic.LogicController;
import parser.Message;
import ui.Ui;

/**
 * Main file that scans for input from UI
 */
public class Star {
    private String commandType;

    /**
     * Entry point for the Star chatbot.
     * Runs the CLI loop until the user exits.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage(); // Prints welcome message in UI
        readUserInputLoop();
    }

    private static void readUserInputLoop() {
        while (true) { // Infinite loop
            if (!Ui.hasMoreInput()) {
                break; // EOF → exit
            }

            // Ignores empty input
            String input = Ui.scanInput();
            if (input.isEmpty() || input.trim().isEmpty()) {
                continue;
            }

            String response = handleSingleInput(input);
            System.out.print(response);

            if (isExitCommand(response)) {
                break;
            }
        }
    }

    private static String handleSingleInput(String input) {
        try {
            Message message = new Message(input);
            message.parseMessage();
            Command cmd = LogicController.createCommand(message);
            return cmd.execute();
        } catch (CustomException e) {
            return "Error: " + e.getMessage();
        }
    }

    private static boolean isExitCommand(String response) {
        return response.contains("Bye");
    }

    /**
     * Generates a response for the user's chat message.
     * Creates command based on user input
     * @param input String from user
     * @return response from executing command
     */
    public String getResponse(String input) {
        String response = handleSingleInput(input);
        if (isExitCommand(response)) {
            Platform.exit();
        }
        return response;
    }
}