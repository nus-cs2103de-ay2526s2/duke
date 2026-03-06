package listo;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The entry point of the application.
     * Delegates to the Listo class to start the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Listo.class, args);
    }
}