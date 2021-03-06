package gui.controller.circle;

import be.Citizen;
import be.Case;
import gui.controller.create_edit.CreateAndEditCaseController;
import gui.controller.DashboardController;
import gui.model.*;
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
    private Label sagsansvarligLbl;
    @FXML
    private Label lblHenvisning;
    @FXML
    private Label lblOpfolgningsTag;

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

    private Citizen citizen;
    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private DashboardController dashboardController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            citizen = dashboardController.getSelectedCitizen();

            fornavnLbl.setText(citizen.getFirstNameProperty().get());
            efternavnLbl.setText(citizen.getLastNameProperty().get());
            alderLbl.setText(String.valueOf(citizen.getAgeProperty().get()));

            if (dashboardController.getSelectedCase() != null) {
                vaelgSagCbx.getSelectionModel().select(dashboardController.getSelectedCase());
            }
        });

        vaelgSagCbx.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                overkategoriTxtField.setText(newValue.getOverCategoryTitleProperty().get());
                underkategoriTxtField.setText(newValue.getSubcategoryTitleProperty().get());
                fornavnLbl.setText(citizen.getFirstNameProperty().get());
                efternavnLbl.setText(citizen.getLastNameProperty().get());
                alderLbl.setText(String.valueOf(citizen.getAgeProperty().get()));
                sagsansvarligLbl.setText(newValue.getCaseResponsibleProperty().get());
                lblHenvisning.setText(newValue.getReferenceProperty().get());
                lblOpfolgningsTag.setText(newValue.getFollowUpTagProperty().get());
                beskrivelseTxtArea.setText(newValue.getDescriptionProperty().get());
                aarsagsfritekstTxtArea.setText(newValue.getCauseProperty().get());
                aarsagsdiagnoseTxtArea.setText(newValue.getCauseDiagnosisProperty().get());
                aarsagstilstandTxtArea.setText(newValue.getCauseConditionProperty().get());
                borgerMaalTxtArea.setText(newValue.getCitizenWishesProperty().get());
            }
        });

    }

    public void setCaseModel(CaseModel caseModel) {
        this.caseModel = caseModel;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void handleOpretSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setDashboardController(dashboardController);
        createAndEditCaseController.newCaseModeIsOn();
        createAndEditCaseController.setCitizenModel(citizenModel);
        createAndEditCaseController.setCaseModel(caseModel);
    }

    public void handleRedigerSag(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditCaseController> createAndEditCaseScene = new CreateAndEditCaseScene();
        createAndEditCaseScene.loadNewScene(new Stage());
        CreateAndEditCaseController createAndEditCaseController = createAndEditCaseScene.getController();
        createAndEditCaseController.setDashboardController(dashboardController);
        createAndEditCaseController.setCaseModel(caseModel);
        createAndEditCaseController.setCitizenModel(citizenModel);

        if (getSelectedCase() != null) {
            createAndEditCaseController.setEditThisCase(getSelectedCase());
            createAndEditCaseController.editCaseModeIsOn();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error... ");
            alert.setContentText("Der skal v??lges en sag, f??r den kan redigeres");
            alert.showAndWait();
        }
    }

    public void handleSletSag(ActionEvent actionEvent) {
        if (displayWarning()) {
            caseModel.deleteCaseOnCitizen(citizen.getIDProperty().get(),
                    getSelectedCase().getCaseIDProperty().get());

            vaelgSagCbx.getItems().clear();
            overkategoriTxtField.clear();
            underkategoriTxtField.clear();
            fornavnLbl.setText("");
            efternavnLbl.setText("");
            alderLbl.setText("");
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

    private boolean displayWarning() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Skal denne Sag (" + getSelectedCase() + ") slettes?");
        alert.setContentText("Press OK to continue.");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void handleMouseOpdateVaelgSagCbox(MouseEvent mouseEvent) {
        vaelgSagCbx.getItems().clear();
        vaelgSagCbx.getItems().addAll(caseModel.getAllCasesOnCitizen(citizen.getIDProperty().get()));
    }

    public void handleMouseDashboardScene(MouseEvent mouseEvent) {
        setSelectedCase();
        getStage().close();
    }

    public void handleMouseSagsoplysningsScene(MouseEvent mouseEvent) throws IOException {
        setSelectedCase();
        citizenModel.setTilstandeOnCitizen(dashboardController.getSelectedCitizen());
        citizenModel.getGenerelleOplysninger(dashboardController.getSelectedCitizen());
        ISceneLoader<AssessmentInformationController> sagsoplysningsScene = new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(getStage());
        AssessmentInformationController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
        sagsoplysningController.setCaseModel(caseModel);
    }

    private Stage getStage() {
        return (Stage) parentGridPane.getScene().getWindow();
    }

    private Case getSelectedCase() {
        return vaelgSagCbx.getSelectionModel().getSelectedItem();
    }

    private void setSelectedCase() {
        dashboardController.setSelectedCase(vaelgSagCbx.getSelectionModel().getSelectedItem());
    }
}
