package listo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A custom control using JavaFX.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param l The text to display in the dialog box.
     * @param iv The image to display representing the speaker.
     */
    public DialogBox(String l, Image iv, String hexColor) {
        Label text = new Label(l);
        ImageView displayPicture = new ImageView(iv);

        text.setWrapText(true);
        text.setStyle("-fx-background-color: " + hexColor + "; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 12; " +
                "-fx-text-fill: #424242; " +
                "-fx-font-family: 'Verdana'; " +
                "-fx-font-size: 13px;");

        double imageSize = 50.0;
        displayPicture.setFitWidth(imageSize);
        displayPicture.setFitHeight(imageSize);
        Circle clip = new Circle(imageSize / 2, imageSize / 2, imageSize / 2);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox object representing the user's input.
     * The image is positioned on the right side.
     *
     * @param l The user's text message.
     * @param iv The user's profile image.
     * @return A DialogBox containing the user's text and image.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv, "#E8F5E9");
    }

    /**
     * Returns a DialogBox object representing the bot's response.
     * The image is positioned on the left side.
     *
     * @param l The bot's text response.
     * @param iv The bot's profile image.
     * @return A DialogBox containing the bot's text and image, flipped.
     */
    public static DialogBox getListoDialog(String l, Image iv) {
        var db = new DialogBox(l, iv, "#E1F5FE");
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox object representing an error message.
     * The image is positioned on the left side (like the bot), but with a red style.
     *
     * @param l The error text.
     * @param iv The bot's profile image.
     * @return A DialogBox containing the error text and image.
     */
    public static DialogBox getErrorDialog(String l, Image iv) {
        var db = new DialogBox(l, iv, "#FFCDD2");
        db.flip();
        return db;
    }
}