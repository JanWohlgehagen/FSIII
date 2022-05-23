package gui.util;

import gui.controller.create_edit.CreateAndEditClassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CreateAndEditClassScene implements ISceneLoader<CreateAndEditClassController> {

    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/CreateAndEditClassView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Opret og ændre klassen");
        stage.getIcons().add(image);
        stage.show();
    }

    @Override
    public CreateAndEditClassController getController() {
        return loader.getController();
    }
}
