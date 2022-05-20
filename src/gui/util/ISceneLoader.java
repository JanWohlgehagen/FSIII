package gui.util;

import javafx.stage.Stage;

import java.io.IOException;

public interface ISceneLoader<T> {
    void loadNewScene(Stage stage) throws IOException;

    T getController();
}
