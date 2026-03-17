package orange;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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
    @FXML
    private Button todoButton;
    @FXML
    private Button deadlineButton;
    @FXML
    private Button eventButton;

    private Orange orange;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image orangeImage = new Image(this.getClass().getResourceAsStream("/images/DaOrange.png"));

    @FXML
    public void initialize() {
        // Bind scroll to always show latest messages
        //scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show welcome message with orange theme
        String welcomeMessage = "🍊 Hello! I'm Orange!\n\n" +
                "Your friendly task manager is ready to help.\n" +
                "What would you like to do today? 😊\n\n" +
                "💡 Tip: Click the quick action buttons below for templates!";

        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog(welcomeMessage, orangeImage)
        );
    }

    public void setOrange(Orange o) {
        orange = o;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = orange.getResponse(input);

        // Add user message
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // Add Orange response (with error styling if needed)
        if (response.startsWith("Error:") || response.contains("Oops!") || response.contains("⚠")) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(response, orangeImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getOrangeDialog(response, orangeImage)
            );
        }

        userInput.clear();
        scrollPane.setVvalue(1.0);
    }

    /**
     * Handles Todo button click - inserts todo template.
     */
    @FXML
    private void handleTodoButton() {
        userInput.setText("todo ");
        userInput.requestFocus();
        userInput.positionCaret(userInput.getText().length());

        // Show helpful hint
        String hint = "📝 Todo template ready!\n\n" +
                "Format: todo [description]\n" +
                "Example: todo read book";
        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog(hint, orangeImage)
        );
        scrollPane.setVvalue(1.0);
    }

    /**
     * Handles Deadline button click - inserts deadline template.
     */
    @FXML
    private void handleDeadlineButton() {
        userInput.setText("deadline ");
        userInput.requestFocus();
        userInput.positionCaret(userInput.getText().length());

        // Show helpful hint
        String hint = "⏰ Deadline template ready!\n\n" +
                "Format: deadline [description] /by YYYY-MM-DD\n" +
                "Example: deadline return book /by 2025-03-15";
        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog(hint, orangeImage)
        );
        scrollPane.setVvalue(1.0);
    }

    /**
     * Handles Event button click - inserts event template.
     */
    @FXML
    private void handleEventButton() {
        userInput.setText("event ");
        userInput.requestFocus();
        userInput.positionCaret(userInput.getText().length());

        // Show helpful hint
        String hint = "📅 Event template ready!\n\n" +
                "Format: event [description] /from YYYY-MM-DD /to YYYY-MM-DD\n" +
                "Example: event project meeting /from 2025-03-10 /to 2025-03-12";
        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog(hint, orangeImage)
        );
        scrollPane.setVvalue(1.0);
    }
}