package chatterbox;

/**
 * Stores user-facing error messages used throughout the application.
 */
public class Errors {
    public static final String TODO_EMPTY =
            "Hello! The description of a todo cannot be empty. Please try again.";

    public static final String DEADLINE_EMPTY =
            "Hello! The description of a deadline cannot be empty. Please try again.";

    public static final String DEADLINE_FORMAT =
            "Hello! A deadline must have '/by <time>'. Please try again.";

    public static final String EVENT_EMPTY =
            "Hello! The description of an event cannot be empty. Please try again.";

    public static final String EVENT_FORMAT =
            "Hello! An event must have '/from <start> /to <end>'. Please try again.";

    public static final String FIND_EMPTY =
            "Hello! Please provide a keyword to find.";

    public static final String INVALID_INDEX =
            "Hello! Please provide a valid task number.";

    public static final String UNKNOWN =
            "Hello! I'm sorry, but I don't know what that means. Please try again.";

    public static final String INDEX_OUT_OF_RANGE =
            "Hello! That task number does not exist. Please try again.";

    public static final String INVALID_DATE_FORMAT =
            "Hello! Please use yyyy-MM-dd or yyyy-MM-dd HHmm for dates.";
}