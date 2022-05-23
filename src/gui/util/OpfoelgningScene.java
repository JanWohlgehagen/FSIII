package gui.util;

import gui.controller.circle.FollowUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OpfoelgningScene implements ISceneLoader<FollowUpController> {

    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/FollowUpView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Opfølgning");
        stage.getIcons().add(image);
        stage.show();
    }

    @Override
    public FollowUpController getController() {
        return loader.getController();
    }
}
