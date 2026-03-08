# Bob User Guide

## Introduction
Bob is a task management chatbot with a GUI. It helps users manage tasks such as todo, deadline and event.

## Using Bob
Bob can be used in 2 ways.
1. **GUI**
2. **CLI**

## GUI Mode
This provides a graphical user interface

### Launching the GUI

0. (Recommended)
1. Download the bob.jar file. 
2. Open a terminal.
3. Run the following command:

````bash
java -jar bob.jar
````

4. Or if downloading from repository

5. Run:

````bash
java ./gradlew clean shadowJar
````

````bash
java -jar build/libs/bob.jar
````

## CLI Mode
This provides a comand line user interface

### Launching the CLI

1. From the dropdown of intelliJ run Bob directly
2. Or
3. From the root Run:

````bash
java -cp build/classes/java/main bob.Bob
````

## Features

### View tasks
`list`
Shows all tasks.

### Add a todo
`todo <description>`
Example: `todo buy book`

### Add a deadline
`deadline <description> /by <yyyy-mm-dd>`
Example: `deadline submit assignment  /by 2026-04-11`

### Add an event
`event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
Example: `group meeting /from 2026-03-10 /to 2026-03-10`

### Mark task
`mark <task number>`

### Unmark task
`unmark <task number>`

### Delete task
`delete <task number>`

### Find tasks
`find <keyword>`

### Help 
`help`

### Exit
`bye`
