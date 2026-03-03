package duchess.ui.components;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialogue box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox class.
     * @param text the text to be displayed in the dialogue box
     * @param image the image to be displayed in the dialogue box
     */
    private DialogueBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialogue box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialogue.getStyleClass().add("reply-label");
    }

    /**
     * Returns a DialogBox from the user's perspective.
     * @param text the text to be displayed in the dialogue box
     * @param image the image to be displayed in the dialogue box
     * @return a DialogBox from the user's perspective
     */
    public static DialogueBox getUserDialog(String text, Image image) {
        return new DialogueBox(text, image);
    }

    /**
     * Returns a DialogBox from Duchess's perspective.
     * @param text the text to be displayed in the dialogue box
     * @param image the image to be displayed in the dialogue box
     * @return a DialogBox from Duchess's perspective
     */
    public static DialogueBox getDuchessDialog(String text, Image image) {
        DialogueBox dialogueBox = new DialogueBox(text, image);
        dialogueBox.flip();
        return dialogueBox;
    }
}
