package duchess.ui;

import java.util.Scanner;

/**
 * Ui class to display messages and read user input.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void displayWelcomeMessage() {
        display("Hark, I be Duchess! What service dost thou require of me?");
    }

    public void displayLoadingErrorMessage() {
        display("Hark! An error hath befallen, the tasks from yon file could not be summoned. Thus, we begin anew...");
    }
}
