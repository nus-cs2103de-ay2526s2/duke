import chatterbox.BotResponse;
import chatterbox.ChatterBotCore;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
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

    private ChatterBotCore bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/usagi.jpg"));

    @FXML
    public void initialize() {
        // Auto-scroll to bottom when new messages are added, without blocking manual scrolling
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    /**
     * Injects the ChatterBotCore instance and shows a welcome message.
     *
     * @param bot Core logic handler.
     */
    public void setBot(ChatterBotCore bot) {
        this.bot = bot;
        showWelcomeMessage();
    }

    /**
     * Displays the chatbot's welcome message on startup.
     */
    private void showWelcomeMessage() {
        String welcome = "Welcome to ChatterBox! We are an interactive task tracker."
                + " Enter the command \"help\" for a list of commands.";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, botImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            userInput.clear();
            return;
        }

        BotResponse response = bot.getResponse(input);

        DialogBox botDialog;
        if (response.isError()) {
            botDialog = DialogBox.getErrorDialog(response.getMessage(), botImage);
        } else {
            botDialog = DialogBox.getDukeDialog(response.getMessage(), botImage);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                botDialog
        );
        userInput.clear();

        if (bot.shouldExit()) {
            Platform.exit();
        }
    }
}