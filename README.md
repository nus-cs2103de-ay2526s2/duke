# Listo

Listo is a personal assistant chatbot designed to help you manage your tasks efficiently. It supports tracking Todos, Deadlines, and Events, all wrapped in a friendly and interactive GUI.

📖 **[Click here to read the full Listo User Guide](docs/UserGuide.md)**

## Features

* **GUI Interface:** A clean, chat-like interface built with JavaFX.
* **Task Management:** Add, delete, and mark tasks as done.
* **Search & Filter:** Find tasks by keyword (case-insensitive) or filter deadlines/events by specific dates.
* **Data Persistence:** Automatically saves your tasks to a local file.
* **Duplicate Detection:** Prevents adding the same task twice.
* **Smart Parsing:** Handles flexible date formats and validates inputs.
* **Motivation:** Type `cheer` to get a quick motivational boost from bundled custom quotes!

## Setting up in IntelliJ

Prerequisites: JDK 21.

1.  Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first).
2.  Open the project into IntelliJ as follows:
    1.  Click `Open`.
    2.  Select the project directory, and click `OK`.
    3.  If there are any further prompts, accept the defaults.
3.  Configure the project to use **JDK 21** (not other versions) as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
    In the same dialog, set the **Project language level** field to the `SDK default` option.
4.  Locate the `src/main/java/listo/Launcher.java` file, right-click it, and choose `Run 'Launcher.main()'`.

**Warning:** Keep the `src/main/java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Running Tests

The project includes JUnit tests for automated testing.

* **Windows/Mac/Linux:**
  Open the terminal in IntelliJ and run:
    ```bash
    ./gradlew test
    ```