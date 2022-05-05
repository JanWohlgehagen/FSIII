package gui.controller;

import be.Case;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.model.FunktionstilstandModel;
import gui.util.CreateAndEditCaseScene;
import gui.util.ISceneLoader;
import javafx.beans.property.StringProperty;
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

public class CaseOpeningController implements Initializable {

    @FXML
    private GridPane parentGridPane;
    @FXML
    private ComboBox<Case> vaelgSagCbx;
    @FXML
    private TextField overtilstandTxtField;
    @FXML
    private TextField undertilstandTxtField;
    @FXML
    private TextField overkategoriTxtField;
    @FXML
    private TextField underkategoriTxtField;
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
    private Button opretSagBtn;
    @FXML
    private Button redigerSagBtn;
    @FXML
    private Button sletSagBtn;
    @FXML
    private Button videreBtn;
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
    private Funktionstilstand funktionstilstand;
    private FunktionstilstandsUnderkategori funktionstilstandsUnderkategori;
    private DashboardController dashboardController;
    private FunktionstilstandModel funktionstilstandModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void setFunktionstilstandModel(FunktionstilstandModel funktionstilstandModel) {
        this.funktionstilstandModel = funktionstilstandModel;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void handleOpretSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setDashboardController(dashboardController);
        createAndEditCaseController.setCaseModel(caseModel);
        createAndEditCaseController.setFunktionstilstandModel(funktionstilstandModel);
    }

    public void handleRedigerSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
    }

    public void handleSletSag(ActionEvent actionEvent) {
        // noget med at se på items i comboboxen og slette selected item?
    }

    public void handleVaelgSag(ActionEvent actionEvent) {
        //caseModel.getAllCasesOnCitizen(citizenID); // er det ID på citizen den vil have?
    }

    public void setUnderkategoriTxtField(StringProperty underkategoriTxtField) {
        //return this.underkategoriTxtField = funktionstilstandsUnderkategori.getTilstandsklassifikationProperty()
        /** skal den parses på en eller anden måde, eller skal jeg ændre den fra TextField om til StringProperty? **/
        //this.underkategoriTxtField = underkategoriTxtField;
    }

    public void setOverkategoriTxtField(TextField overkategoriTxtField) {
        this.overkategoriTxtField = overkategoriTxtField;
    }

    public void generelleOplysningerHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        // gem funktionalitet
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }

    public void generelleOplysningerHandleSaveAndNextBtn(MouseEvent mouseEvent) {
        //Gem funktionalitet
        //ISCeneLoader næste scene
    }

}
