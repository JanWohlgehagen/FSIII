package gui.controller;

import be.Borger;
import be.Case;
import be.Person;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

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
    private Person person;
    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            person = dashboardController.
        });

    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
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

        Borger borger = new Borger(fornavnLbl.getText(), efternavnLbl.getText());
        Case sag = new Case()


                sag.personIDProperty().get(),
                sag.caseTitleProperty().get(),
                sag.caseDescriptionProperty().get());

        caseModel.createCaseOnCitizen(sag);

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

    public void handleOverkategori(ActionEvent actionEvent) {
    }

    public void handleUnderkategori(ActionEvent actionEvent) {
    }
}
