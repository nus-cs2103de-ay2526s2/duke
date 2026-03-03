package duchess.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.ToDo;

/**
 * Tests for the FileParser class.
 */
public class FileParserTest {
    /**
     * Tests that an exception is thrown when an invalid completion marker is provided.
     */
    @Test
    public void testGetTask_invalidCompletionMarker_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                FileParser.getTask("T | 2 | hello"),
                "Invalid completion marker");
    }

    /**
     * Tests that an exception is thrown when a todo task is missing its name.
     */
    @Test
    public void testGetTask_toDoMissingName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("T | 1"),
                "Missing task name");
    }

    /**
     * Tests that an exception is thrown when a todo task has too many arguments provided.
     */
    @Test
    public void testGetTask_toDoTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("T | 1 | hello | world"),
                "Invalid toDo task format");
    }

    /**
     * Tests that an exception is thrown when an event task has too many arguments provided.
     */
    @Test
    public void testGetTask_eventTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("E | 1 | task | a | 2001-01-01 | 2001-01-02"),
                "Invalid event task format");
    }

    /**
     * Tests that an exception is thrown when a deadline task has too many arguments provided.
     */
    @Test
    public void testGetTask_deadlineTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("D | 1 | hello | 2001-01-01 | 2001-01-01"),
                "Invalid deadline task format");
    }

    /**
     * Tests that an exception is thrown when an event task has too few arguments provided.
     */
    @Test
    public void testGetTask_eventTooFewArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("E | 1 | task"),
                "Invalid event task format");
    }

    /**
     * Tests that an exception is thrown when a deadline task has too few arguments provided.
     */
    @Test
    public void testGetTask_deadlineTooFewArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("D | 1 | hello"),
                "Invalid deadline task format");
    }

    /**
     * Tests that an exception is thrown when an invalid task type is provided.
     */
    @Test
    public void testGetTask_invalidTaskType_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                FileParser.getTask("F | 1 | hello"),
                "Invalid task type");
    }

    /**
     * Tests that a valid todo object is returned when a valid task is provided.
     */
    @Test
    public void testGetTask_toDo_success() throws Exception {
        String rawTask = "T | 0 | task";
        assertEquals(ToDo.class,
                FileParser.getTask(rawTask)
                        .getClass(),
                "ToDo object created successfully");
        assertFalse(FileParser.getTask(rawTask).isComplete(),
                "ToDo object initialised with correct completion status");
    }

    /**
     * Tests that a valid event object is returned when a valid task is provided.
     */
    @Test
    public void testGetTask_event_success() throws Exception {
        String rawTask = "E | 1 | task | 2001-01-01 | 2001-01-02";
        assertEquals(Event.class,
                FileParser.getTask(rawTask)
                        .getClass(),
                "Event object created successfully");
        assertTrue(FileParser.getTask(rawTask).isComplete(),
                "Event object initialised with correct completion status");
    }

    /**
     * Tests that a valid deadline object is returned when a valid task is provided.
     */
    @Test
    public void testGetTask_deadline_success() throws Exception {
        String rawTask = "D | 1 | task | 2001-01-01";
        assertEquals(Deadline.class,
                FileParser.getTask(rawTask)
                        .getClass(),
                "Deadline object created successfully");
        assertTrue(FileParser.getTask(rawTask).isComplete(),
                "Deadline object initialised with correct completion status");
    }

    /**
     * Tests that an exception is thrown when the task name is whitespace.
     */
    @Test
    public void testGetTask_whitespaceName_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                FileParser.getTask("T | 1 |    "),
                "Whitespace name");
    }

    /**
     * Tests that an exception is thrown when the task name is null/empty after split.
     */
    @Test
    public void testGetTask_emptyName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                FileParser.getTask("T | 1"),
                "Missing name");
    }
}
