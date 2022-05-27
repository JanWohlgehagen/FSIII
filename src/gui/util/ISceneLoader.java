package gui.util;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * This interface takes a controller
 * @param <T>
 */
public interface ISceneLoader<T> {

    /**
     * Creates a new scene from an FXML file
     * @param stage
     * @throws IOException
     */

    void loadNewScene(Stage stage) throws IOException;

    /**
     * Provides the controller from the new scene
     * @return Controller
     */
    T getController();
}
