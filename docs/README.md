# Mickey User Guide

Mickey is a task management chatbot that helps you keep track of your todos, deadlines, and events. It's got a GUI that makes you want to track all your tasks here.

## Quick start

1. Make sure you have Java 21 installed.

2. Download the latest `.jar` file from releases.

3. Double-click it or run `java -jar mickey.jar` in your terminal.

4. A window should pop up. Type commands in the text box at the bottom and hit Enter.

5. Try these to get started:
   - `list` - see what's on your plate
   - `todo finish assignment` - add a quick task
   - `deadline project report /by 31-12-2026` - add something with a due date
   - `mark 1` - check off the first task
   - `bye` - close the app



## Features

**Quick notes:**
- Commands aren't case-sensitive (`LIST` = `list`)
- Extra spaces are fine (`todo    buy    milk` works)
- Dates use `DD-MM-YYYY` format
- Times use 24-hour format like `1400` for 2pm



### `list` - See all your tasks

Shows everything you've added.

**Example:** `list`



### `todo` - Add a basic task

For stuff without a specific deadline.

**Format:** `todo DESCRIPTION`

**Examples:**
- `todo buy groceries`
- `todo call mom`
- `todo gym`



### `deadline` - Add a task with a due date

For assignments, bills, anything with a deadline.

**Format:** `deadline DESCRIPTION /by DD-MM-YYYY`

**Examples:**
- `deadline CS2103 iP /by 02-02-2026`
- `deadline pay rent /by 01-03-2026`



### `event` - Add an event with time

For meetings, classes, hangouts - anything with a start and end time.

**Format:** `event DESCRIPTION /from DD-MM-YYYY HHmm /to DD-MM-YYYY HHmm`

**Examples:**
- `event CS2103 lecture /from 17-02-2026 1400 /to 17-02-2026 1600`
- `event dinner with friends /from 20-02-2026 1900 /to 20-02-2026 2100`



### `mark` - Check off a task

Mark something as done when you finish it.

**Format:** `mark INDEX`

**Example:** `mark 1` (marks the first task)



### `unmark` - Undo a checkmark

Accidentally marked something? Unmark it.

**Format:** `unmark INDEX`

**Example:** `unmark 2`



### `delete` - Remove a task

Get rid of tasks you don't need anymore.

**Format:** `delete INDEX`

**Example:** `delete 3`



### `find` - Search your tasks

Find tasks by keyword.

**Format:** `find KEYWORD`

**Examples:**
- `find assignment` - shows all tasks with "assignment"
- `find meeting` - shows all tasks with "meeting"



### `due` - What's due on a specific day?

Check what you have on a particular date.

**Format:** `due DD-MM-YYYY`

**Example:** `due 15-02-2026`



### `remind` - What's coming up?

Shows everything due in the next 7 days. Good for weekly planning.

**Format:** `remind`



### `clear` - Delete completed tasks

Cleans up your list by removing everything you've already marked as done.

**Format:** `clear`



### `clear all` - Nuclear option

Deletes EVERYTHING. Use with caution.

**Format:** `clear all`



### `cheer` - Need motivation?

Gets you a random motivational quote when you're feeling down.

**Format:** `cheer`



### `bye` - Exit

Close the app.

**Format:** `bye`



## Other stuff

[!TIP]
**Auto-save:** Everything saves automatically. No save button needed.

[!TIP]
**Data file:** Your tasks are stored in `data/mickey.txt`. 

[!TIP]
**Dark mode:** The app switches to dark theme after 6pm automatically.


## Command Cheat Sheet

[!IMPORTANT]
| Command | What it does | Example |
|---------|-------------|---------|
| `list` | Show all tasks | `list` |
| `todo` | Add basic task | `todo study for midterm` |
| `deadline` | Add task with due date | `deadline essay /by 28-02-2026` |
| `event` | Add event with time | `event tutorial /from 19-02-2026 1000 /to 19-02-2026 1100` |
| `mark` | Check off task | `mark 1` |
| `unmark` | Undo checkmark | `unmark 2` |
| `delete` | Remove task | `delete 3` |
| `find` | Search tasks | `find project` |
| `due` | Tasks on specific date | `due 15-02-2026` |
| `remind` | Next 7 days | `remind` |
| `clear` | Delete completed | `clear` |
| `clear all` | Delete everything | `clear all` |
| `cheer` | Get motivated | `cheer` |
| `bye` | Exit | `bye` |