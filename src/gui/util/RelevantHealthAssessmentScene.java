package gui.util;

import gui.controller.RelevantHealthAssessmentViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RelevantHealthAssessmentScene implements ISceneLoader<RelevantHealthAssessmentViewController> {
    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    /**
     * Creates a new scene from an FXML file
     * @param stage
     * @throws IOException
     */
    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/RelevantHealthAssessmentView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Learning Platform");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();
    }
    /**
     * Provides the controller from the new scene
     * @return Controller
     */
    @Override
    public RelevantHealthAssessmentViewController getController() {
        return loader.getController();
    }
}
