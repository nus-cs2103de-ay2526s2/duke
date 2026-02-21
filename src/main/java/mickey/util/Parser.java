package mickey.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for parsing user input and extracting information needed
 */
public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Extracts the first word of input as the command
     *
     * @param chatEntry the user input
     * @return the command
     */
    public static String getCommand(String chatEntry) {
        assert chatEntry != null : "Input should not be null";
        assert !chatEntry.trim().isEmpty() : "Input does not contain a command";
        String[] firstWord = chatEntry.trim().split("\\s+");
        return firstWord[0];

    }

    /**
     * Retreived the index in the input
     *
     * @param chatEntry the user input
     * @return the index of /by in the input
     */
    public static int getByIndex(String chatEntry) {
        int byIndex = chatEntry.indexOf("/by");
        return byIndex;
    }

    /**
     * Retreived the index in the input
     *
     * @param chatEntry the user input
     * @return the index /from in the input
     */
    public static int getFromIndex(String chatEntry) {
        int fromIndex = chatEntry.indexOf("/from");
        return fromIndex;
    }

    /**
     * Retreived the index in the input
     *
     * @param chatEntry the user input
     * @return the index of /to in the input
     */
    public static int getToIndex(String chatEntry) {
        int toIndex = chatEntry.indexOf("/to");
        return toIndex;
    }

    /**
     * Extracts the task number from the input
     *
     * @param chatEntry the user input
     * @return the task number
     */
    public static int getCompletedTask(String chatEntry) {
        String[] parts = chatEntry.trim().split("\\s+");
        //add error handling when just entering "mark"/ "unmark"
        if (parts.length < 2) {
            throw new NumberFormatException("No task number provided");
        }
        int completedTask = Integer.parseInt(parts[1]);
        assert completedTask > 0 : "Task number should be positive";
        return completedTask;
    }


    /**
     * Retreived the index in the input
     *
     * @param chatEntry user input
     * @return the task number
     */
    public static int getDeletedTask(String chatEntry) {
        String[] parts = chatEntry.trim().split("\\s+");
        // Add same error handling as above
        if (parts.length < 2) {
            throw new NumberFormatException("No task number provided");
        }
        int deletedTask = Integer.parseInt(parts[1]);
        return deletedTask;
    }

    /**
     * Retreived the index in the input
     *
     * @param chatEntry user input
     * @return the description of todo task
     */
    public static String getTodoDescription(String chatEntry) {
        String description = chatEntry.substring(5);
        return description;
    }

    /**
     * Retreived the keyword
     *
     * @param chatEntry user input
     * @return the keyword
     */
    public static String getKeywordToSearch(String chatEntry) {
        return chatEntry.substring(5).trim();
    }

    /**
     * Extracts the description and deadline date from input
     *
     * @param chatEntry user input
     * @return the description and deadline date
     */
    public static Object[] getDeadline(String chatEntry) {
        int byIndex = chatEntry.indexOf("/by");
        String description = chatEntry.substring(9, byIndex).trim();
        String dateBy = chatEntry.substring(byIndex + 4).trim();
        LocalDate date = LocalDate.parse(dateBy, DATE_FORMAT);
        return new Object[]{description, date};
    }

    /**
     * Extracts the description and event date range from the input
     *
     * @param chatEntry user input
     * @return the description and  date range
     */
    public static Object[] getEvent(String chatEntry) {
        int fromIndex = chatEntry.indexOf("/from");
        int toIndex = chatEntry.indexOf("/to");
        String description = chatEntry.substring(6, fromIndex).trim();
        String fromDate = chatEntry.substring(fromIndex + 6, toIndex).trim();
        String toDate = chatEntry.substring(toIndex + 4).trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateFrom = LocalDateTime.parse(fromDate, dateFormat);
        LocalDateTime dateTo = LocalDateTime.parse(toDate, dateFormat);
        return new Object[]{description, dateFrom, dateTo};
    }

    /**
     * Extract date from command
     *
     * @param chatEntry user input
     * @return LocalDate object in format needed
     * @throws DateTimeParseException if date is not correct format
     */
    public static LocalDate getDateInFormat(String chatEntry) {
        String dateString = chatEntry.substring(4).trim();
        return LocalDate.parse(dateString, DATE_FORMAT);
    }
}
