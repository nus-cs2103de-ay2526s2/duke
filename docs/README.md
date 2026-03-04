# STAR BOT - User Guide

**STAR BOT** is your personal celestial assistant, designed to help you navigate and organize your universe of tasks. Optimized for quick typing and equipped with a sleek Graphical User Interface (GUI), STAR BOT ensures you never miss a deadline.

## Quick Start
1. Download the latest `.jar` release of STAR BOT.
2. Open your terminal or command prompt, navigate to the folder containing the `.jar` file, and run it using the `java -jar <filename>.jar` command.
3. The STAR BOT window will appear with a welcoming message.
4. Type a command into the text box and press **Enter** (or click **Send**) to execute it.
5. *Tip:* You can click the **☰** menu button on the bottom left to quickly autofill command templates!

---

## UI Features
STAR BOT includes several UX enhancements to make task management seamless:
* **Command Palette:** Click the **☰** icon to view a list of all available commands. Selecting one will automatically type it into your input box.
* **Real-time Validation:** If you type an unrecognized command or miss a required parameter, the input box will turn red to alert you before you even hit send. Hover over the red box to see exactly what went wrong!
* **Asymmetric Chat:** User inputs and bot responses are styled distinctively to make reading your task history easy on the eyes.

---

## Command Dictionary

Below is the comprehensive list of commands you can use to interact with STAR BOT.

### 1. Task Management
Add different types of tasks to your tracker.

* **Add a To-Do:** Adds a basic task without any date or time restrictions.
    * **Format:** `todo <description>`
    * **Example:** `todo run`

* **Add a Deadline:** Adds a task that needs to be done before a specific date/time.
    * **Format:** `deadline <description> /by <date>`
    * **Example:** `deadline return library book /by 2026-12-01`

* **Add an Event:** Adds an event that spans a specific duration.
    * **Format:** `event <description> /st <start_date> /by <end_date>`
    * **Example:** `event project meeting /st 2026-03-10 /by 2026-03-12`

### 2. Tracking Progress
Keep your task list updated as you complete your goals.

* **List all tasks:** Displays your entire list of saved tasks.
    * **Format:** `list`

* **Mark a task as done:** Marks the task at the specified index number as completed.
    * **Format:** `mark <index>`
    * **Example:** `mark 1`

* **Mark a task as not done:** Reverts the task at the specified index number to incomplete.
    * **Format:** `unmark <index>`
    * **Example:** `unmark 1`

* **Delete a task:** Permanently removes the task at the specified index.
    * **Format:** `delete <index>`
    * **Example:** `delete 3`

### 3. Utility Commands
Additional tools to help you navigate your tasks and manage the application.

* **Find a task:** Searches for tasks that contain the specified keyword in their description.
    * **Format:** `find <keyword>`
    * **Example:** `find book`

* **Get Motivation:** Asks STAR BOT to send a motivational message to keep you going.
    * **Format:** `cheer`

* **Save data:** Manually saves your current task list to the hard drive. *(Note: STAR BOT also auto saves after every modification).*
    * **Format:** `save`

* **Exit:** Closes the application.
    * **Format:** `bye`

---

## Command Summary Table

| Action       | Format                                                | Example                                           |
|:-------------|:------------------------------------------------------|:--------------------------------------------------|
| **Todo**     | `todo <description>`                                  | `todo run`                                        |
| **Deadline** | `deadline <description> /by <date>`                   | `deadline submit assignment /by 2026-03-05`       |
| **Event**    | `event <description> /st <start_date> /by <end_date>` | `event career fair /st 2026-08-10 /by 2026-08-11` |
| **List**     | `list`                                                | `list`                                            |
| **Mark**     | `mark <index>`                                        | `mark 2`                                          |
| **Unmark**   | `unmark <index>`                                      | `unmark 2`                                        |
| **Delete**   | `delete <index>`                                      | `delete 1`                                        |
| **Find**     | `find <keyword>`                                      | `find meeting`                                    |
| **Cheer**    | `cheer`                                               | `cheer`                                           |
| **Save**     | `save`                                                | `save`                                            |
| **Exit**     | `bye`                                                 | `bye`                                             |