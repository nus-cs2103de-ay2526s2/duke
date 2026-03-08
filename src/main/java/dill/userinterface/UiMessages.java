package dill.userinterface;

import dill.task.Task;

/**
 * Represents the collection of user interface messages used by Dill.
 */
public class UiMessages {
    private static String username;

    private static final String TASKS_LOAD_SUCCESS = "I found %d saved tasks. Let's pick up where we left off!";

    private static final String TASKS_LOAD_SUCCESS_EMPTY = "I could not find any saved tasks. Let's start fresh!";

    private static final String TASKS_LOAD_CORRUPT = "Oh no!"
            + " My memory seems to be corrupted. I'm unable to load our previous tasks, let's start fresh shall we?";

    private static final String TASKS_LOAD_ERROR = "Uh oh. I'm having some troubles with my memory."
            + " Don't worry! I can still help you but I won't be able to save our progress this time.";

    private static final String TASKS_SAVE_ERROR = "Oops! I encountered an issue while saving your tasks."
            + " I'll pause saving for the rest of this session while I work on a fix.";

    private static final String TASK_ID_NOT_FOUND = "Hold up! I could not find a task with that id on our list!";

    private static final String MESSAGE_EXIT = "Bye, %s! Hope to see you again soon!";

    private static final String MESSAGE_SET_NAME = "Alright, %s! What shall we do this time?";

    private static final String GREETING =
            """
            Hello there! I'm Dill.
            %s
            But first, how should I address you?""";

    private static final String TASK_ADD_SUCCESS =
            """
            Logged it! Here's the new task:
              %s
            That makes %d items on our list.""";

    private static final String TASK_DELETE_SUCCESS =
            """
            Poof! Task deleted:
              %s
            We are down to %d tasks now.""";

    private static final String TASK_MARK_SUCCESS =
            """
            Hooray! Great work. I've marked this as done:
              %s""";

    private static final String TASK_UNMARK_SUCCESS =
            """
            Whoops, hold on a minute! I've marked this as NOT done yet:
              %s""";

    private static final String TASK_UPDATE_SUCCESS =
            """
            Update complete! I have tweaked the task:
              From: %s
              To    : %s""";

    private static final String TASK_CLONE_SUCCESS =
            """
            Ctrl+C, Ctrl+V! Task successfully cloned:
              %s
            We now have %d tasks on the list.""";

    private static final String MESSAGE_HELP =
            """
            These are the commands I understand:

            Managing Tasks:
            1. list
                Displays the current tasks in the list.
            2. todo <task-name>
                Adds a to-do task to the list.
            3. deadline <task-name> /by <yyyy-mm-dd>
                Adds a deadline task to your list.
            4. event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>
                Adds an event task to the list.
            5. mark <task-id>
                Marks the task with specified task id as complete.
            6. unmark <task-id>
                Marks the task with specified task id as incomplete.
            7. delete <task-id>
                Removes the task with specified task id from the list.
            8. update <task-id> <flag> <value> ...
                Modifies one or more fields of the task with specified task id.
                Supported flags: /name, /by, /start, /end
                e.g., update 3 /name myTask /end 2026-03-14
            9. clone <task-id>
                Duplicates the task with the specified task id.

            Quick Tools:
            1. find <keyword>
                Displays all tasks matching the specified keyword.
            2. view <yyyy-mm-dd>
                Displays all tasks occurring on the specified date.
            3. cheer
                Displays a random motivational message.
            4. bye
                Terminates the current session.""";

    /**
     * Returns the formatted greeting message displayed at startup.
     *
     * @param loadMessage The message describing the startup load status.
     * @return The formatted greeting message.
     */
    public static String getGreeting(String loadMessage) {
        return String.format(GREETING, loadMessage);
    }

    /**
     * Returns the exit message displayed when the user terminates the session.
     *
     * @return The exit message.
     */
    public static String getExit() {
        return String.format(MESSAGE_EXIT, username);
    }

    /**
     * Returns the message displayed after successfully loading tasks.
     *
     * @param n The number of tasks successfully loaded.
     * @return The load success message.
     */
    public static String getLoadTasksSuccess(int n) {
        if (n > 0) {
            return String.format(TASKS_LOAD_SUCCESS, n);
        } else {
            return TASKS_LOAD_SUCCESS_EMPTY;
        }
    }

    /**
     * Returns the message displayed when an error occurs during loading.
     *
     * @return The load error message.
     */
    public static String getLoadTasksError() {
        return TASKS_LOAD_ERROR;
    }

    /**
     * Returns the help message listing all available commands.
     *
     * @return The help message.
     */
    public static String getHelp() {
        return MESSAGE_HELP;
    }

    /**
     * Returns the message displayed after successfully adding a task.
     *
     * @param task The task that was added.
     * @param listSize The updated number of tasks in the list.
     * @return The formatted add-success message.
     */
    public static String getAddSuccess(Task task, int listSize) {
        return String.format(TASK_ADD_SUCCESS, task, listSize);
    }

    /**
     * Returns the message displayed after successfully deleting a task.
     *
     * @param task The task that was deleted.
     * @param listSize The updated number of tasks in the list.
     * @return The formatted delete-success message.
     */
    public static String getDeleteSuccess(Task task, int listSize) {
        return String.format(TASK_DELETE_SUCCESS, task, listSize);
    }

    /**
     * Returns the message displayed after successfully marking a task as done.
     *
     * @param task The task that was marked as complete.
     * @return The formatted mark-success message.
     */
    public static String getMarkSuccess(Task task) {
        return String.format(TASK_MARK_SUCCESS, task);
    }

    /**
     * Returns the message displayed after successfully marking a task as not done.
     *
     * @param task The task that was marked as incomplete.
     * @return The formatted unmark-success message.
     */
    public static String getUnmarkSuccess(Task task) {
        return String.format(TASK_UNMARK_SUCCESS, task);
    }

    /**
     * Returns the message displayed when the task storage file is corrupted.
     *
     * @return The corruption error message.
     */
    public static String getTasksLoadCorrupt() {
        return TASKS_LOAD_CORRUPT;
    }

    /**
     * Returns the message displayed when an error occurs while saving tasks.
     *
     * @return The save error message.
     */
    public static String getTasksSaveError() {
        return TASKS_SAVE_ERROR;
    }

    /**
     * Returns the message displayed after successfully updating a task.
     *
     * @param taskBefore The string representation of the task before the update.
     * @param taskAfter The updated task.
     * @return The formatted update-success message.
     */
    public static String getTaskUpdateSuccess(String taskBefore, Task taskAfter) {
        return String.format(TASK_UPDATE_SUCCESS, taskBefore, taskAfter);
    }

    /**
     * Returns the message displayed after successfully cloning a task.
     *
     * @param clonedTask The duplicated task.
     * @param listSize The updated number of tasks in the list.
     * @return The formatted clone-success message.
     */
    public static String getCloneSuccess(Task clonedTask, int listSize) {
        return String.format(TASK_CLONE_SUCCESS, clonedTask, listSize);
    }

    /**
     * Returns the message displayed if task id is out of bounds.
     *
     * @return The task id not found message.
     */
    public static String getTaskIdNotFound() {
        return TASK_ID_NOT_FOUND;
    }

    /**
     * Returns the message displayed after setting the username.
     *
     * @param name The username to be set.
     * @return The username set message.
     */
    public static String setUsername(String name) {
        username = name;
        return String.format(MESSAGE_SET_NAME, username);
    }
}
