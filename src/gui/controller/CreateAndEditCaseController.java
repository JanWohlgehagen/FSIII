package gui.controller;

import be.Borger;
import be.Case;
import gui.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

    public TextField lblSagsansvarlig;
    public TextField lblSagsHenvisning;
    @FXML
    private ComboBox<String> overkategoriCbx;
    @FXML
    private ComboBox<String> underkategoriCbx;
    @FXML
    private GridPane parentGridPane;
    @FXML
    private Button annullerBtn;
    @FXML
    private Button gemBtn;
    @FXML
    private TextArea beskrivelseTxtArea;
    @FXML
    private TextArea aarsagsfritekstTxtArea;
    @FXML
    private TextArea aarsagsdiagnoseTxtArea;
    @FXML
    private TextArea aarsagstilstandTxtArea;
    @FXML
    private TextArea borgerMaalTxtArea;

    private boolean newCaseMode;
    private boolean editCaseMode;

    private CaseModel caseModel;
    private Borger borger;

    private Case editThisCase;
    private DashboardController dashboardController;
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel;
    private HelbredstilstandModel helbredstilstandModel;
    private HelbredstilstandsUnderkategoriModel helbredstilstandsUnderkategoriModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            borger = dashboardController.getSelectedCitizen();

            overkategoriCbx.getItems().addAll(funktionstilstandModel.getFunktionstilstandsList());
            overkategoriCbx.getItems().addAll(helbredstilstandModel.getHelbredstilstandsList());

            if(editCaseMode) {
                overkategoriCbx.setPromptText(editThisCase.getOverkategoriTitleProperty().get());
                underkategoriCbx.setPromptText(editThisCase.getUnderkategoriTitleProperty().get());
                lblSagsansvarlig.setText(editThisCase.getSagsansvarligProperty().get());
                lblSagsHenvisning.setText(editThisCase.getHenvisningProperty().get());
                beskrivelseTxtArea.setText(editThisCase.getCaseDescriptionProperty().get());
                aarsagsfritekstTxtArea.setText(editThisCase.getAasagsfritekstProperty().get());
                aarsagsdiagnoseTxtArea.setText(editThisCase.getAasagsdiagnoseProperty().get());
                aarsagstilstandTxtArea.setText(editThisCase.getAasagstilstandProperty().get());
                borgerMaalTxtArea.setText(editThisCase.getBorgerensonskerProperty().get());

            }
        });
    }

    public void HandleOverkategoriCbx(ActionEvent actionEvent) {
        underkategoriCbx.getItems().clear();
        var cbx = overkategoriCbx.getSelectionModel().getSelectedItem();
        if (cbx.contains("Samfundsliv") || cbx.contains("Mobilitet") || cbx.contains("Mentale funktioner") || cbx.contains("Egenomsorg") || cbx.contains("Praktiske opgaver"))
            underkategoriCbx.getItems().addAll(funktionstilstandsUnderkategoriModel.getFunktionstilstandsUnderkategoriList());
        else
            underkategoriCbx.getItems().addAll(helbredstilstandsUnderkategoriModel.getHelbredstilstandsUnderkategoriList());
    }

    public void newCaseModeIsOn(){
        this.newCaseMode = true;
    }

    public void editCaseModeIsOn(){
        this.editCaseMode = true;
    }

    public void setEditThisCase(Case editThisCase){
        this.editThisCase = editThisCase;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setFunktionstilstandModel(FunktionstilstandModel funktionstilstandModel) {
        this.funktionstilstandModel = funktionstilstandModel;
    }

    public void setFunktionstilstandsUnderkategoriModel(FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel) {
        this.funktionstilstandsUnderkategoriModel =funktionstilstandsUnderkategoriModel;
    }

    public void setHelbredstilstandModel(HelbredstilstandModel helbredstilstandModel) {
        this.helbredstilstandModel = helbredstilstandModel;
    }

    public void setHelbredstilstandsUnderkategoriModel(HelbredstilstandsUnderkategoriModel helbredstilstandsUnderkategoriModel) {
        this.helbredstilstandsUnderkategoriModel = helbredstilstandsUnderkategoriModel;
    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void handleGem(ActionEvent actionEvent) {
        if(newCaseMode){
            Case newCase = new Case(borger.getIDProperty().get(), overkategoriCbx.getSelectionModel().getSelectedItem(), underkategoriCbx.getSelectionModel().getSelectedItem());
            newCase.setIsBevilget(false);
            newCase.setHenvisning(lblSagsHenvisning.getText());
            newCase.setSagsansvarlig(lblSagsansvarlig.getText());
            newCase.setCaseDescription(beskrivelseTxtArea.getText());
            newCase.setAasagsfritekst(aarsagsfritekstTxtArea.getText());
            newCase.setAasagsdiagnose(aarsagsdiagnoseTxtArea.getText());
            newCase.setAasagstilstand(aarsagstilstandTxtArea.getText());
            newCase.setBorgerensonsker(borgerMaalTxtArea.getText());
            newCase.setBevillingstekst("");
            newCase.setPlan("");
            newCase.setOpfoelgningstag("");

            caseModel.createCaseOnCitizen(newCase);
            closeStage();
        } else if (editCaseMode) {
            if(overkategoriCbx.getSelectionModel().getSelectedItem() != null){
                editThisCase.setOverkategoriTitle(overkategoriCbx.getSelectionModel().getSelectedItem());
            }
            if(underkategoriCbx.getSelectionModel().getSelectedItem() != null){
                editThisCase.setUnderkategoriTitle(underkategoriCbx.getSelectionModel().getSelectedItem());
            }
            editThisCase.setSagsansvarlig(lblSagsansvarlig.getText());
            editThisCase.setHenvisning(lblSagsHenvisning.getText());
            editThisCase.setCaseDescription(beskrivelseTxtArea.getText());
            editThisCase.setAasagsfritekst(aarsagsfritekstTxtArea.getText());
            editThisCase.setAasagsdiagnose(aarsagsdiagnoseTxtArea.getText());
            editThisCase.setAasagstilstand(aarsagstilstandTxtArea.getText());
            editThisCase.setBorgerensonsker(borgerMaalTxtArea.getText());

            caseModel.updateCaseOnCitizen(borger.getIDProperty().get(), editThisCase);
            closeStage();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.. ");
            alert.setContentText("Der er noget galt, men sag den der var opret/vælget");
            alert.showAndWait();
        }
    }

    public void handleAnnuller(ActionEvent actionEvent) {
        closeStage();
    }

    private void closeStage(){
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }

}
