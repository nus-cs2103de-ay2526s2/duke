package seedu.star;
import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.shape.Circle;
/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Gets the user dialog box.
     * @param text The text to display.
     * @param img The user's image.
     * @return A DialogBox representing the user's message.
     */
    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Circular clip for profile pictures (Optimizes space and improves UX)
        Circle clip = new Circle(20, 20, 20); // Center X, Center Y, Radius
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(40); // Downsize the image
        displayPicture.setFitHeight(40);

        // Assign CSS classes based on who is speaking
        dialog.getStyleClass().add("chat-bubble");
        if (isUser) {
            dialog.getStyleClass().add("user-bubble");
        } else {
            dialog.getStyleClass().add("star-bubble");
            if (isError) {
                dialog.getStyleClass().add("error-bubble"); // Highlight errors
            }
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    public static DialogBox getStarDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img, false, isError);
        db.flip();
        return db;
    }
}