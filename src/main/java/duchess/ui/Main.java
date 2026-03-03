package duchess.ui;

import java.io.IOException;

import duchess.ui.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The GUI for Duchess using FXML.
 */
public class Main extends Application {
    private final Duchess duchess = new Duchess();

    /**
     * Starts the GUI.
     * @param stage the stage to display the GUI on
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            stage.setTitle("Duchess");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuchess(duchess); // inject the Duchess instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
