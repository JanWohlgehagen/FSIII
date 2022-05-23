package gui.util;

import gui.controller.circle.CaseOpeningController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CaseOpeningScene implements ISceneLoader<CaseOpeningController> {

    private static FXMLLoader loader;
    private final Image image = new Image("gui/resources/images/logo.png");

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/CaseOpeningView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Sagsåbning");
        stage.getIcons().add(image);
        stage.show();
    }

    @Override
    public CaseOpeningController getController() {
        return loader.getController();
    }
}
