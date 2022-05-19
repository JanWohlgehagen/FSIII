package gui.controller;

import be.Borger;
import be.Case;
import be.FunktionstilstandsUnderkategori;
import be.Observation;
import gui.model.CitizenModel;
import gui.util.ISceneLoader;
import gui.util.OpfoelgningScene;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
            citizenModel.getTilstande(selectedCitizen);
            if(selectedCitizen.getObservationer().isEmpty()){
                for (String key: selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().keySet())
                    for (FunktionstilstandsUnderkategori fuk: selectedCitizen.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                        if (fuk.getObservation().getTidspunkt() != null && fuk.getObservation().getDescriptionProperty().get() != null ) {
                            selectedCitizen.getObservationer().add(fuk.getObservation());
                        }
                    }
            }
            ListViewObservations.setItems(selectedCitizen.getObservationer());
            ListViewObservations.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            {
                if(newValue!=null)
                {
                    txtAreaDok.setText(newValue.getDescriptionProperty().get());
                }
            });
        });
    }
    public void setDashboardController(DashboardController dashboardController){
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

    public void handleMouseReleasedDokumentationArea(MouseEvent mouseEvent) {
        txtAreaDok.setText(observation.getDescriptionProperty().get());
    }
}
