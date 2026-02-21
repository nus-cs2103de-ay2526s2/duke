package mickey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mickey.task.Deadline;
import mickey.task.Event;
import mickey.task.Task;
import mickey.task.TaskList;
import mickey.task.Todo;
import mickey.util.FileSaver;
import mickey.util.Parser;
import mickey.util.UI;

/**
 * Main Mickey chatbot class
 * Takes care of managing tasks and responding to user commands
 */
public class Mickey {
    private TaskList tasks;
    private UI ui;
    private int taskCount;
    private FileSaver saver;
    private ArrayList<String> quotes;
    private String lastCommandType; // tracks what kind of command was just run

    /**
     * Constructor - sets up Mickey with saved tasks from file
     */
    public Mickey(String filePath) {
        this.ui = new UI();
        this.saver = new FileSaver(filePath);
        this.tasks = new TaskList(saver.loadTasks());
        this.taskCount = tasks.size();
        this.quotes = saver.loadQuotes();
        this.lastCommandType = null;
        assert taskCount >= 0 : "Task count should not be negative";
    }

    /**
     * Gets the last command type executed
     *
     * @return command type like "todo", "mark", "delete"
     */
    public String getLastCommandType() {
        return lastCommandType;
    }

    /**
     * Main method for chatbot
     */
    public static void main(String[] args) {
        new Mickey("./data/mickey.txt").run();
    }

    // helper method to save tasks to file
    private void saveTask() {
        saver.saveTasks(tasks.getAllTasks());
    }

    /**
     * To run the chatbot
     */
    public void run() {
        ui.welcomeLogo();
        String userInput = ui.userInput();

        while (!userInput.equals("bye")) {
            String command = Parser.getCommand(userInput);
            ui.nextLine();

            if (userInput.equals("list") || userInput.equals("to-do")) {
                handleListCommand();
            } else if (command.equals("mark")) {
                handleMarkCommand(userInput);
            } else if (command.equals("unmark")) {
                handleUnmarkCommand(userInput);
            } else if (command.equals("todo")) {
                handleTodoCommand(userInput);
            } else if (command.equals("deadline")) {
                handleDeadlineCommand(userInput);
            } else if (command.equals("event")) {
                handleEventCommand(userInput);
            } else if (command.equals("delete")) {
                handleDeleteCommand(userInput);
            } else if (command.equals("due")) {
                handleDueCommand(userInput);
            } else if (command.equals("cheer")) {
                handleCheerCommand();
            } else if (command.equals("find")) {
                handleFindCommand(userInput);
            } else {
                handleEchoCommand(userInput);
            }

            ui.nextLine();
            userInput = ui.userInput();
        }

        ui.sayBye();
        ui.close();
    }

    /**
     * Shows all tasks in the list
     */
    private void handleListCommand() {
        if (taskCount == 0) {
            ui.showNoTask();
        } else {
            ui.allTaskList();
            for (int i = 0; i < taskCount; i++) {
                Task currentTask = tasks.getTask(i);
                int displayIndex = i + 1;
                ui.showTask(displayIndex, currentTask.toString());
            }
        }
    }

