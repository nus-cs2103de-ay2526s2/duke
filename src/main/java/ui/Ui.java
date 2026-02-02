package ui;
import java.util.Scanner;
import model.TaskList;
import model.Task;
/**
 * Interacts with user by printing
 */
public class Ui {

    public static final String CHATBOT_NAME = "Star";
    public static final String BOT_LABEL = String.format("[%s]", CHATBOT_NAME);
    public static final String INDENTATION = "       ";
    private static final Scanner sc = new Scanner(System.in);

    // Prints welcome message.
    public static void printWelcomeMessage() {

        String WELCOME_MESSAGE = BOT_LABEL + " Hello! I'm "
                + CHATBOT_NAME + "\nWhat can I do for you?\n";
        System.out.println(WELCOME_MESSAGE);
    }

    // Scans for user input.
    public static String scanInput() {
        return sc.nextLine();
    }

    public static boolean hasMoreInput() {
        return sc.hasNextLine();
    }

    // Prints goodbye message.
    public static void printExitMessage(){
        System.out.printf("\n%s Bye. Hope to see you again soon!",BOT_LABEL);
    }

    // Prints all task
    public static void printTasks() {
        System.out.println("\n" + BOT_LABEL + " Tasklist:");
        for (int i = 0; i < TaskList.getSize(); i++) {
            Task task = TaskList.getTask(i);
            System.out.printf("%s%d.%s\n",INDENTATION, i + 1, task.toString());
        }
    }

    public static void printString(String msg) {
        System.out.println(INDENTATION + msg);
    }

    public static void printErrorMsg(String errorMsg) {
        System.out.println("\n" + BOT_LABEL + " ERROR! " + errorMsg);
    }

    public static void printNewTask(Task task) {
        System.out.printf("\n%s I have added a new task: %s\n", BOT_LABEL, task.name);
    }

    public static void printDeleteTask(Task task) {
        System.out.printf("\n%s I have removed this task: %s\n", BOT_LABEL, task.name);
    }

    public static void showMarkSuccess(Task task) {
        System.out.printf("\n%s Nice! I've marked this task as done:",BOT_LABEL);
        System.out.println(task.toString());
    }

    public static void showUnmarkSuccess(Task task) {
        System.out.printf("\n%s OK, I've marked this task as not done yet:",BOT_LABEL);
        System.out.println(task.toString());
    }
}