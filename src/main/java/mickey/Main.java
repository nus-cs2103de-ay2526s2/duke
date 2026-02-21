package mickey;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Mickey using FXML.
 */
public class Main extends Application {

    private Mickey mickey = new Mickey("./data/mickey.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mickey Chatbot");
            stage.setMinHeight(620);
            stage.setMinWidth(417);

            // pass Mickey instance to the controller
            fxmlLoader.<MainWindow>getController().setMickey(mickey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
