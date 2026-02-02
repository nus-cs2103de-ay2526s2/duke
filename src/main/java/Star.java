import ui.Ui;
import logic.LogicController;
import parser.Message;
public class Star {
    public static void main(String[] args) {
        Ui.printWelcomeMessage();   // Prints welcome message in UI

        while (true) {  // Infinite loop
            if (!Ui.hasMoreInput()) {
                break;  // EOF → exit
            }

            // Ignores empty input
            String input = Ui.scanInput();
            if (input.isEmpty() || input.trim().isEmpty()) {
                continue;
            }

            Message message = new Message(input);
            message.parseMessage();

            // exit ONLY for "bye"
            if (LogicController.run(message)) {
                break;
            }
        }
    }
}