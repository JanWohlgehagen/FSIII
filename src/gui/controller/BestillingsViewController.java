package gui.controller;

import be.Borger;
import be.Case;
import be.user.User;
import gui.model.CaseModel;
import gui.util.ISceneLoader;
import gui.util.PlanlaegningScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BestillingsViewController implements Initializable {

    @FXML
    private GridPane parentPane;
    @FXML
    private TextArea txtAreaBestillingsText;
    @FXML
    private CheckBox checkBoxBevilling;

    private Case currentCase;
    private User loginUser;
    private DashboardController dashBoardController;
    private CaseModel caseModel;
    private Borger currentCitizen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            currentCase = dashBoardController.getSelectedCase();
            loginUser = dashBoardController.getLoginUser();
            setBestillingsViewToLoginUserProfile();

            if (currentCase.isBevilgetProperty().get()) {
                checkBoxBevilling.setSelected(true);
            }
            txtAreaBestillingsText.setText(currentCase.getBevillingstekstProperty().get());
        });
    }

    public void handleSaveAndClose(MouseEvent mouseEvent) {
        if(checkBoxBevilling.isSelected()){
        currentCase.setIsBevilget(checkBoxBevilling.isSelected());
        currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
        }
        else
        {
            caseModel.deleteCaseOnCitizen(currentCitizen.getIDProperty().get(), currentCase.getCaseIDProperty().get());
        }

        getStage().close();
    }

    public void handleSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        if(checkBoxBevilling.isSelected()){
            currentCase.setIsBevilget(checkBoxBevilling.isSelected());
            currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
            currentCase.setIsBevilget(checkBoxBevilling.isSelected());
            currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
            ISceneLoader<PlanlaegningController> planlaegningScene = new PlanlaegningScene();
            planlaegningScene.loadNewScene(getStage());
            PlanlaegningController planlaegningController = planlaegningScene.getController();
            planlaegningController.setDashboardController(dashBoardController);
        }
        else
        {
            caseModel.deleteCaseOnCitizen(currentCitizen.getIDProperty().get(), currentCase.getCaseIDProperty().get());
            getStage().close();
        }


    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashBoardController = dashboardController;
    }

    public void setCurrentCitizen(Borger currentCitizen) {
        this.currentCitizen = currentCitizen;
    }

    public void setCaseModel(CaseModel caseModel) {
        this.caseModel = caseModel;
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }

    private void setBestillingsViewToLoginUserProfile(){
        if (this.loginUser != null) {
            switch (this.loginUser.getUserType()) {
                case STUDENT -> {
                    txtAreaBestillingsText.setEditable(false);
                    checkBoxBevilling.setDisable(true);
                }
                case TEACHER -> {
                    txtAreaBestillingsText.setEditable(true);
                    checkBoxBevilling.setDisable(false);
                }

                default -> throw new UnsupportedOperationException();
            }
        }
    }
}
