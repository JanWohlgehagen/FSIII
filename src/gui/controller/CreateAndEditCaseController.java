package gui.controller;

import be.Borger;
import be.Case;
import bll.FunktionstilstandsUnderkategoriManager;
import gui.model.CaseModel;
import gui.model.FunktionstilstandModel;
import gui.model.FunktionstilstandsUnderkategoriModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

    public TextField txtTitle;
    public TextField txtOpfoelgningTag;
    public CheckBox cbBevilget;
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
    private Borger borger;
    private DashboardController dashboardController;
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            borger = dashboardController.getSelectedCitizen();
            ObservableList<String> funktionstilstandsList = FXCollections.observableList(funktionstilstandModel.getFunktionstilstandsList());
            overkategoriCbx.setItems(funktionstilstandsList);
            ObservableList<String> funktionstilstandsUnderkategoriList = FXCollections.observableList(funktionstilstandsUnderkategoriModel.getFunktionstilstandsUnderkategoriList());
            underkategoriCbx.setItems(funktionstilstandsUnderkategoriList);
        });
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

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void handleGem(ActionEvent actionEvent) {
        Case newCase = new Case(2, txtTitle.getText(), beskrivelseTxtArea.getText()); // skal lige rettes til
        newCase.setIsBevilget(cbBevilget.isSelected());
        newCase.setBevillingstekst("");
        newCase.setPlan("");
        newCase.setOpfoelgningstag("");

        caseModel.createCaseOnCitizen(newCase);
        closeStage();
    }

    public void handleAnnuller(ActionEvent actionEvent) {
        closeStage();
    }

    public String getSelectedFunktionstilstand() {
        return overkategoriCbx.getSelectionModel().getSelectedItem();
    }

    public void handleUnderkategori(ActionEvent actionEvent) {
    }

    private void closeStage(){
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }
}
