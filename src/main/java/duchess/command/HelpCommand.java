package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to display help information.
 */
public class HelpCommand extends Command {

    /**
     * Displays help information.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     *
     * @return help message to be displayed to the user
     */
    public String execute(TaskList tasks, Storage storage) {
        return """
                Hark, attend to the commands at thy disposal:\n
                1. todo NAME
                - doth forge a task.\n
                2. deadline NAME /by DATE
                - doth set a task with a term.\n
                3. event NAME /from START_DATE /to END_DATE
                - doth frame a task 'twixt two suns.\n
                4. delete INDEX
                - doth strike down a task by its given count.\n
                5. list
                - doth show forth all tasks in thy ledger.\n
                6. cheer
                - doth speak a random word of courage.\n
                7. outstanding DATE
                - doth reveal tasks yet to be done by the set day.\n
                8. find KEYWORD
                - doth seek tasks akin to the given word.\n
                9. mark INDEX
                - doth deem a task done by its count.\n
                10. unmark INDEX
                - doth call back a task to be done.\n
                11. bye
                - doth bid farewell to this program.\n
                For deeper lore, seek the README.md.""";
    }
}
