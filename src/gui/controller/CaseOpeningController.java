package gui.controller;

import be.Borger;
import be.Case;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.model.FunktionstilstandModel;
import gui.util.CreateAndEditCaseScene;
import gui.util.ISceneLoader;
import gui.util.SagsoplysningScene;
import javafx.application.Platform;
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

    private Borger borger;
    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private Funktionstilstand funktionstilstand;
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategori funktionstilstandsUnderkategori;
    private DashboardController dashboardController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            borger = new Borger("tobastest", "ramussen", false, 33); // skal lige rettes til !!!!
            borger.setID(2);

            fornavnLbl.setText(borger.getFirstNameProperty().get());
            efternavnLbl.setText(borger.getLastNameProperty().get());
            alderLbl.setText(String.valueOf(borger.getAgeProperty().get()));
        });

        vaelgSagCbx.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                beskrivelseTxtArea.setText(newValue.getCaseDescriptionProperty().get());
            }
        });

    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
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
        //TODO
    }

    public void handleSletSag(ActionEvent actionEvent) {
        caseModel.deleteCaseOnCitizen(borger.getIDProperty().get(),
            vaelgSagCbx.getSelectionModel().getSelectedItem().getCaseIDProperty().get());
    }

    public void handleMouseOpdateVaelgSagCbox(MouseEvent mouseEvent) {
        vaelgSagCbx.getItems().clear();
        vaelgSagCbx.getItems().addAll(caseModel.getAllCasesOnCitizen(borger.getIDProperty().get()));
    }

    public void setUnderkategoriTxtField(StringProperty underkategoriTxtField) {
        //return this.underkategoriTxtField = funktionstilstandsUnderkategori.getTilstandsklassifikationProperty()
        /** skal den parses på en eller anden måde, eller skal jeg ændre den fra TextField om til StringProperty? **/
        //this.underkategoriTxtField = underkategoriTxtField;
    }

    public void setOverkategoriTxtField(TextField overkategoriTxtField) {
        this.overkategoriTxtField = overkategoriTxtField;
    }

    public void generelleOplysningerHandleSaveAndExitBtn(MouseEvent mouseEvent) throws IOException {
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
        closeStage();
    }

    public void handleMouseDashboardScene(MouseEvent mouseEvent) {
        closeStage();
    }

    public void handleMouseSagsoplysningsScene(MouseEvent mouseEvent) throws IOException {
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
        closeStage();
    }

    private void closeStage(){
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }
}
