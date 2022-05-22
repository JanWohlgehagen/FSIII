package gui.controller;

import be.*;
import gui.model.CitizenModel;
import gui.util.ISceneLoader;
import gui.util.OpfoelgningScene;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class UdfoerelseIOgLeveringController implements Initializable {

    @FXML
    private ListView<Observation> ListViewObservations;
    @FXML
    private GridPane parentPane;
    @FXML
    private TextArea txtAreaDok;

    private UdfoerelseIOgLeveringController udfoerelseIOgLeveringController;
    private Borger selectedCitizen;
    private CitizenModel citizenModel;
    private DashboardController dashboardController;
    private ObservableList<Observation> observationList;
    private Observation observation;

    public void setObservationList(ObservableList<Observation> observationList) {
        this.observationList = observationList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectedCitizen = dashboardController.getSelectedCitizen();
            citizenModel.setTilstandeOnCitizen(selectedCitizen);
            selectedCitizen.getObservations().clear();
            if (selectedCitizen.getObservations().isEmpty()) {
                for (String key : selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().keySet())
                    for (FunktionstilstandsUnderkategori fuk : selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                        if (fuk.getObservation().getTime() != null && fuk.getObservation().getDescriptionProperty().get() != null) {
                            selectedCitizen.getObservations().add(fuk.getObservation());
                        }
                    }
                for (String key : selectedCitizen.getHelbredstilstand().getHelbredsTilstandsKort().keySet())
                    for (HelbredstilstandsUnderkategori huk : selectedCitizen.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                        if (huk.getObservation().getTime() != null && huk.getObservation().getDescriptionProperty().get() != null) {
                            selectedCitizen.getObservations().add(huk.getObservation());
                        }
                    }
            }
            ListViewObservations.setItems(selectedCitizen.getObservations());

            ListViewObservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
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
            ISceneLoader<OpfoelgningController> opfoelgningScene = new OpfoelgningScene();
            opfoelgningScene.loadNewScene(getStage());
            OpfoelgningController opfoelgningController = opfoelgningScene.getController();
            opfoelgningController.setDashboardController(dashboardController);
            opfoelgningController.setCaseModel(dashboardController.getCaseModel());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    private Stage getStage() {
        return (Stage) parentPane.getScene().getWindow();
    }

    public void handleMouseReleasedDokumentationArea(MouseEvent mouseEvent) {
        txtAreaDok.setText(observation.getDescriptionProperty().get());
    }
}
