package chatterbox;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeUtilityTest {

    @Test
    public void tryParseUserDateTime_dateOnly_success() {
        LocalDateTime dt = DateTimeUtility.tryParseUserDateTime("2019-12-02");
        assertNotNull(dt);
        assertEquals(LocalDateTime.of(2019, 12, 2, 0, 0), dt);
    }

    @Test
    public void tryParseUserDateTime_dateTime_success() {
        LocalDateTime dt = DateTimeUtility.tryParseUserDateTime("2019-12-02 1800");
        assertNotNull(dt);
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt);
    }

    @Test
    public void tryParseUserDateTime_isoDateTime_success() {
        LocalDateTime dt = DateTimeUtility.tryParseUserDateTime("2019-12-02T18:00");
        assertNotNull(dt);
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt);
    }

    @Test
    public void tryParseUserDateTime_invalid_returnsNull() {
        assertNull(DateTimeUtility.tryParseUserDateTime("02-12-2019"));
        assertNull(DateTimeUtility.tryParseUserDateTime("not a date"));
        assertNull(DateTimeUtility.tryParseUserDateTime(""));
        assertNull(DateTimeUtility.tryParseUserDateTime("   "));
    }

    @Test
    public void formatForDisplay_nonNull_returnsFormattedString() {
        LocalDateTime dt = LocalDateTime.of(2019, 12, 2, 18, 0);
        String s = DateTimeUtility.formatForDisplay(dt);
        assertEquals("Dec 02 2019", s);
    }

    @Test
    public void formatForDisplay_null_returnsEmptyString() {
        assertEquals("", DateTimeUtility.formatForDisplay(null));
    }
}