package mickey;

import java.io.IOException;
import java.time.LocalTime;
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

    private DialogBox(String text, Image img) {
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

        // clip the image to make it circular
        Circle clip = new Circle(40, 40, 40);
        displayPicture.setClip(clip);

        applyThemeBasedOnTime(); // adjust colors for day/night mode
    }

    /**
     * Checks time and applies dark theme if it's after 6pm
     */
    private void applyThemeBasedOnTime() {
        LocalTime now = LocalTime.now();
        LocalTime themeChangeTime = LocalTime.of(18, 0);

        if (now.isAfter(themeChangeTime) || now.equals(themeChangeTime)) {
            this.getStyleClass().add("dark-theme");
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
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Changes bubble color based on what command was run
     * Green for adding tasks, purple for marking, red for deleting
     *
     * @param commandType like "todo", "mark", "delete" etc
     */
    private void setCommandStyle(String commandType) {
        if (commandType == null) {
            return;
        }
        switch (commandType) {
        case "todo":
        case "deadline":
        case "event":
            dialog.getStyleClass().add("add-label");
            break;
        case "mark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "delete":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // no special styling for other commands
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getMickeyDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setCommandStyle(commandType);
        return db;
    }

    public static DialogBox getMickeyDialog(String text, Image img) {
        return getMickeyDialog(text, img, null);
    }
}

