package mickey.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class for Parser
 * Tests various parsing methods
 */
public class TestParser {

    @Test
    public void testGetCommand() {
        // test basic commands
        String result1 = Parser.getCommand("todo read book");
        assertEquals("todo", result1);

        String result2 = Parser.getCommand("deadline return book /by 25-12-2024");
        assertEquals("deadline", result2);

        assertEquals("list", Parser.getCommand("list"));
        assertEquals("bye", Parser.getCommand("bye"));
    }

    @Test
    public void testTodoDescription() {
        String desc = Parser.getTodoDescription("todo read book");
        assertEquals("read book", desc);
    }

    @Test
    public void testTodoWithSpaces() {
        // edge case: extra spaces
        String desc = Parser.getTodoDescription("todo   multiple   spaces");
        assertEquals("  multiple   spaces", desc);
    }

    @Test
    public void testDeadlineParsing() {
        Object[] result = Parser.getDeadline("deadline return book /by 25-12-2024");

        // check description
        assertEquals("return book", result[0]);
        // check date
        assertEquals(LocalDate.of(2024, 12, 25), result[1]);
    }

    @Test
    public void testDeadlineWithDifferentDate() {
        Object[] result = Parser.getDeadline("deadline submit assignment /by 31-01-2025");
        assertEquals("submit assignment", result[0]);
        assertEquals(LocalDate.of(2025, 1, 31), result[1]);
    }

    @Test
    public void testEventParsing() {
        // test event with from and to dates
        Object[] result = Parser.getEvent("event meeting /from 20-08-2024 1400 /to 20-08-2024 1600");

        assertEquals("meeting", result[0]);
        assertEquals(LocalDateTime.of(2024, 8, 20, 14, 0), result[1]);
        assertEquals(LocalDateTime.of(2024, 8, 20, 16, 0), result[2]);
    }

    @Test
    public void testGetTaskNumber() {
        // test extracting task numbers from commands
        int num1 = Parser.getCompletedTask("mark 1");
        assertEquals(1, num1);

        int num2 = Parser.getCompletedTask("unmark 5");
        assertEquals(5, num2);

        assertEquals(10, Parser.getCompletedTask("delete 10"));
    }

    @Test
    public void testFindByIndex() {
        // should find /by at position 14
        int index = Parser.getByIndex("deadline test /by 25-12-2024");
        assertEquals(14, index);

        // should return -1 if /by not found
        assertEquals(-1, Parser.getByIndex("deadline test"));
    }
}
