package parser;

import exception.CustomException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;


public class DateTimeConverterTest {
    /**
     * Tests DateTimeConverterTest by giving invalid Month
     */
    @Test
    public void toLocalDate_invalidDate_throwsError() {
        CustomException ex = assertThrows(
            CustomException.class,
            () -> DateTimeConverter.toLocalDate(new String[]{"2026-47-30"})
        );
        assertEquals("Invalid date time format! Input dd/MM/yy HH:mm", ex.getMessage());
    }

    /**
     * Tests DateTimeConverterTest by giving valid date
     */
    @Test
    public void toLocalDate_validDate_string() {
        // Input "06/01/26" → dd/MM/yy → Jan 6, 2026 → end-of-day (no time provided)
        LocalDateTime expected = LocalDateTime.of(2026, 1, 6, 23, 59);

        LocalDateTime actual = DateTimeConverter.toLocalDate(new String[]{"06/01/26"});

        assertEquals(expected, actual);
    }
}