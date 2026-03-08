package bob.gui;

import bob.Bob;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller class for main GUI window
 * Also handles user input and updates chat display
 */

public class MainWindow {
    @FXML
    private Button sendButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Bob bob;

    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image bobImage = new Image(getClass().getResourceAsStream("/images/DaBob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Bob instance into controller
     * @param b the Bob chatbot instance
     */
    public void setBob(Bob b) {
        bob = b;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(bob.getWelcomeMessage(), bobImage));

    }

    /**
     * Handle user input from text field
     *
     * Creates dialog box for
     * both bob and user response
     */

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input,userImage),
                DialogBox.getBotDialog(response, bobImage));

        userInput.clear();
        if (bob.isExit()) {
            Platform.exit();
        }
    }
}
