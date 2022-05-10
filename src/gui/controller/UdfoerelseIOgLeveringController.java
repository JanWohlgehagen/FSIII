package gui.controller;

import be.Case;
import gui.util.ISceneLoader;
import gui.util.OpfoelgningScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UdfoerelseIOgLeveringController {
    @FXML
    private GridPane parentPane;
    @FXML
    private ScrollPane scrollpaneDocumentations;
    @FXML
    private TextField txtTitel;
    @FXML
    private TextArea txtAreaDok;

    private UdfoerelseIOgLeveringController udfoerelseIOgLeveringController;
    private Case currentCase;
    private DashboardController dashboardController;

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void btnNewDocumentation(ActionEvent actionEvent) {
        /// TODO: 09/05/2022
    }

    public void setCaseDocumentationViewController(UdfoerelseIOgLeveringController udfoerelseIOgLeveringController) {
        this.udfoerelseIOgLeveringController = udfoerelseIOgLeveringController;
    }
    public void setCurrentCase(Case currentCase)
    {
        this.currentCase = currentCase;
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        // TODO: 5/9/2022  
        getStage().close();
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        ISceneLoader<OpfoelgningController> opfoelgningScene = new OpfoelgningScene();
        opfoelgningScene.loadNewScene(getStage());
        OpfoelgningController opfoelgningController = opfoelgningScene.getController();
        opfoelgningController.setDashboardController(dashboardController);
        opfoelgningController.setCaseModel(dashboardController.getCaseModel());
        /// TODO: 09/05/2022
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }
}
