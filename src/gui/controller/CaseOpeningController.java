package gui.controller;

import be.Borger;
import be.Case;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.model.FunktionstilstandModel;
import gui.model.FunktionstilstandsUnderkategoriModel;
import gui.util.CreateAndEditCaseScene;
import gui.util.ISceneLoader;
import gui.util.SagsoplysningScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private Label lblHenvisning;
    @FXML
    private Label lblOpfolgningsTag;
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
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel;
    private DashboardController dashboardController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
           borger = dashboardController.getSelectedCitizen();

            fornavnLbl.setText(borger.getFirstNameProperty().get());
            efternavnLbl.setText(borger.getLastNameProperty().get());
            alderLbl.setText(String.valueOf(borger.getAgeProperty().get()));
        });

        vaelgSagCbx.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                overkategoriTxtField.setText(newValue.getOverkategoriTitleProperty().get());
                underkategoriTxtField.setText(newValue.getUnderkategoriTitleProperty().get());
                fornavnLbl.setText(borger.getFirstNameProperty().get());
                efternavnLbl.setText(borger.getLastNameProperty().get());
                alderLbl.setText(String.valueOf(borger.getAgeProperty().get()));
                antalAktiveSagerLbl.setText("Skal denne være her???");
                sagsansvarligLbl.setText(newValue.getSagsansvarligProperty().get());
                lblHenvisning.setText(newValue.getHenvisningProperty().get());
                lblOpfolgningsTag.setText(newValue.getOpfoelgningstagProperty().get());
                beskrivelseTxtArea.setText(newValue.getCaseDescriptionProperty().get());
                aarsagsfritekstTxtArea.setText(newValue.getAasagsfritekstProperty().get());
                aarsagsdiagnoseTxtArea.setText(newValue.getAasagsdiagnoseProperty().get());
                aarsagstilstandTxtArea.setText(newValue.getAasagstilstandProperty().get());
                borgerMaalTxtArea.setText(newValue.getBorgerensonskerProperty().get());
            }
        });

    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setFunktionstilstandModel(FunktionstilstandModel funktionstilstandModel) {
        this.funktionstilstandModel = funktionstilstandModel;
    }

    public void setFunktionstilstandsUnderkategoriModel(FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel) {
        this.funktionstilstandsUnderkategoriModel = funktionstilstandsUnderkategoriModel;
    }

    public void handleOpretSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setDashboardController(dashboardController);
        createAndEditCaseController.newCaseModeIsOn();
        createAndEditCaseController.setCaseModel(caseModel);
        createAndEditCaseController.setFunktionstilstandModel(funktionstilstandModel);
        createAndEditCaseController.setFunktionstilstandsUnderkategoriModel(funktionstilstandsUnderkategoriModel);
    }

    public void handleRedigerSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setDashboardController(dashboardController);
        createAndEditCaseController.setCaseModel(caseModel);
        createAndEditCaseController.setFunktionstilstandModel(funktionstilstandModel);
        createAndEditCaseController.setFunktionstilstandsUnderkategoriModel(funktionstilstandsUnderkategoriModel);
        if(getSelectedCase() != null){
            createAndEditCaseController.setEditThisCase(getSelectedCase());
            createAndEditCaseController.editCaseModeIsOn();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.. ");
            alert.setContentText("Der skal vælges en sag, før man kan rediger den");
            alert.showAndWait();
        }
    }

    public void handleSletSag(ActionEvent actionEvent) {
        if(displayWarning()){
            caseModel.deleteCaseOnCitizen(borger.getIDProperty().get(),
                    getSelectedCase().getCaseIDProperty().get());

            vaelgSagCbx.getItems().clear();
            overkategoriTxtField.clear();
            underkategoriTxtField.clear();
            fornavnLbl.setText("");
            efternavnLbl.setText("");
            alderLbl.setText("");
            antalAktiveSagerLbl.setText("Skal denne være her???");
            sagsansvarligLbl.setText("");
            lblHenvisning.setText("");
            lblOpfolgningsTag.setText("");
            beskrivelseTxtArea.clear();
            aarsagsfritekstTxtArea.clear();
            aarsagsdiagnoseTxtArea.clear();
            aarsagstilstandTxtArea.clear();
            borgerMaalTxtArea.clear();
        }
    }

    private boolean displayWarning (){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Skal denne Sag( " + getSelectedCase() + " ) slettes");
        alert.setContentText("Press OK to continue.");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void handleMouseOpdateVaelgSagCbox(MouseEvent mouseEvent) {
        vaelgSagCbx.getItems().clear();
        vaelgSagCbx.getItems().addAll(caseModel.getAllCasesOnCitizen(borger.getIDProperty().get()));
    }

    public void handleMouseDashboardScene(MouseEvent mouseEvent) {
        setSelectedCase();
        getStage().close();
    }

    public void handleMouseSagsoplysningsScene(MouseEvent mouseEvent) throws IOException {
        setSelectedCase();
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(getStage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
    }

    private Stage getStage(){
        return (Stage) parentGridPane.getScene().getWindow();
    }

    private Case getSelectedCase(){
        return vaelgSagCbx.getSelectionModel().getSelectedItem();
    }

    private void setSelectedCase(){
        dashboardController.setSelectedCase(vaelgSagCbx.getSelectionModel().getSelectedItem());
    }
}
