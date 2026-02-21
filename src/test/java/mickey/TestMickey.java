package mickey;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertTrue(response.contains("Got it!") || response.contains("Added"));
        assertTrue(response.contains("buy groceries"));
        assertEquals("todo", mickey.getLastCommandType());
    }

    @Test
    public void testInvalidMarkCommand() {
        // Test negative by marking non existent
        mickey.getResponse("todo only one task");
        String response = mickey.getResponse("mark 9999");

        assertTrue(response.contains("doesn't exist"));
    }
}
