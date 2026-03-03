package duchess.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

/**
 * Utility class containing generic methods for parsing inputs.
 */
public class Utility {
    /**
     * Returns the specified input as an array of length 2, after splitting with a specified string as the delimiter.
     *
     * @param input the input command string from the user
     * @return a String array of length 2
     */
    public static String[] splitIntoPair(String input, String delimiter) {
        String[] split = input.strip().split(delimiter, 2);

        if (split.length == 1) {
            return new String[]{ split[0], "" };
        }

        split[1] = split[1].strip();
        return split;
    }

    /**
     * Checks if the specified string is null or empty.
     * @param string the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isInvalidString(String string) {
        return string == null || string.isBlank();
    }

    /**
     * Returns an index to a list as an integer after extracting the argument from the delimiter-argument pair.
     *
     * @param indexAsString string containing the index
     * @return an integer denoting the list index
     * @throws MissingArgumentException if the argument is an empty string or null
     * @throws InvalidArgumentException if the argument is not a number or multiple numbers are specified
     */
    public static int parseInt(String indexAsString) throws MissingArgumentException, InvalidArgumentException {
        if (isInvalidString(indexAsString)) {
            throw new MissingArgumentException("No index hath been given!");
        }

        int index;

        try {
            index = Integer.parseInt(indexAsString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Hark, the index given be not a solitary number, methinks!");
        }

        return index;
    }

    /**
     * Parses a date string into a LocalDate object.
     * @param dateAsString the date string to parse
     * @return a LocalDate object representing the date
     * @throws MissingArgumentException if the date string is empty or null
     * @throws InvalidArgumentException if the date string is not in the correct format
     */
    public static LocalDate parseDate(String dateAsString) throws MissingArgumentException, InvalidArgumentException {
        if (isInvalidString(dateAsString)) {
            throw new MissingArgumentException("Hark! No date hath been given!"
                    + "I did expect it in the fashion of YYYY-MM-DD, methinks.");
        }

        LocalDate date;

        try {
            date = LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Hark! A date most foul hath been given!"
                    + "I crave one writ thus: YYYY-MM-DD");
        }

        return date;
    }

    /**
     * Formats a LocalDate object into a specific String format.
     * @param date the LocalDate object to format
     * @return a formatted String representing the date
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEE, dd LLL yyyy"));
    }
}
