package duchess.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

/**
 * Tests for the Utility class.
 */
public class UtilityTest {
    /**
     * Tests the splitIntoPair method can handle strings without the specified delimiters.
     */
    @Test
    public void testSplitIntoPair_singleLengthArray_success() {
        assertEquals("",
                Utility.splitIntoPair("0", " ")[1],
                "Handle single-length array");
    }

    /**
     * Tests the splitIntoPair method can handle empty strings.
     */
    @Test
    public void testSplitIntoPair_emptyArray_success() {
        assertEquals("",
                Utility.splitIntoPair("", " ")[1],
                "Handle empty array");
    }

    /**
     * Tests the splitIntoPair method can handle valid input.
     */
    @Test
    public void testSplitIntoPair_validInput_success() {
        assertEquals("world",
                Utility.splitIntoPair("hello | world", " \\| ")[1],
                "Split valid input");
    }

    /**
     * Tests that an exception is thrown when input is missing.
     */
    @Test
    public void testParseInt_missingNumber_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                Utility.parseInt(""),
                "Missing number");
    }

    /**
     * Tests that an exception is thrown when input is invalid.
     */
    @Test
    public void testParseInt_invalidNumber_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                Utility.parseInt("hello"),
                "Input is not a number");
    }

    /**
     * Tests that an exception is thrown when the input date is missing.
     */
    @Test
    public void testParseDate_missingDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                Utility.parseDate(""),
                "Missing date");
    }

    /**
     * Tests that an exception is thrown when the input date is invalid.
     */
    @Test
    public void testParseDate_invalidDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                Utility.parseInt("hello"),
                "Input is not a date");
    }

    /**
     * Tests that the date is formatted correctly.
     */
    @Test
    public void testFormatDate() {
        assertEquals("Thu, 05 Feb 2026",
                Utility.formatDate(LocalDate.parse("2026-02-05")),
                "Format LocalDate into specific String format");
    }

    /**
     * Tests that an exception is thrown when parsing a non-integer.
     */
    @Test
    public void testParseInt_nonInteger_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                Utility.parseInt("1.5"),
                "Input is not an integer");
    }

    /**
     * Tests that a valid string is detected correctly.
     */
    @Test
    public void testIsInvalidString() {
        assertTrue(Utility.isInvalidString(null), "Null should be invalid");
        assertTrue(Utility.isInvalidString(""), "Empty should be invalid");
        assertTrue(Utility.isInvalidString("   "), "Whitespace should be invalid");
        assertFalse(Utility.isInvalidString("a"), "Non-empty should be valid");
    }

    /**
     * Tests that an exception is thrown when the input date format is invalid.
     */
    @Test
    public void testParseDate_wrongFormat_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () ->
                Utility.parseDate("05-02-2026"),
                "Input date is in wrong format");
    }
}
