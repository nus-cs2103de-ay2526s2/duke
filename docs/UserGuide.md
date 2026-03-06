# User Guide

Listo is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still providing the benefits of a Graphical User Interface (GUI).

## Quick Start

1. Ensure you have Java `21` or above installed in your Computer.
2. Download the latest `listo-0.1.0.jar` from [here](https://github.com/YeowChunSiang/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your personal task manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar listo-0.1.0.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all tasks.
    * **`todo read book`** : Adds a todo task with the description `read book`.
    * **`deadline return book /by 2/12/2026 1800`** : Adds a deadline task.
    * **`delete 3`** : Deletes the 3rd task shown in the current list.
    * **`bye`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

## Features

### Seeing the help message: `help`

Shows a list of all available commands.

Format: `help`

### Adding a todo task: `todo`

Adds a standard todo task to the list.

Format: `todo <description>`

Example: `todo read book`

### Adding a deadline: `deadline`

Adds a task with a specific deadline date.

Format: `deadline <description> /by <date>`
* Date format must be `d/M/yyyy HHmm` (e.g., 2/12/2026 1800)

### Adding an event: `event`

Adds a task that spans a specific time period.

Format: `event <description> /from <start> /to <end>`
* If start and end are dates, the format must be `d/M/yyyy` (e.g., 2/12/2026)

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `mark`

Marks an existing task as completed.

Format: `mark <index>`
* The index refers to the number shown in the displayed task list.
* The index must be a positive integer (1, 2, 3...).

Example: `mark 1`

### Marking a task as not done: `unmark`

Marks an existing task as not yet completed.

Format: `unmark <index>`
* The index refers to the number shown in the displayed task list.
* The index must be a positive integer (1, 2, 3...).

Example: `unmark 1`

### Locating tasks by name: `find`

Finds tasks whose names contain the given keyword (case-insensitive).

Format: `find <keyword>`

Example: `find book`

### Filtering tasks by date: `filter`

Finds all deadlines and events occurring on a specific date.

Format: `filter <date>`
* Date format must be `d/M/yyyy`

Example: `filter 2/12/2026`

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete <index>`
* The index refers to the number shown in the displayed task list.
* The index must be a positive integer (1, 2, 3...).

Example: `delete 3`

### Getting motivation: `cheer`

Displays a random motivational message to keep you going!

Format: `cheer`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Listo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.