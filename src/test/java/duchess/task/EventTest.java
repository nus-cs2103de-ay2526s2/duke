package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Event class.
 */
public class EventTest {
    /**
     * Tests that an event object is created correctly.
     */
    @Test
    public void testEventCreation() {
        LocalDate startDate = LocalDate.parse("2026-02-05");
        LocalDate endDate = LocalDate.parse("2026-02-06");
        Event event = new Event("Test Event", startDate, endDate);
        assertEquals("Test Event", event.getName());
        assertFalse(event.isComplete());
    }

    /**
     * Tests the string representation of the event task.
     */
    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.parse("2026-02-05");
        LocalDate endDate = LocalDate.parse("2026-02-06");
        Event event = new Event("Test Event", startDate, endDate);
        assertEquals("[E][ ] Test Event (from: Thu, 05 Feb 2026 to: Fri, 06 Feb 2026)", event.toString());
        event.markAsComplete();
        assertEquals("[E][X] Test Event (from: Thu, 05 Feb 2026 to: Fri, 06 Feb 2026)", event.toString());
    }

    /**
     * Tests the save string representation of the event task.
     */
    @Test
    public void testToSaveString() {
        LocalDate startDate = LocalDate.parse("2026-02-05");
        LocalDate endDate = LocalDate.parse("2026-02-06");
        Event event = new Event("Test Event", startDate, endDate);
        assertEquals("E | 0 | Test Event | 2026-02-05 | 2026-02-06", event.toSaveString());
        event.markAsComplete();
        assertEquals("E | 1 | Test Event | 2026-02-05 | 2026-02-06", event.toSaveString());
    }

    /**
     * Tests the isOutstanding method.
     */
    @Test
    public void testIsOutstanding() {
        LocalDate startDate = LocalDate.parse("2026-02-05");
        LocalDate endDate = LocalDate.parse("2026-02-07");
        Event event = new Event("Test Event", startDate, endDate);

        // Before event
        assertFalse(event.isOutstanding(LocalDate.parse("2026-02-04")));
        // During event
        assertTrue(event.isOutstanding(LocalDate.parse("2026-02-05")));
        assertTrue(event.isOutstanding(LocalDate.parse("2026-02-06")));
        assertTrue(event.isOutstanding(LocalDate.parse("2026-02-07")));
        // After event
        assertFalse(event.isOutstanding(LocalDate.parse("2026-02-08")));

        // Completed
        event.markAsComplete();
        assertFalse(event.isOutstanding(LocalDate.parse("2026-02-06")));
    }
}
