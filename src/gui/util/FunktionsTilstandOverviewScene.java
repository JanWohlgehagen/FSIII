package gui.util;

import gui.controller.AlleRelevanteOplysningerViewController;
import gui.controller.CreateCitizenViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FunktionsTilstandOverviewScene implements ISceneLoader<AlleRelevanteOplysningerViewController> {

    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/AlleRelevanteFunktionstilstandeView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Learning Platform");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public AlleRelevanteOplysningerViewController getController() {
        return loader.getController();
    }
}