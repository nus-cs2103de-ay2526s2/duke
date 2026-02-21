package mickey;

import java.time.LocalTime;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer; // holds all the chat messages
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private AnchorPane mainLayout;

    private Mickey mickey;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/penguin.png"));
    private Image mickeyImage = new Image(this.getClass().getResourceAsStream("/images/mickey.png"));

    /**
     * Initialises the controller and displays welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // auto-scroll to bottom
        applyThemeBasedOnTime();
        String welcome = "Hey there! I'm Mickey\n( o.o )\n  >^<\n\n Let's do this!";
        dialogContainer.getChildren().add(DialogBox.getMickeyDialog(welcome, mickeyImage));
    }

    /**
     * Switches between light and dark theme based on time
     * Light theme before 6pm, dark theme after 6pm
     */
    private void applyThemeBasedOnTime() {
        LocalTime now = LocalTime.now();
        LocalTime themeChangeTime = LocalTime.of(18, 0); // 6PM

        if (now.isAfter(themeChangeTime) || now.equals(themeChangeTime)) {
            this.getStyleClass().add("dark-theme"); // evening mode
        } else {
            this.getStyleClass().remove("dark-theme"); // day mode
        }
    }

    /**
     * Injects the Mickey instance
     */
    public void setMickey(Mickey m) {
        mickey = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Close the application if "bye" command is entered.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = mickey.getResponse(input);
        String commandType = mickey.getLastCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMickeyDialog(response, mickeyImage, commandType)
        );
        userInput.clear();

        // Close application if user says bye
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
