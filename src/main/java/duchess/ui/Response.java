package duchess.ui;

/**
 * Wrapper class for command output and termination status.
 * @param commandOutput output String after executing the command
 * @param shouldTerminate true if the program should terminate
 */
public record Response(String commandOutput, boolean shouldTerminate) {
}
