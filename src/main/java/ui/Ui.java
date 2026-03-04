package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Task;
/**
 * Interacts with user by printing.
 */
public class Ui {

    public static final String CHATBOT_NAME = "Star";
    public static final String BOT_LABEL = String.format("[%s]", CHATBOT_NAME);
    public static final String INDENTATION = "       ";
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message.
     */
    public static void printWelcomeMessage() {

        String WELCOME_MESSAGE = BOT_LABEL + " Hello! I'm "
                + CHATBOT_NAME + "\nWhat can I do for you?\n";
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Scans for user input.
      */
    public static String scanInput() {
        return sc.nextLine();
    }

    /**
     * accounts for multiple line inputs
     * runs line by line
     * @return nextline String
     */
    public static boolean hasMoreInput() {
        return sc.hasNextLine();
    }

    /**
     * @return exit message, String
     */
    public static String getExitMessage() {
        return String.format("\n%s Bye, hope to see you again soon!", BOT_LABEL);
    }

    /**
     * @return output String that shows status of all existing task
     */

    public static String taskstoString(String msg, List<Task> tasks) {
        return msg + tasks.stream()
                .map(task -> String.format("%d. %s\n", tasks.indexOf(task) + 1, task))
                .collect(Collectors.joining());
    }

    /**
     * Prints any string.
     */
    public static String getMessageWithIndentation(String msg) {
        return INDENTATION + msg + "\n";
    }


    /**
     * Print strings starting with bot label
     * @param msg String to print out
     */
    public static String getMessageWithBotName(String msg) {
        return BOT_LABEL + msg;
    }
}