package gui.util;

import gui.controller.BestillingsViewController;
import gui.controller.CaseDocumentationViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CaseDocumentationScene implements ISceneLoader<CaseDocumentationViewController> {
    private final Image image = new Image("gui/resources/images/logo.png");
    private static FXMLLoader loader;

    @Override
    public void loadNewScene(Stage stage) throws IOException {
        loader = new FXMLLoader(new File("src/gui/view/CaseDocumentationView.fxml").toURI().toURL());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("FSIII Learning Platform");
        stage.getIcons().add(image);
        stage.show();
    }

    @Override
    public CaseDocumentationViewController getController() {
        {
            return loader.getController();
        }
    }
}
