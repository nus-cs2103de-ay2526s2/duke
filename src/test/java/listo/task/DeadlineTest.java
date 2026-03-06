package listo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        // Verifies that the Deadline parses the input date and prints it in the "MMM dd yyyy" format.
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (dueDateTime: Dec 02 2019, 6:00 PM)", d.toString());
    }

    @Test
    public void testToFileFormat() {
        // Verifies that the file format preserves the original raw date string for easy reloading.
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("D | 0 | return book | 2/12/2019 1800", d.toFileFormat());
    }
}