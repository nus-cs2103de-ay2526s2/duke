package duchess.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Response record.
 */
public class ResponseTest {
    /**
     * Tests that the response is created correctly with the correct fields.
     */
    @Test
    public void testResponseCreation() {
        Response response = new Response("Test Output", true);
        assertEquals("Test Output", response.commandOutput());
        assertTrue(response.shouldTerminate());

        Response continueResponse = new Response("Continue", false);
        assertEquals("Continue", continueResponse.commandOutput());
        assertFalse(continueResponse.shouldTerminate());
    }
}
