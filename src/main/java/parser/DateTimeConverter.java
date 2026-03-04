package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.CustomException;

/**
 * Converts datetime from String to LocalDate yyyy-MM-dd HH:mm
 */
public class DateTimeConverter {
    /**
     * Convert date time tokens into LocalDateTime
     *
     * @param dtStrings tokens of string
     * @return LocalDateTime
     * @throw CustomException
     */
    public static LocalDateTime toLocalDate(String[] dtStrings) throws CustomException {
        assert dtStrings.length != 0 : "dtStrings should not be empty";
        assert dtStrings.length < 3 : "dtStrings should not have more than 2 tokens";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "[yyyy-MM-dd HH:mm]"
                            + "[yyyy-MM-dd]"
                            + "[dd/MM/yy HH:mm]"
                            + "[dd/MM/yy]"
                    );

            // dtStrings only contain date
            if (dtStrings.length == 1) {
                String tdString = dtStrings[0] + " 23:59";
                return LocalDateTime.parse(tdString, formatter);
            }

            // dtStrings contain date and time
            String tdString = String.join(" ", dtStrings);
            return LocalDateTime.parse(tdString, formatter);

        } catch (DateTimeParseException e) {
            throw new CustomException("Invalid date time format! Input dd/MM/yy HH:mm");
        }
    }
}