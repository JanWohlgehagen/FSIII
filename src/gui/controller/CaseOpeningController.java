package gui.controller;

import be.Person;
import gui.util.CaseOpeningScene;
import gui.util.CreateAndEditCaseScene;
import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CaseOpeningController implements Initializable {

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

    private Stage primaryStage;
    CaseOpeningController caseOpeningController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public void openCreateEditCaseView(Stage stage) {

    }

    public void setCaseOpeningController(CaseOpeningController caseOpeningController){
        this.caseOpeningController = caseOpeningController;
    }

    public void handleOpretSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
    }

    public void handleRedigerSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
    }

    public void handleSletSag(ActionEvent actionEvent) {
        // noget med at se på items i comboboxen og slette selected item?
    }

    public void handleVidere(ActionEvent actionEvent) {
        //Gem alt +
        //ISCeneLoader næste scene
    }

    public void handleVaelgSag(ActionEvent actionEvent) {
    }
}
