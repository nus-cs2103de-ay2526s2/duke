package duchess.task;

/**
 * Enum representing the different types of tasks.
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T"),
    UNKNOWN("");

    public final String input;

    /**
     * Constructor for CommandType enum.
     *
     * @param input the input string
     */
    TaskType(String input) {
        this.input = input;
    }

    /**
     * Returns the task type of the input.
     *
     * @param input the input string
     * @return the task type of the input
     */
    public static TaskType getTaskType(String input) {
        for (TaskType type : TaskType.values()) {
            if (type.input.equals(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
