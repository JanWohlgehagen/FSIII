package gui.controller;

import be.Borger;
import be.Case;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.model.FunktionstilstandModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

    @FXML
    private ComboBox<String> overkategoriCbx;
    @FXML
    private ComboBox<String> underkategoriCbx;
    @FXML
    private GridPane parentGridPane;
    @FXML
    private TextField overtilstandTxtField;
    @FXML
    private TextField undertilstandTxtField;
    @FXML
    private Label fornavnLbl;
    @FXML
    private Label efternavnLbl;
    @FXML
    private Label alderLbl;
    @FXML
    private Label antalAktiveSagerLbl;
    @FXML
    private Label sagsansvarligLbl;

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

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private Borger borger;
    private DashboardController dashboardController;
    private FunktionstilstandModel funktionstilstandModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            borger = dashboardController.getSelectedCitizen();
            ObservableList<String> funktionstilstandsList = FXCollections.observableList(funktionstilstandModel.getFunktionstilstandsList());
            overkategoriCbx.setItems(funktionstilstandsList);
        });
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setFunktionstilstandModel(FunktionstilstandModel funktionstilstandModel) {
        this.funktionstilstandModel = funktionstilstandModel;
    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }

    public void handleGem(ActionEvent actionEvent) {
        // if statements
        // registrere oprettelsestidspunkt ?

        Case newCase = new Case(borger.IDProperty().get(), beskrivelseTxtArea.getText(), overtilstandTxtField.getText());
        newCase.setPersonID(dashboardController.getSelectedCitizen().IDProperty().get());
        newCase.setIsBevilget(false);
        newCase.setBevillingstekst("");
        newCase.setPlan("");
        newCase.setOpfoelgningstag("");

        caseModel.createCaseOnCitizen(newCase);
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }

// public Case(int personID, String caseTitle, String caseDescription)

    public void handleAnnuller(ActionEvent actionEvent) {
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }

    public void handleVaelgSag(ActionEvent actionEvent) {
    }

    public String getSelectedFunktionstilstand() {
        return overkategoriCbx.getSelectionModel().getSelectedItem();
    }

    public void handleUnderkategori(ActionEvent actionEvent) {
    }
}
