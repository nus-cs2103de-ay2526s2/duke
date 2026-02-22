package chatbox.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Controller for the main GUI of the ZhengjunChatbox.
 * Provides the layout for the other controls and manages the interaction between
 * the user interface and the chatbox logic.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ZhengjunChatbox chatbox;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image zhengjunImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Sets the chatbox logic instance and triggers the initial welcome message.
     *
     * @param d The ZhengjunChatbox logic instance to be used by the GUI.
     */


    public void setZhengjun(ZhengjunChatbox d) {
        chatbox = d;
        // This makes the bot say Hello immediately!
        dialogContainer.getChildren().add(
                DialogBox.getZhengjunDialog(chatbox.getWelcome(), zhengjunImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbox.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZhengjunDialog(response, zhengjunImage)
        );
        userInput.clear();
        //close the webpage when bye command is given
        if (input.trim().equalsIgnoreCase("bye")) {
            // Create a 1.5 second delay so the user can read the message
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}