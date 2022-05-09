package gui.controller;

import be.Borger;
import be.Case;
import gui.model.CaseModel;
import gui.util.CaseDocumentationScene;
import gui.util.ISceneLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanlaegningController implements Initializable {

    private DashboardController dashboardController;
    private CaseModel caseModel;
    private Case selectCase;
    private Borger selectCitizen;

    @FXML
    private GridPane parentPane;
    @FXML
    private TextArea txtAreaPlanlaegning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectCase = dashboardController.getSelectedCase();
            selectCitizen = dashboardController.getSelectedCitizen();
            txtAreaPlanlaegning.setText(selectCase.getPlanProperty().get());
            caseModel = dashboardController.getCaseModel();
        });
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
        getStage().close();
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
        ISceneLoader<UdfoerelseIOgLeveringController> caseDocumentationScene = new CaseDocumentationScene();
        caseDocumentationScene.loadNewScene(getStage());
        UdfoerelseIOgLeveringController udfoerelseIOgLeveringController = caseDocumentationScene.getController();
        udfoerelseIOgLeveringController.setCaseDocumentationViewController(udfoerelseIOgLeveringController);
        udfoerelseIOgLeveringController.setCurrentCase(dashboardController.getSelectedCase());
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }
}
