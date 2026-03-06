package chatterbox;

/**
 * Represents a list of tasks backed by a fixed-size array.
 */
public class TaskList {
    private final Task[] tasks;
    private int taskCount;

    /**
     * Creates an empty TaskList with the given capacity.
     *
     * @param capacity Maximum number of tasks.
     */
    public TaskList(int capacity) {
        assert capacity > 0 : "TaskList capacity must be positive";

        this.tasks = new Task[capacity];
        this.taskCount = 0;

        assert taskCount >= 0 && taskCount <= tasks.length : "taskCount out of bounds after init";
    }

    /**
     * Creates a TaskList using an existing array and count (used when loading from storage).
     *
     * @param tasks Existing tasks array.
     * @param taskCount Number of valid tasks in the array.
     */
    public TaskList(Task[] tasks, int taskCount) {
        assert tasks != null : "Tasks array cannot be null";
        assert taskCount >= 0 && taskCount <= tasks.length : "taskCount out of bounds for given array";
        
        this.tasks = tasks;
        this.taskCount = taskCount;
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return Task count.
     */
    public int size() {
        return taskCount;
    }

    /**
     * Returns the backing task array.
     *
     * @return Task array.
     */
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Returns task by 1-based index.
     *
     * @param index 1-based task number.
     * @return Task at that position.
     * @throws ChatterBotException If index is invalid.
     */
    public Task get(int index) throws ChatterBotException {
        if (!isValidIndex(index)) {
            throw new ChatterBotException(Errors.INDEX_OUT_OF_RANGE);
        }

        Task t = tasks[index - 1];
        assert t != null : "Task at valid index should not be null";
        return t;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        assert task != null :  "Cannot add null task";
        assert taskCount < tasks.length : "Cannot add task, TaskList is full";

        tasks[taskCount++] = task;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index 1-based task number.
     * @throws ChatterBotException If index is invalid.
     */
    public void mark(int index) throws ChatterBotException {
        get(index).markDone();
    }



    /**
     * Deletes and returns the task at the given index.
     *
     * @param index 1-based task number.
     * @return Removed task.
     * @throws ChatterBotException If index is invalid.
     */
    public Task delete(int index) throws ChatterBotException {
        if (!isValidIndex(index)) {
            throw new ChatterBotException(Errors.INDEX_OUT_OF_RANGE);
        }

        Task removed = tasks[index - 1];

        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[taskCount - 1] = null;
        taskCount--;

        return removed;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index 1-based task number.
     * @throws ChatterBotException If index is invalid.
     */
    public void unmark(int index) throws ChatterBotException {
        get(index).markNotDone();
    }

    /**
     * Finds tasks whose description contains the given keyword (case-insensitive).
     *
     * @param keyword Keyword to match.
     * @return FindResult containing matches and count.
     */
    public FindResult findByKeyword(String keyword) {
        String needle = keyword.toLowerCase();
        Task[] matches = new Task[taskCount];
        int count = 0;

        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];
            if (task != null && task.getDescription().toLowerCase().contains(needle)) {
                matches[count++] = task;
            }
        }

        return new FindResult(matches, count);
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= taskCount;
    }

    /**
     * Represents the result of a find operation.
     */
    public static class FindResult {
        public final Task[] matches;
        public final int count;

        public FindResult(Task[] matches, int count) {
            this.matches = matches;
            this.count = count;
        }
    }
}