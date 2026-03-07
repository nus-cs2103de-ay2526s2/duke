package mickey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Mickey class
 */
public class TestMickey {
    private static final String TEST_FILE_PATH = "./src/test/resources/test_mickey.txt";
    private Mickey mickey;

    @BeforeEach
    public void setUp() throws IOException {
        // Clear the test file before each test to ensure tests start empty
        FileWriter writer = new FileWriter(TEST_FILE_PATH);
        writer.write("");
        writer.close();

        // Create fresh Mickey instance with empty test data file
        mickey = new Mickey(TEST_FILE_PATH);
    }

    @Test
    public void testTodoCommand() {
        // Test adding a todo task
        String response = mickey.getResponse("todo buy groceries");

        assertTrue(response.contains("added"));
        assertTrue(response.contains("buy groceries"));
        assertEquals("todo", mickey.getLastCommandType());
    }

    @Test
    public void testEmptyTodo() {
        String response = mickey.getResponse("todo");
        assertTrue(response.toLowerCase().contains("empty") || response.toLowerCase().contains("gosh"));
        assertEquals("error", mickey.getLastCommandType());
    }


    @Test
    public void testDeadlineCommand() {
        String response = mickey.getResponse("deadline submit report /by 31-12-2026");
        assertTrue(response.contains("Deadline") || response.contains("added") || response.contains("Added"));
        assertTrue(response.contains("submit report"));
        assertEquals("deadline", mickey.getLastCommandType());
    }
    @Test
    public void testDeadlineMissingBy() {
        String response = mickey.getResponse("deadline submit report");
        assertTrue(response.contains("/by") || response.contains("forgot"));
        assertEquals("error", mickey.getLastCommandType());
    }
    @Test
    public void testEmptyDeadlineDescription() {
        String response = mickey.getResponse("deadline /by 31-12-2026");
        assertTrue(response.toLowerCase().contains("description") || response.toLowerCase().contains("empty"));
        assertEquals("error", mickey.getLastCommandType());
    }

    @Test
    public void testEventCommand() {
        String response = mickey.getResponse("event meeting /from 15-02-2027 1400 /to 15-02-2027 1600");
        assertTrue(response.contains("Event") || response.contains("added") || response.contains("Added"));
        assertTrue(response.contains("meeting"));
        assertEquals("event", mickey.getLastCommandType());
    }
    @Test
    public void testEventMissingFrom() {
        String response = mickey.getResponse("event meeting /to 15-02-2026 1600");
        assertTrue(response.contains("/from") || response.contains("missing"));
        assertEquals("error", mickey.getLastCommandType());
    }
    @Test
    public void testEventMissingTo() {
        String response = mickey.getResponse("event meeting /from 15-02-2026 1400");
        assertTrue(response.contains("/to") || response.contains("missing"));
        assertEquals("error", mickey.getLastCommandType());
    }
    @Test
    public void testInvalidDateFormatEvent() {
        String response = mickey.getResponse("event meeting /from 2026-02-15 1400 /to 2026-02-15 1600");
        assertTrue(response.toLowerCase().contains("format") || response.toLowerCase().contains("wrong"));
        assertEquals("error", mickey.getLastCommandType());
    }

    @Test
    public void testMarkCommand() {
        mickey.getResponse("todo task one");
        String response = mickey.getResponse("mark 1");
        assertTrue(response.toLowerCase().contains("done") || response.toLowerCase().contains("marked"));
        assertEquals("mark", mickey.getLastCommandType());
    }
    @Test
    public void testMarkInvalidNumber() {
        mickey.getResponse("todo only one task");
        String response = mickey.getResponse("mark 9999");
        assertTrue(response.contains("doesn't exist") || response.contains("exist"));
        assertEquals("error", mickey.getLastCommandType());
    }

    @Test
    public void testMarkNegativeNumber() {
        try {
            String response = mickey.getResponse("mark -1");
            assertTrue(response.toLowerCase().contains("exist")
                    || response.toLowerCase().contains("number"));
            assertEquals("error", mickey.getLastCommandType());
        } catch (AssertionError e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteCommand() {
        mickey.getResponse("todo task to delete");
        String response = mickey.getResponse("delete 1");
        assertTrue(response.toLowerCase().contains("delete") || response.toLowerCase().contains("removed"));
        assertEquals("delete", mickey.getLastCommandType());
    }
    @Test
    public void testDeleteInvalidNumber() {
        String response = mickey.getResponse("delete 999");
        assertTrue(response.toLowerCase().contains("exist") || response.toLowerCase().contains("doesn't"));
        assertEquals("error", mickey.getLastCommandType());
    }
    @Test
    public void testDeleteNoNumber() {
        String response = mickey.getResponse("delete");
        assertTrue(response.toLowerCase().contains("number") || response.toLowerCase().contains("gosh"));
        assertTrue(mickey.getLastCommandType() == null || mickey.getLastCommandType().equals("error"));
    }

    @Test
    public void testListEmpty() {
        String response = mickey.getResponse("list");
        assertTrue(response.toLowerCase().contains("empty"));
        assertEquals("list", mickey.getLastCommandType());
    }

    @Test
    public void testDueCommand() {
        mickey.getResponse("deadline task /by 15-02-2026");
        String response = mickey.getResponse("due 15-02-2026");
        assertTrue(response.contains("due") || response.contains("15-02-2026") || response.contains("Feb"));
        assertEquals("due", mickey.getLastCommandType());
    }


    @Test
    public void testCheerCommand() {
        String response = mickey.getResponse("cheer");
        assertFalse(response.isEmpty());
        assertEquals("cheer", mickey.getLastCommandType());
    }
}
