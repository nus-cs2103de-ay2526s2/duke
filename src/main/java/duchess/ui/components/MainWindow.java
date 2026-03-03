package duchess.ui.components;

import duchess.ui.Duchess;
import duchess.ui.Response;
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
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duchess duchess;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/duchess.jpeg"));

    /**
     * Initializes the controller and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        showWelcomeMessage();
    }

    /** Injects the Duchess instance */
    public void setDuchess(Duchess duchess) {
        this.duchess = duchess;
    }

    /**
     * Creates two dialogue boxes, one echoing user input and the other containing Duchess's reply and then appends them
     * to the dialogue container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duchess.getResponse(input);
        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialog(input, userImage),
                DialogueBox.getDuchessDialog(response.commandOutput(), duchessImage)
        );

        if (response.shouldTerminate()) {
            exitWithDelay();
        }

        userInput.clear();
    }

    /**
     * Shows a welcome message to the user.
     */
    private void showWelcomeMessage() {
        dialogueContainer.getChildren().addAll(
                DialogueBox.getDuchessDialog("Hark, I be Duchess! What service dost thou require of me?", duchessImage)
        );
    }

    /**
     * Exits the application with a delay.
     */
    private void exitWithDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
