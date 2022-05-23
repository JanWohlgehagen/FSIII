package gui.controller.circle;

import be.Citizen;
import be.Case;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.controller.DashboardController;
import gui.model.CaseModel;
import gui.model.CitizenModel;
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

public class PlanningController implements Initializable {

    private DashboardController dashboardController;
    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private Case selectCase;
    private Citizen selectCitizen;

    @FXML
    private GridPane parentPane;
    @FXML
    private TextArea txtAreaPlanlaegning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectCase = dashboardController.getSelectedCase();
            try {
                citizenModel = new CitizenModel(new ManagerFacade(new DatabaseFacade()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectCitizen = dashboardController.getSelectedCitizen();
            txtAreaPlanlaegning.setText(selectCase.getPlanProperty().get());
            caseModel = dashboardController.getCaseModel();
        });
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        selectCase.setPlan(txtAreaPlanlaegning.getText());
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
        getStage().close();
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        selectCase.setPlan(txtAreaPlanlaegning.getText());
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
        ISceneLoader<ExecutionAndDeliveryController> caseDocumentationScene = new CaseDocumentationScene();
        caseDocumentationScene.loadNewScene(getStage());
        ExecutionAndDeliveryController executionAndDeliveryController = caseDocumentationScene.getController();
        executionAndDeliveryController.setDashboardController(dashboardController);
        executionAndDeliveryController.setCitizenModel(citizenModel);
    }

    private Stage getStage() {
        return (Stage) parentPane.getScene().getWindow();
    }
}
