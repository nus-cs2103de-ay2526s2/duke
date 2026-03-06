package chatterbox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtility {

    private static final DateTimeFormatter DATE_ONLY_INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter DATE_TIME_INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Attempts to parse a date or date-time string input by the user
     *
     * @param input the raw user input
     * @return LocalDateTime
     */
    public static LocalDateTime tryParseUserDateTime(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        String trimmed = input.trim();

        try {
            return LocalDateTime.parse(trimmed, DATE_TIME_INPUT);
        } catch (DateTimeParseException ignored) {}

        try {
            LocalDate date = LocalDate.parse(trimmed, DATE_ONLY_INPUT);
            return date.atStartOfDay(); // 00:00
        } catch (DateTimeParseException ignored) {}

        // This is for parsing ISO-8601 styled date-time, learnt from copilot
        try {
            return LocalDateTime.parse(trimmed);
        } catch (DateTimeParseException ignored) {}

        return null;
    }

    /**
     * Format a LocalDateTime for display
     *
     * @param dateTime the date-time to format
     * @return formatted string (e.g. "Dec 02 2019")
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DISPLAY_FORMAT);
    }
}