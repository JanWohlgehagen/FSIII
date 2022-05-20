package gui.util;

import gui.controller.AlleRelevanteHelbredstilstandeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelbredsTilstandOverviewScene implements ISceneLoader<AlleRelevanteHelbredstilstandeViewController> {
    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/AlleRelevanteHelbredstilstandeView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Learning Platform");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public AlleRelevanteHelbredstilstandeViewController getController() {
        return loader.getController();
    }
}
