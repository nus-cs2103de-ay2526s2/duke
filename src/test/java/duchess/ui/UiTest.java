package duchess.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Ui class.
 */
public class UiTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    /**
     * Sets up the test environment by redirecting System.out.
     */
    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    /**
     * Cleans up the test environment by restoring System.in and System.out.
     */
    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * Sets a mock input stream for System.in.
     * @param data the string to be used as input
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Returns the output from System.out.
     * @return the captured output string
     */
    private String getOutput() {
        return testOut.toString();
    }

    /**
     * Tests that the readCommand method correctly reads user input.
     */
    @Test
    public void testReadCommand() {
        provideInput("test command\n");
        Ui ui = new Ui();
        assertEquals("test command", ui.readCommand(), "Should read the provided command");
    }

    /**
     * Tests that the display method prints the message to System.out.
     */
    @Test
    public void testDisplay() {
        Ui ui = new Ui();
        ui.display("Hello World");
        assertEquals("Hello World" + System.lineSeparator(), getOutput(), "Should display the message");
    }

    /**
     * Tests that the welcome message is displayed correctly.
     */
    @Test
    public void testDisplayWelcomeMessage() {
        Ui ui = new Ui();
        ui.displayWelcomeMessage();
        String expected = "Hark, I be Duchess! What service dost thou require of me?" + System.lineSeparator();
        assertEquals(expected, getOutput(), "Should display the correct welcome message");
    }

    /**
     * Tests that the loading error message is displayed correctly.
     */
    @Test
    public void testDisplayLoadingErrorMessage() {
        Ui ui = new Ui();
        ui.displayLoadingErrorMessage();
        String expected = "Hark! An error hath befallen, the tasks from yon file could not be summoned. "
                + "Thus, we begin anew..." + System.lineSeparator();
        assertEquals(expected, getOutput(), "Should display the correct loading error message");
    }
}
