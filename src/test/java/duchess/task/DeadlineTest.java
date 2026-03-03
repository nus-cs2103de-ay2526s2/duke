package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Deadline class.
 */
public class DeadlineTest {
    /**
     * Tests that a deadline object is created correctly.
     */
    @Test
    public void testDeadlineCreation() {
        LocalDate deadlineDate = LocalDate.parse("2026-02-05");
        Deadline deadline = new Deadline("Test Deadline", deadlineDate);
        assertEquals("Test Deadline", deadline.getName());
        assertFalse(deadline.isComplete());
    }

    /**
     * Tests the string representation of the deadline task.
     */
    @Test
    public void testToString() {
        LocalDate deadlineDate = LocalDate.parse("2026-02-05");
        Deadline deadline = new Deadline("Test Deadline", deadlineDate);
        assertEquals("[D][ ] Test Deadline (by: Thu, 05 Feb 2026)", deadline.toString());
        deadline.markAsComplete();
        assertEquals("[D][X] Test Deadline (by: Thu, 05 Feb 2026)", deadline.toString());
    }

    /**
     * Tests the save string representation of the deadline task.
     */
    @Test
    public void testToSaveString() {
        LocalDate deadlineDate = LocalDate.parse("2026-02-05");
        Deadline deadline = new Deadline("Test Deadline", deadlineDate);
        assertEquals("D | 0 | Test Deadline | 2026-02-05", deadline.toSaveString());
        deadline.markAsComplete();
        assertEquals("D | 1 | Test Deadline | 2026-02-05", deadline.toSaveString());
    }

    /**
     * Tests the isOutstanding method.
     */
    @Test
    public void testIsOutstanding() {
        LocalDate deadlineDate = LocalDate.parse("2026-02-05");
        Deadline deadline = new Deadline("Test Deadline", deadlineDate);

        // Before deadline
        assertTrue(deadline.isOutstanding(LocalDate.parse("2026-02-04")));
        // On deadline (Wait, isAfter(date) means if date is the same as deadline, it's NOT after)
        // Deadline.java: return deadline.isAfter(date) && !isComplete();
        // So if date is 2026-02-05, deadline.isAfter(2026-02-05) is false.
        assertFalse(deadline.isOutstanding(LocalDate.parse("2026-02-05")));
        // After deadline
        assertFalse(deadline.isOutstanding(LocalDate.parse("2026-02-06")));

        // Completed
        deadline.markAsComplete();
        assertFalse(deadline.isOutstanding(LocalDate.parse("2026-02-04")));
    }
}
