package gui.controller.circle;

import be.*;
import gui.controller.DashboardController;
import gui.model.CitizenModel;
import gui.util.ISceneLoader;
import gui.util.OpfoelgningScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExecutionAndDeliveryController implements Initializable {

    @FXML
    private ListView<Observation> listViewObservations;
    @FXML
    private GridPane parentPane;
    @FXML
    private TextArea txtAreaDok;
    private Citizen selectedCitizen;
    private CitizenModel citizenModel;
    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectedCitizen = dashboardController.getSelectedCitizen();
            citizenModel.setTilstandeOnCitizen(selectedCitizen);
            selectedCitizen.getObservations().clear();
            if (selectedCitizen.getObservations().isEmpty()) {
                for (String key : selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().keySet())
                    for (FunktionstilstandsUnderkategori fuk : selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                        if (fuk.getObservation().getDescriptionProperty().get() != null) {
                            selectedCitizen.getObservations().add(fuk.getObservation());
                        }
                    }
                for (String key : selectedCitizen.getHelbredstilstand().getHelbredsTilstandsKort().keySet())
                    for (HelbredstilstandsUnderkategori huk : selectedCitizen.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                        if (huk.getObservation().getDescriptionProperty().get() != null) {
                            selectedCitizen.getObservations().add(huk.getObservation());
                        }
                    }
            }
            listViewObservations.setItems(selectedCitizen.getObservations());

            listViewObservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            {
                if (newValue != null) {
                    txtAreaDok.setText(newValue.getDescriptionProperty().get());
                }
            });
        });
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }

    public void btnNewDocumentation(ActionEvent actionEvent) {
        /// TODO: 09/05/2022
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        // TODO: 5/9/2022  
        getStage().close();
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            ISceneLoader<FollowUpController> opfoelgningScene = new OpfoelgningScene();
            opfoelgningScene.loadNewScene(getStage());
            FollowUpController followUpController = opfoelgningScene.getController();
            followUpController.setDashboardController(dashboardController);
            followUpController.setCaseModel(dashboardController.getCaseModel());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal v??lge en sag f??rst.", ButtonType.OK);
            alert.show();
        }
    }

    private Stage getStage() {
        return (Stage) parentPane.getScene().getWindow();
    }

    public void handleMouseReleasedDescriptionArea(MouseEvent mouseEvent) {
        txtAreaDok.setText(listViewObservations.getSelectionModel().getSelectedItem().getDescriptionProperty().get());
    }
}
