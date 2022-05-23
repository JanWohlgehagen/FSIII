package gui.controller.create_edit;

import be.*;
import gui.controller.DashboardController;
import gui.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAndEditCaseController implements Initializable {

    @FXML
    private TextField lblSagsansvarlig;
    @FXML
    private ComboBox<String> comboboxCaseReference;
    @FXML
    private ComboBox<String> overkategoriCbx;
    @FXML
    private ComboBox<String> underkategoriCbx;
    @FXML
    private GridPane parentGridPane;
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

    private boolean newCaseMode;
    private boolean editCaseMode;

    private CaseModel caseModel;
    private Citizen citizen;

    private Case editThisCase;
    private DashboardController dashboardController;

    private HealthAssessment healthAssessmentTitle;
    private FunctionAssessment functionAssessmentTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            citizen = dashboardController.getSelectedCitizen();
            functionAssessmentTitle = caseModel.getTitleFunktionsTilstand();
            healthAssessmentTitle = caseModel.getTitleHelbredsTilstand();

            setComboBoxOvercategory();

            comboboxCaseReference.getItems().addAll(caseReferences());

            if (editCaseMode) {
                overkategoriCbx.setPromptText(editThisCase.getOverCategoryTitleProperty().get());
                underkategoriCbx.setPromptText(editThisCase.getSubcategoryTitleProperty().get());
                lblSagsansvarlig.setText(editThisCase.getCaseResponsibleProperty().get());
                comboboxCaseReference.getSelectionModel().select(editThisCase.getReferenceProperty().get());
                beskrivelseTxtArea.setText(editThisCase.getDescriptionProperty().get());
                aarsagsfritekstTxtArea.setText(editThisCase.getCauseProperty().get());
                aarsagsdiagnoseTxtArea.setText(editThisCase.getCauseDiagnosisProperty().get());
                aarsagstilstandTxtArea.setText(editThisCase.getCauseConditionProperty().get());
                borgerMaalTxtArea.setText(editThisCase.getCitizenWishesProperty().get());

            }
        });
    }

    public void HandleOverkategoriCbx(ActionEvent actionEvent) {
        underkategoriCbx.getItems().clear();
        String overcategory = overkategoriCbx.getSelectionModel().getSelectedItem();
        List<HelbredstilstandsUnderkategori> subcategorysOfHelbredstilstand  = healthAssessmentTitle.getHelbredsTilstandsKort().get(overcategory);
        List<FunktionstilstandsUnderkategori> subcategorysOfFunktionsTilstand = functionAssessmentTitle.getFunktionsTilstandsKort().get(overcategory);
        
        if(subcategorysOfHelbredstilstand != null){
            for (HelbredstilstandsUnderkategori subcategory: subcategorysOfHelbredstilstand) {
                underkategoriCbx.getItems().add(subcategory.toString());
            }
        }else if(subcategorysOfFunktionsTilstand != null){
            for (FunktionstilstandsUnderkategori subcategory: subcategorysOfFunktionsTilstand) {
                underkategoriCbx.getItems().add(subcategory.toString());
            }
        }
    }

    private void setComboBoxOvercategory(){
        for (var overcategory: functionAssessmentTitle.getFunktionsTilstandsKort().keySet()) {
            overkategoriCbx.getItems().add(overcategory);
        };
        for (var overcategory: healthAssessmentTitle.getHelbredsTilstandsKort().keySet()) {
            overkategoriCbx.getItems().add(overcategory);
        };
    }

    private List<String> caseReferences() {
        String[] caseReferences = {"Borger", "Pårørende", "Sagsbehandler - anden forvaltning", "Hjemmeplejen",
                "Hjemmesygeplejen", "Træning", "Sundhedsfremme og forebyggelse", "Anden kommune",
                "Egen læge/vagtlæge", "Speciallæge", "Sygehus - kirurgisk", "Sygehus - medicinsk",
                "Sygehus – psykiatrisk", "Sygehus – akutmodtagelse", "Andre"};

        return Arrays.stream(caseReferences).toList();
    }

    public void newCaseModeIsOn() {
        this.newCaseMode = true;
    }

    public void editCaseModeIsOn() {
        this.editCaseMode = true;
    }

    public void setEditThisCase(Case editThisCase) {
        this.editThisCase = editThisCase;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setCaseModel(CaseModel caseModel) {
        this.caseModel = caseModel;
    }

    public void handleGem(ActionEvent actionEvent) {
        if (newCaseMode) {
            Case newCase = new Case(citizen.getIDProperty().get(), overkategoriCbx.getSelectionModel().getSelectedItem(), underkategoriCbx.getSelectionModel().getSelectedItem());
            newCase.setIsGranted(false);
            newCase.setReference(comboboxCaseReference.getSelectionModel().getSelectedItem());
            newCase.setCaseResponsible(lblSagsansvarlig.getText());
            newCase.setDescription(beskrivelseTxtArea.getText());
            newCase.setCause(aarsagsfritekstTxtArea.getText());
            newCase.setCauseDiagnosis(aarsagsdiagnoseTxtArea.getText());
            newCase.setCauseCondition(aarsagstilstandTxtArea.getText());
            newCase.setCitizenWishes(borgerMaalTxtArea.getText());
            newCase.setGrantedText("");
            newCase.setPlan("");
            newCase.setFollowUpTag("");

            caseModel.createCaseOnCitizen(newCase);
            closeStage();
        } else if (editCaseMode) {
            if (overkategoriCbx.getSelectionModel().getSelectedItem() != null) {
                editThisCase.setOverCategoryTitle(overkategoriCbx.getSelectionModel().getSelectedItem());
            }
            if (underkategoriCbx.getSelectionModel().getSelectedItem() != null) {
                editThisCase.setSubcategoryTitle(underkategoriCbx.getSelectionModel().getSelectedItem());
            }
            editThisCase.setCaseResponsible(lblSagsansvarlig.getText());
            editThisCase.setReference(comboboxCaseReference.getSelectionModel().getSelectedItem());
            editThisCase.setDescription(beskrivelseTxtArea.getText());
            editThisCase.setCause(aarsagsfritekstTxtArea.getText());
            editThisCase.setCauseDiagnosis(aarsagsdiagnoseTxtArea.getText());
            editThisCase.setCauseCondition(aarsagstilstandTxtArea.getText());
            editThisCase.setCitizenWishes(borgerMaalTxtArea.getText());

            caseModel.updateCaseOnCitizen(citizen.getIDProperty().get(), editThisCase);
            closeStage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.. ");
            alert.setContentText("Der er noget galt, men sag den der var opret/vælget");
            alert.showAndWait();
        }
    }

    public void handleAnnuller(ActionEvent actionEvent) {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) parentGridPane.getScene().getWindow();
        stage.close();
    }

}
