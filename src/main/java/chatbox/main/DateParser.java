package chatbox.main;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    private static final List<String> DATE_TIME_FORMATS = Arrays.asList(
            "d/M/yyyy HHmm",
            "yyyy-MM-dd HHmm",
            "dd-MM-yyyy HHmm"  // Multiple formats
    );
    /**
     * Parse a string into a LocalDateTime object.
     * @param dateString The string entered by the user .
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateString) {
        for (String format : DATE_TIME_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next format if this one fails
            }
        }
        return null;

    }
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }
}

