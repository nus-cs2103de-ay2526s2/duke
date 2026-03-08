package dill.userinterface.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

        Circle pictureCircle = new Circle(49.5);
        pictureCircle.setFill(new ImagePattern(img));

        pictureCircle.setStroke(Color.WHITE); // Set border color
        pictureCircle.setStrokeWidth(2.0); // Set border thickness

        DropShadow pictureShadow = new DropShadow();
        pictureShadow.setRadius(12.0);
        pictureShadow.setOffsetY(4.0);
        pictureShadow.setColor(Color.color(0, 0, 0, 0.3));
        pictureCircle.setEffect(pictureShadow);

        int imageIndex = this.getChildren().indexOf(displayPicture);
        if (imageIndex != -1) {
            this.getChildren().set(imageIndex, pictureCircle);
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
        DialogBox db = new DialogBox(text, img);

        db.dialog.setStyle("-fx-background-color: #6A5ACD; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10 15 10 15;" // Top, Right, Bottom, Left
                + "-fx-text-fill: #FFFFFF;"
                + "-fx-font-family: 'Verdana';"
                + "-fx-font-size: 12px;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);");
        return db;
    }

    public static DialogBox getDillDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();

        db.dialog.setStyle("-fx-background-color: #FFFFFF; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10 15 10 15;" // Top, Right, Bottom, Left
                + "-fx-font-family: 'Verdana';"
                + "-fx-font-size: 12px;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);");
        return db;
    }

    public static DialogBox getDillErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();

        db.dialog.setStyle("-fx-background-color: #FFD2D2; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10 15 10 15;" // Top, Right, Bottom, Left
                + "-fx-font-family: 'Verdana';"
                + "-fx-font-size: 12px;"
                + "-fx-text-fill: #900000;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(144,0,0,0.2), 10, 0, 0, 4);");
        return db;
    }
}