    /**
     * Searches for tasks matching a keyword
     *
     * @param userInput the user input containing the keyword to search
     */
    private void handleFindCommand(String userInput) {
        if (userInput.length() <= 4) {
            System.out.println("Please enter the keyword to search by");
            return;
        }
        String keyword = Parser.getKeywordToSearch(userInput);
        ArrayList<Task> matchResults = new ArrayList<>();

        // loop through all tasks and check if keyword matches
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchResults.add(task);
            }
        }
        ui.showKeywordResults(matchResults);
    }

    /**
     * Removes a task from the list
     *
     * @param userInput the user input containing the task number to be deleted
     */
    private void handleDeleteCommand(String userInput) {
        try {
            int taskNumber = Parser.getDeletedTask(userInput);
            int taskIndex = taskNumber - 1;

            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                Task deletedTask = tasks.deleteTask(taskIndex);
                taskCount--;
                saveTask();
                ui.showDeleted(deletedTask.toString(), taskCount);
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Handles the cheer command
     */
    private void handleCheerCommand() {
        if (quotes.isEmpty()) {
            System.out.println(" Keep coding! You're doing great!");
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.size());
        String randomQuote = quotes.get(randomIndex);
        System.out.println(randomQuote);
    }

    /**
     * Marks a task as done
     *
     * @param userInput containing the task number to be marked as done
     */
    private void handleMarkCommand(String userInput) {
        try {
            int taskNumber = Parser.getCompletedTask(userInput);
            int taskIndex = taskNumber - 1;

            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                tasks.markTask(taskIndex);
                Task selectedTask = tasks.getTask(taskIndex);
                saveTask();
                ui.showMarked();
                System.out.println(" " + selectedTask.toString());
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Marks a task as incomplete
     *
     * @param userInput contains the task number to be marked as incomplete
     */
    private void handleUnmarkCommand(String userInput) {
        try {
            int taskNumber = Parser.getCompletedTask(userInput);
            int taskIndex = taskNumber - 1;

            if (taskIndex < 0 || taskIndex >= taskCount) {
                ui.showInvalidTaskNumber();
            } else {
                tasks.unmarkTask(taskIndex);
                Task selectedTask = tasks.getTask(taskIndex);
                saveTask();
                ui.showUnmarked();
                System.out.println(" " + selectedTask.toString());
            }
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }
    }

    /**
     * Adds a todo task to the task list
     *
     * @param userInput contains the description of the task
     */
    private void handleTodoCommand(String userInput) {
        if (userInput.length() <= 4) {
            ui.showEmptyTodoError();
        } else {
            String description = Parser.getTodoDescription(userInput);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            taskCount++;
            saveTask();
            ui.showTaskAdded(newTodo.toString(), taskCount);
        }
    }

    /**
     * Shows all tasks due on a particular date
     *
     * @param userInput contains the date to show tasks due
     */
    private void handleDueCommand(String userInput) {
        try {
            LocalDate date = Parser.getDateInFormat(userInput);
            ArrayList<Task> dueTasks = new ArrayList<>();
            for (Task task : tasks.getAllTasks()) {
                if (taskIsSameDate(task, date)) {
                    dueTasks.add(task);
                }
            }
            ui.showDueTasks(dueTasks, date);
        } catch (DateTimeParseException e) {
            ui.showInvalidDate();
        }
    }

    /**
     * Checks if a task is due on a specific date
     *
     * @param task the task
     * @param date the date to check
     * @return true if the task is due on the date and false if not due
     */
    private boolean taskIsSameDate(Task task, LocalDate date) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDateBy().equals(date);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            LocalDate eventDate = event.getDateFrom().toLocalDate();
            return eventDate.equals(date);
        }
        return false;
    }

    /**
     * Adds a deadline task
     *
     * @param userInput contains the description and deadline date of the task
     */
    private void handleDeadlineCommand(String userInput) {
        int byIndex = Parser.getByIndex(userInput);

        if (userInput.length() <= 8) {
            ui.showDeadlineEmptyError();
        } else if (byIndex == -1) {
            ui.showDeadlineMissingDateError();
        } else if (byIndex <= 10) {
            ui.showDeadlineNoDescriptionError();
        } else {
            try {
                Object[] deadlineDetails = Parser.getDeadline(userInput);
                String description = (String) deadlineDetails[0];
                LocalDate dateBy = (LocalDate) deadlineDetails[1];
                Deadline newDeadline = new Deadline(description, dateBy);
                tasks.addTask(newDeadline);
                taskCount++;
                saveTask();
                ui.showTaskAdded(newDeadline.toString(), taskCount);
            } catch (DateTimeParseException e) {
                ui.showInvalidDate();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input user input string
     * @return response message
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            lastCommandType = null;
            return "Aww bye bye! Catch you later!";
        }

        String command = Parser.getCommand(input);
        try {
            if (input.equals("list") || input.equals("to-do")) {
                lastCommandType = "list";
                return getListResponse();
            } else if (command.equals("mark")) {
                lastCommandType = "mark";
                return getMarkResponse(input);
            } else if (command.equals("unmark")) {
                lastCommandType = "unmark";
                return getUnmarkResponse(input);
            } else if (command.equals("todo")) {
                lastCommandType = "todo";
                return getTodoResponse(input);
            } else if (command.equals("deadline")) {
                lastCommandType = "deadline";
                return getDeadlineResponse(input);
            } else if (command.equals("event")) {
                lastCommandType = "event";
                return getEventResponse(input);
            } else if (command.equals("delete")) {
                lastCommandType = "delete";
                return getDeleteResponse(input);
            } else if (command.equals("due")) {
                lastCommandType = "due";
                return getDueResponse(input);
            } else if (command.equals("cheer")) {
                lastCommandType = "cheer";
                return getCheerResponse();
            } else if (command.equals("find")) {
                lastCommandType = "find";
                return getFindResponse(input);
            } else {
                lastCommandType = "todo";
                return getEchoResponse(input);
            }
        } catch (Exception e) {
            lastCommandType = null;
            return "Oops! Something went wrong: " + e.getMessage();
        }
    }

    private String getEchoResponse(String input) {
        Todo echoTask = new Todo(input);
        tasks.addTask(echoTask);
        taskCount++;
        saveTask();
        return "Got it! Added:\n  " + echoTask.toString()
                + "\n\nYou now have " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " total.";
    }

    private String getListResponse() {
        if (taskCount == 0) {
            return "Your list is empty! Time to add some tasks maybe?";
        }
        StringBuilder response = new StringBuilder("Alright, here's what you've got:\n");
        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks.getTask(i);
            int displayIndex = i + 1;
            response.append(displayIndex).append(". ").append(currentTask.toString()).append("\n");
        }
        return response.toString().trim();
    }

    private String getFindResponse(String input) {
        if (input.length() <= 4) {
            return "Umm, you need to give me a keyword to search for!";
        }
        String keyword = Parser.getKeywordToSearch(input);
        List<Task> matchResults = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (matchResults.isEmpty()) {
            return "Hmm, couldn't find anything matching '" + keyword + "'";
        }

        StringBuilder response = new StringBuilder("Found these for you:\n");
        IntStream.range(0, matchResults.size())
                .forEach(i -> response.append((i + 1)).append(". ")
                        .append(matchResults.get(i).toString()).append("\n"));
        return response.toString().trim();
    }

    private String getDeleteResponse(String input) {
        try {
            int taskNumber = Parser.getDeletedTask(input);
            int taskIndex = taskNumber - 1;

            if (taskIndex < 0 || taskIndex >= taskCount) {
                return "Oops! That task number doesn't exist!";
            }

            Task deletedTask = tasks.deleteTask(taskIndex);
            taskCount--;
            saveTask();
            return "Alrighty, deleted this one:\n  " + deletedTask.toString()
                    + "\nYou've got " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " left!";
        } catch (NumberFormatException e) {
            return "Hey, I need a valid task number!";
        }
    }

    private String getCheerResponse() {
        if (quotes.isEmpty()) {
            return "You're doing amazing! Keep crushing it!";
        }
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.size());
        return quotes.get(randomIndex);
    }

    private String getMarkResponse(String input) {
        try {
            int taskIndex = getTaskIndex(input);
            if (taskIndex < 0) {
                return "Hmm, that task number doesn't exist!";
            }
            tasks.markTask(taskIndex);
            Task selectedTask = tasks.getTask(taskIndex);
            saveTask();
            return "Yay! Marked this as done:\n  " + selectedTask.toString() + "\n\nNice work!";
        } catch (NumberFormatException e) {
            return "Hey, gimme a valid task number!";
        }
    }

    private String getUnmarkResponse(String input) {
        try {
            int taskIndex = getTaskIndex(input);
            if (taskIndex < 0) {
                return "Hmm, that task number doesn't exist!";
            }
            tasks.unmarkTask(taskIndex);
            Task selectedTask = tasks.getTask(taskIndex);
            saveTask();
            return "Okay okay, unmarked this one:\n  " + selectedTask.toString() + "\n\nBack to the to-do pile!";
        } catch (NumberFormatException e) {
            return "I need a valid task number!";
        }
    }

    /**
     * Get the task index for mark/unmark commands.
     * 
     * @return the task index
     * @throws NumberFormatException if input is invalid
     * @param input the user input
     */
    private int getTaskIndex(String input) throws NumberFormatException {
        int taskNumber = Parser.getCompletedTask(input);
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= taskCount) {
            return -1;
        }
        return taskIndex;
    }

    private String getTodoResponse(String input) {
        if (input.length() <= 4) {
            return "Wait, you can't add an empty todo! Give me something to work with!";
        }
        String description = Parser.getTodoDescription(input);
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        taskCount++;
        saveTask();
        return "Got it! Added:\n  " + newTodo.toString()
                + "\n\nYou now have " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " total.";
    }

    private String getDueResponse(String input) {
        try {
            LocalDate date = Parser.getDateInFormat(input);
            List<Task> dueTasks = tasks.getAllTasks().stream()
                    .filter(task -> taskIsSameDate(task, date))
                    .collect(Collectors.toList());

            if (dueTasks.isEmpty()) {
                return "Nothing due on " + date + "! You're free that day!";
            }

            StringBuilder response = new StringBuilder("Here's what's due on " + date + ":\n");
            for (int i = 0; i < dueTasks.size(); i++) {
                response.append((i + 1)).append(". ").append(dueTasks.get(i).toString()).append("\n");
            }
            return response.toString().trim();
        } catch (DateTimeParseException e) {
            return "Oops! Use this date format: YYYY-MM-DD";
        }
    }

    private String getDeadlineResponse(String input) {
        int byIndex = Parser.getByIndex(input);

        if (input.length() <= 8) {
            return "Hey, your deadline needs a description!";
        } else if (byIndex == -1) {
            return "You forgot the /by date! Try: deadline <task> /by <date>";
        } else if (byIndex <= 10) {
            return "Umm, the description can't be empty!";
        }

        try {
            Object[] deadlineDetails = Parser.getDeadline(input);
            String description = (String) deadlineDetails[0];
            LocalDate dateBy = (LocalDate) deadlineDetails[1];
            Deadline newDeadline = new Deadline(description, dateBy);
            tasks.addTask(newDeadline);
            taskCount++;
            saveTask();
            return "Deadline added!\n  " + newDeadline.toString()
                    + "\n\nYou've got " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " now.";
        } catch (DateTimeParseException e) {
            return "Hmm, that date format looks wrong! Use YYYY-MM-DD";
        }
    }

    private String getEventResponse(String input) {
        int fromIndex = Parser.getFromIndex(input);
        int toIndex = Parser.getToIndex(input);

        if (input.length() <= 5) {
            return "Hey, your event needs a description!";
        } else if (fromIndex == -1 || toIndex == -1) {
            return "You're missing /from or /to! Try: event <task> /from <date-time> /to <date-time>";
        }

        try {
            Object[] eventDetails = Parser.getEvent(input);
            String description = (String) eventDetails[0];
            LocalDateTime dateFrom = (LocalDateTime) eventDetails[1];
            LocalDateTime dateTo = (LocalDateTime) eventDetails[2];
            Event newEvent = new Event(description, dateFrom, dateTo);
            tasks.addTask(newEvent);
            taskCount++;
            saveTask();
            return "Event scheduled! \n  " + newEvent.toString()
                    + "\n\nThat's " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " on your plate!";
        } catch (DateTimeParseException e) {
            return "Oops! Wrong date-time format! Use YYYY-MM-DD HH:MM";
        }
    }

    /**
     * Adds an event task
     *
     * @param userInput contains the description and event date range of the task
     */
    private void handleEventCommand(String userInput) {
        int fromIndex = Parser.getFromIndex(userInput);
        int toIndex = Parser.getToIndex(userInput);

        if (userInput.length() <= 5) {
            ui.showEventEmptyError();
        } else if (fromIndex == -1 || toIndex == -1) {
            ui.showEventMissingDatesError();
        } else {
            try {
                Object[] eventDetails = Parser.getEvent(userInput);
                String description = (String) eventDetails[0];
                LocalDateTime dateFrom = (LocalDateTime) eventDetails[1];
                LocalDateTime dateTo = (LocalDateTime) eventDetails[2];
                Event newEvent = new Event(description, dateFrom, dateTo);
                tasks.addTask(newEvent);
                taskCount++;
                saveTask();
                ui.showTaskAdded(newEvent.toString(), taskCount);
            } catch (DateTimeParseException e) {
                ui.showInvalidDate();
            }
        }
    }

    /**
     * Adds as a Todo task default
     *
     * @param userInput contains the description of task
     */
    private void handleEchoCommand(String userInput) {
        Todo echoTask = new Todo(userInput);
        tasks.addTask(echoTask);
        taskCount++;
        saveTask();
        ui.showTaskAdded(echoTask.toString(), taskCount);
    }
}
