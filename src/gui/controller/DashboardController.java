package gui.controller;

import be.Borger;
import be.Case;
import be.user.User;

import be.user.UserType;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.model.*;
import gui.util.*;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnNewCitizen;
    @FXML
    private Button btnUdorelseLevering;
    @FXML
    private Button btnOpfolgning;
    @FXML
    private Button btnSagsaabning;
    @FXML
    private Button btnSagsOplysning;
    @FXML
    private Button btnAfgorelseBestilling;
    @FXML
    private Button btnPlanlaegning;

    @FXML
    private TextField txtSearchBarBorgere;
    @FXML
    private TextField txtSearchBarStudentBorgere;
    @FXML
    private TextField txtSearchBarTemplates;

    @FXML
    private ListView<Borger> lvCitizens;
    @FXML
    private ListView lvTemplates;
    @FXML
    private ListView lvStuderende;
    @FXML
    private ListView lvStuderendesBorgere;

    @FXML
    private TabPane tabpaneDBView;

    @FXML
    private Tab tabTemplates;
    @FXML
    private Tab tabStudents;

    @FXML
    private Label lblName;
    @FXML
    private Label lblAge;


    private DashboardController dashboardController;
    private User loginUser;

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private Case selectedCase;
    private Borger selectCitizen;
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel;
    private HelbredstilstandModel helbredstilstandModel;
    private HelbredstilstandsUnderkategoriModel helbredstilstandsUnderkategoriModel;
    private UserModel userModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            caseModel = new CaseModel(new ManagerFacade(new DatabaseFacade()));
            citizenModel = new CitizenModel(new ManagerFacade(new DatabaseFacade()));
            funktionstilstandModel = new FunktionstilstandModel(new ManagerFacade(new DatabaseFacade()));
            funktionstilstandsUnderkategoriModel = new FunktionstilstandsUnderkategoriModel(new ManagerFacade(new DatabaseFacade()));
            helbredstilstandModel = new HelbredstilstandModel(new ManagerFacade(new DatabaseFacade()));
            helbredstilstandsUnderkategoriModel = new HelbredstilstandsUnderkategoriModel(new ManagerFacade(new DatabaseFacade()));
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            setDashboardToLoginUserProfile();

        });


        lvCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectCitizen = newValue;
                lblAge.setText(selectCitizen.getAgeProperty().get() + " år");
                lblName.setText(selectCitizen.getFirstNameProperty().get() + " " + selectCitizen.getLastNameProperty().get());
            }
            if (newValue != null && oldValue == null) {
                btnSagsaabning.setDisable(false);
                btnSagsOplysning.setDisable(false);
                btnAfgorelseBestilling.setDisable(false);
                btnPlanlaegning.setDisable(false);
                btnOpfolgning.setDisable(false);
                btnUdorelseLevering.setDisable(false);
            }
        });


    }

    public void setSelectedCase(Case selectionCase) {
        this.selectedCase = selectionCase;
    }

    public Case getSelectedCase() {
        return selectedCase;
    }

    public Borger getSelectedCitizen() {
        return selectCitizen;
    }

    public CaseModel getCaseModel() {
        return caseModel;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void handleButtonSagsåbning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CaseOpeningController> caseOpeningScene = new CaseOpeningScene();
        caseOpeningScene.loadNewScene(new Stage());
        CaseOpeningController caseOpeningController = caseOpeningScene.getController();
        caseOpeningController.setCaseModel(caseModel);
        caseOpeningController.setDashboardController(dashboardController);
        caseOpeningController.setFunktionstilstandModel(funktionstilstandModel);
        caseOpeningController.setFunktionstilstandsUnderkategoriModel(funktionstilstandsUnderkategoriModel);
        caseOpeningController.setHelbredstilstandModel(helbredstilstandModel);
        caseOpeningController.setHelbredstilstandsUnderkategoriModel(helbredstilstandsUnderkategoriModel);
        caseOpeningController.setCitizenModel(citizenModel);
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<OpfoelgningController> opfoelgningScene = new OpfoelgningScene();
        opfoelgningScene.loadNewScene(new Stage());
        OpfoelgningController opfoelgningController = opfoelgningScene.getController();
        opfoelgningController.setDashboardController(dashboardController);
        opfoelgningController.setCaseModel(caseModel);
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) throws IOException {
        citizenModel.getTilstande(selectCitizen);
        citizenModel.getGenerelleOplysninger(selectCitizen);
        ISceneLoader<SagsoplysningController> sagsoplysningsScene = new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
        sagsoplysningController.setCaseModel(caseModel);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        if (selectedCase != null) {
            ISceneLoader<BestillingsViewController> bestillingsScene = new BestillingsScene();
            bestillingsScene.loadNewScene(new Stage());
            BestillingsViewController bestillingsViewController = bestillingsScene.getController();
            bestillingsViewController.setDashboardController(dashboardController);
            bestillingsViewController.setCaseModel(caseModel);
            bestillingsViewController.setCurrentCitizen(selectCitizen);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }

    }

    public void handleButtonPlanlaegning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<PlanlaegningController> planlaegningScene = new PlanlaegningScene();
        planlaegningScene.loadNewScene(new Stage());
        PlanlaegningController planlaegningController = planlaegningScene.getController();
        planlaegningController.setDashboardController(dashboardController);
    }

    public void handleButtonLevering(ActionEvent actionEvent) throws IOException {
        if (selectedCase != null) {
            ISceneLoader<UdfoerelseIOgLeveringController> caseDocumentationScene = new CaseDocumentationScene();
            caseDocumentationScene.loadNewScene(new Stage());
            UdfoerelseIOgLeveringController udfoerelseIOgLeveringController = caseDocumentationScene.getController();
            udfoerelseIOgLeveringController.setDashboardController(dashboardController);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleBtnNewCitizenTemplate(ActionEvent actionEvent) {
        //TODO
    }

    public void handleBtnDeleteCitizenTemplate(ActionEvent actionEvent) {
        //TODO
    }

    public void handleBtnGenerateCitizenFromTemplate(ActionEvent actionEvent) {
        //TODO
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setLoginPerson(User user) {
        this.loginUser = user;
    }

    private void setDashboardToLoginUserProfile() {
        if (this.loginUser != null) {
            switch (this.loginUser.getUserType()) {
                case STUDENT -> {
                    tabpaneDBView.getTabs().remove(1, 3);
                    btnAddStudent.setVisible(false);
                    btnNewCitizen.setVisible(false);

                    for (Borger b : citizenModel.getAllCitizen()) {
                        if (b.getStudentIDProperty().get() == loginUser.getIdProperty().get()) {
                            lvCitizens.getItems().add(b);
                        }
                    }
                }
                case TEACHER -> {
                    tabStudents.setDisable(false);
                    tabTemplates.setDisable(false);
                    lvCitizens.setItems(citizenModel.getAllCitizen());
                }

                default -> throw new UnsupportedOperationException();
            }
        }
    }

    public void createCitizen(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateCitizenViewController> createCitizenSceneLoader = new CreateCitizenScene();
        createCitizenSceneLoader.loadNewScene(new Stage());
        CreateCitizenViewController createCitizenViewController = createCitizenSceneLoader.getController();
        createCitizenViewController.setDashboardController(dashboardController);
        createCitizenViewController.setCitizenModel(citizenModel);
    }

    public void updateCitizenList() {
        lvCitizens.getItems().clear();
        lvCitizens.setItems(citizenModel.getAllCitizen());
    }

    public void btnConnectStudent(ActionEvent actionEvent) throws IOException {
        ISceneLoader<TilfoejStuderendePaaBorgerController> tilfoejStuderendePaaBorgerControllerSceneLoader = new TilfoejStuderendeScene();
        tilfoejStuderendePaaBorgerControllerSceneLoader.loadNewScene(new Stage());
        TilfoejStuderendePaaBorgerController tilfoejStuderendePaaBorgerController = tilfoejStuderendePaaBorgerControllerSceneLoader.getController();
        tilfoejStuderendePaaBorgerController.setBorger(selectCitizen);
        tilfoejStuderendePaaBorgerController.setModelsAndControllers(citizenModel, userModel, this);
    }
}
