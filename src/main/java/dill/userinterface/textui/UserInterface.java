package dill.userinterface.textui;

import java.util.Scanner;

/**
 * Command line interface of the Dill chatbot application.
 * Captures raw user input and outputs formatted messages to the user.
 */
public class UserInterface {
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Creates a new instance of UserInterface and initializes scanner.
     */
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs a formatted message from Dill to the console
     * Wraps the given message with line dividers to demarcate responses from Dill.
     *
     * @param messages The unformatted messages to be displayed.
     */
    public void displayMessage(String... messages) {
        System.out.println(DIVIDER_LINE);
        for (String m : messages) {
            System.out.println(m);
        }
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Reads a line of user input
     *
     * @return The raw input string from the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
