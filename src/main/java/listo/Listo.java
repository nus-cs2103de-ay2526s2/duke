package listo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import listo.exception.ListoException;
import listo.parser.Parser;
import listo.storage.Storage;
import listo.task.TaskList;
import listo.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The Main class for the GUI application.
 * It acts as the controller for the main GUI, handling user interaction and updating the view.
 */
public class Listo extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private Image user;
    private Image listo;

    /**
     * Constructs a new Listo application instance.
     * Initializes the UI, Storage, and TaskList components.
     * Also attempts to load the profile images for the user and the bot.
     */
    public Listo() {
        String filePath = "./data/listo.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks.");
            tasks = new TaskList();
        }

        try {
            if (this.getClass().getResourceAsStream("/images/User.png") != null) {
                user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
            }
            if (this.getClass().getResourceAsStream("/images/Listo.png") != null) {
                listo = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Listo.png")));
            }
        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }

    /**
     * Starts the JavaFX application stage.
     * Sets up the primary window, layout, and event listeners.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setTitle("Listo");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        dialogContainer.setStyle("-fx-background-color: #ECE5DD;");
        dialogContainer.setSpacing(10);
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());

        scrollPane.setStyle("-fx-background: #ECE5DD; -fx-background-color: #ECE5DD;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);

        // ScrollPane takes up everything except the bottom 50px
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        // Send Button is stuck to the bottom right
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);
        sendButton.setPrefWidth(55.0);

        // User Input is stuck to bottom left, and stretches to meet the button
        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);
        AnchorPane.setRightAnchor(userInput, 75.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren().add(
                DialogBox.getListoDialog("Hello! I'm Listo, your Personal Assistant Chatbot :) " +
                        "What's the plan for today? Let's get things done!\n" +
                        "\nType \"help\" to see the list of commands I understand :)", listo)
        );
    }

    /**
     * Handles the user input event.
     * Reads the text from the text field, generates a response, and updates the dialog container.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, user));

        if (response.startsWith("OOPS!!!")) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, listo));
        } else {
            dialogContainer.getChildren().add(DialogBox.getListoDialog(response, listo));
        }

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }

    /**
     * Generates a response for the user's chat message.
     * It redirects the system output to capture the bot's response.
     *
     * @param input The raw input string from the user.
     * @return The response string to be displayed by the bot.
     */
    private String getResponse(String input) {
        if (input.trim().equalsIgnoreCase("bye")) {
            return "Goodbye! Hope to see you again soon :)";
        }

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Parser.parseCommand(input, tasks, ui);
            storage.save(tasks);
        } catch (ListoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        return outContent.toString().trim();
    }

    /**
     * The standard main method.
     * Delegates to the Launcher class to avoid classpath issues.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}