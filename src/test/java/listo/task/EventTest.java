package listo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        // Verifies that an Event creates the correct string format with "from" and "to" labels.
        Event e = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", e.toString());
    }

    @Test
    public void testToFileFormat() {
        // Verifies that the file format string uses the correct delimiters (|) for saving.
        Event e = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("E | 0 | project meeting | Mon 2pm | 4pm", e.toFileFormat());
    }
}