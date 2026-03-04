package model;

/**
 * Represents the priority level of a task.
 */
public enum Priority {
    LOW(3),
    MEDIUM(2),
    HIGH(1);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    /**
     * Lower number means higher priority.
     * @return level priority level
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param value
     * @return
     */
    public static Priority fromString(String value) {
        switch (value.toUpperCase()) {
        case "HIGH":
        case "H":
        case "1":
            return HIGH;
        case "MEDIUM":
        case "M":
        case "2":
            return MEDIUM;
        case "LOW":
        case "L":
        case "3":
        default:
            return LOW;
        }
    }
}