package bob;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Javafx entry for Bob GUI
 */
public class Main extends Application {

    private final Bob bob = new Bob("./data/bob.txt", true);
    /**
     * Initialises and displays GUI window
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            loader.<bob.gui.MainWindow>getController().setBob(bob);
            stage.setTitle("Bob");
            stage.setResizable(false);


            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
        }
    }

