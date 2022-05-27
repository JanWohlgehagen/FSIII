package gui.util;

import gui.controller.circle.PlanningController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PlanlaegningScene implements ISceneLoader<PlanningController> {

    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    /**
     * Creates a new scene from an FXML file
     * @param stage
     * @throws IOException
     */
    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/PlanningView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Planlægning");
        stage.getIcons().add(image);
        stage.show();
    }
    /**
     * Provides the controller from the new scene
     * @return Controller
     */
    @Override
    public PlanningController getController() {
        return loader.getController();
    }
}
