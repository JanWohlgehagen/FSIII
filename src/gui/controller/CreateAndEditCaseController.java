package gui.controller;

import gui.model.CaseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

    @FXML
    private Label sagsansvarligLbl;
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
    //private Citizen citizen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void handleGem(ActionEvent actionEvent) {
        //caseModel.createCaseOnCitizen(citizen);
    }

    public void handleAnnuller(ActionEvent actionEvent) {
    }

    public void handleVaelgSag(ActionEvent actionEvent) {
    }

    public void handleOverkategori(ActionEvent actionEvent) {
    }

    public void handleUnderkategori(ActionEvent actionEvent) {
    }
}
