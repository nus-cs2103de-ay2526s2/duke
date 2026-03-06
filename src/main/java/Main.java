import java.io.IOException;

import chatterbox.ChatterBotCore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ChatterBot using FXML.
 */
public class Main extends Application {

    private final ChatterBotCore bot = new ChatterBotCore();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChatterBox");
            stage.setMinWidth(300);
            stage.setMinHeight(400);

            fxmlLoader.<MainWindow>getController().setBot(bot); // inject core
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}