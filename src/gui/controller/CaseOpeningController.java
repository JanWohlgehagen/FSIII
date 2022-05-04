package gui.controller;

import be.Case;
import be.Person;
import gui.model.CaseModel;
import gui.util.CaseOpeningScene;
import gui.util.CreateAndEditCaseScene;
import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CaseOpeningController implements Initializable {

    @FXML
    private Label sagsansvarligLbl;
    @FXML
    private ComboBox<Case> vaelgSagCbx;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void openCreateEditCaseView(Stage stage) {
        // ??
    }

    public void handleOpretSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setCaseModel(caseModel);
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
    }

    public void handleOverkategori(ActionEvent actionEvent) {
    }

    public void handleUnderkategori(ActionEvent actionEvent) {
    }

    public void generelleOplysningerHandleSaveAndExitBtn(MouseEvent mouseEvent) {
    }

    public void generelleOplysningerHandleSaveAndNextBtn(MouseEvent mouseEvent) {
        //Gem alt +
        //ISCeneLoader næste scene
    }

}
