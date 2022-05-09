package gui.controller;

import be.Case;
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

    private BestillingsViewController bestillingsViewController;

    private Case currentCase;
    private DashboardController dashBoardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            currentCase = dashBoardController.getSelectedCase();

            if (currentCase.isBevilgetProperty().get()) {
                checkBoxBevilling.setSelected(true);
            }
            txtAreaBestillingsText.setText(currentCase.getBevillingstekstProperty().get());
        });
    }

    public void handleSaveAndClose(MouseEvent mouseEvent) {
        currentCase.setIsBevilget(checkBoxBevilling.isSelected());
        currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
        getStage().close();
    }

    public void handleSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        currentCase.setIsBevilget(checkBoxBevilling.isSelected());
        currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
        ISceneLoader<PlanlaegningController> planlaegningScene = new PlanlaegningScene();
        planlaegningScene.loadNewScene(getStage());
        PlanlaegningController planlaegningController = planlaegningScene.getController();
        planlaegningController.setDashboardController(dashBoardController);
        //TODO - det her skal ned i databasen
    }

    public void setBestillingsViewController(BestillingsViewController bestillingsViewController) {
        this.bestillingsViewController = bestillingsViewController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashBoardController = dashboardController;
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }
}
