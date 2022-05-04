package gui.util;

import gui.controller.OpfoelgningController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OpfoelgningScene implements ISceneLoader<OpfoelgningController> {

    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/OpfoelgningView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Opfølgning");
        stage.getIcons().add(image);
        stage.show();
    }

    @Override
    public OpfoelgningController getController() {
        return loader.getController();
    }
}
