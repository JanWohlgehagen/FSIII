package gui.controller;

import be.Citizen;
import be.Case;
import be.User;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.controller.create_edit.CreateCitizenViewController;
import gui.controller.circle.*;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.model.UserModel;
import gui.util.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button btnCitizenToTemplate;
    @FXML
    private Button btnDeleteCitizen;
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
    private ListView<Citizen> lvCitizens;
    @FXML
    private ListView<Citizen> lvTemplates;
    @FXML
    private ListView<User> lvStuderende;
    @FXML
    private ListView<Citizen> lvStuderendesBorgere;

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
    @FXML
    private Label lblStudent;


    private DashboardController dashboardController;
    private User loginUser;

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private Case selectedCase;
    private Citizen selectCitizen;
    private Citizen selectCitizenTemplate;
    private UserModel userModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::setDashboardToLoginUserProfile);

        try {
            caseModel = new CaseModel(new ManagerFacade(new DatabaseFacade()));
            citizenModel = new CitizenModel(new ManagerFacade(new DatabaseFacade()));
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setAllListener();

    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    private <T, U> void checkForNullValuesAndDisableCircle(T newValue, U oldValue) {
        if (newValue != null && oldValue == null) {
            btnSagsaabning.setDisable(false);
            btnSagsOplysning.setDisable(false);
            btnAfgorelseBestilling.setDisable(false);
            btnPlanlaegning.setDisable(false);
            btnOpfolgning.setDisable(false);
            btnUdorelseLevering.setDisable(false);
        }
    }

    public void setSelectedCase(Case selectionCase) {
        this.selectedCase = selectionCase;
    }

    public Case getSelectedCase() {
        return selectedCase;
    }

    public Citizen getSelectedCitizen() {
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
        caseOpeningController.setCitizenModel(citizenModel);
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) throws IOException {
        if (selectedCase != null) {
            ISceneLoader<FollowUpController> opfoelgningScene = new OpfoelgningScene();
            opfoelgningScene.loadNewScene(new Stage());
            FollowUpController followUpController = opfoelgningScene.getController();
            followUpController.setDashboardController(dashboardController);
            followUpController.setCaseModel(caseModel);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) throws IOException {
        citizenModel.setTilstandeOnCitizen(selectCitizen);
        citizenModel.getGenerelleOplysninger(selectCitizen);
        ISceneLoader<AssessmentInformationController> sagsoplysningsScene = new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        AssessmentInformationController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
        sagsoplysningController.setCaseModel(caseModel);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        if (selectedCase != null) {
            ISceneLoader<OrderViewController> bestillingsScene = new BestillingsScene();
            bestillingsScene.loadNewScene(new Stage());
            OrderViewController orderViewController = bestillingsScene.getController();
            orderViewController.setDashboardController(dashboardController);
            orderViewController.setCaseModel(caseModel);
            orderViewController.setCurrentCitizen(selectCitizen);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleButtonPlanlaegning(ActionEvent actionEvent) throws IOException {
        if (selectedCase != null) {
            ISceneLoader<PlanningController> planlaegningScene = new PlanlaegningScene();
            planlaegningScene.loadNewScene(new Stage());
            PlanningController planningController = planlaegningScene.getController();
            planningController.setDashboardController(dashboardController);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }


    public void handleButtonLevering(ActionEvent actionEvent) throws IOException {
        ISceneLoader<ExecutionAndDeliveryController> caseDocumentationScene = new CaseDocumentationScene();
        caseDocumentationScene.loadNewScene(new Stage());
        ExecutionAndDeliveryController executionAndDeliveryController = caseDocumentationScene.getController();
        executionAndDeliveryController.setDashboardController(dashboardController);
        executionAndDeliveryController.setCitizenModel(citizenModel);
    }

    public void handleBtnNewCitizenTemplate(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateCitizenViewController> createCitizenSceneLoader = new CreateCitizenScene();
        createCitizenSceneLoader.loadNewScene(new Stage());
        CreateCitizenViewController createCitizenViewController = createCitizenSceneLoader.getController();
        createCitizenViewController.setDashboardController(dashboardController);
        createCitizenViewController.setTemplate(true);
        createCitizenViewController.setCitizenModel(citizenModel);
    }

    public void handleBtnDeleteCitizenTemplate(ActionEvent actionEvent) {
        if (selectCitizen != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på du vil slette denne template borger?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.YES)) {
                citizenModel.deleteCitizen(selectCitizenTemplate);
                lvTemplates.getItems().clear();
                lvTemplates.setItems(citizenModel.getAllTemplates());
            }
        }
    }

    public void handleBtnGenerateCitizenFromTemplate(ActionEvent actionEvent) {
        citizenModel.createCitizenFromTemplate(selectCitizenTemplate);
    }

    public void handleChangeTab(Event event) {
        try {
            lvStuderende.getSelectionModel().clearSelection();
            lvStuderendesBorgere.getSelectionModel().clearSelection();
            lvCitizens.getSelectionModel().clearSelection();
            lvTemplates.getSelectionModel().clearSelection();
            selectedCase = null;
            selectCitizen = null;
            btnSagsaabning.setDisable(true);
            btnSagsOplysning.setDisable(true);
            btnAfgorelseBestilling.setDisable(true);
            btnPlanlaegning.setDisable(true);
            btnOpfolgning.setDisable(true);
            btnUdorelseLevering.setDisable(true);
        } catch (NullPointerException nullPointerException) {
            //No handling required, this is thrown when the Dashboard view loads.
        }
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
                    btnDeleteCitizen.setVisible(false);
                    btnCitizenToTemplate.setVisible(false);

                    for (Citizen b : citizenModel.getAllCitizen()) {
                        if (b.getStudent() != null && b.getStudent().getIdProperty().get() == loginUser.getIdProperty().get()) {
                            lvCitizens.getItems().add(b);
                        }
                    }
                }
                case TEACHER -> {
                    tabStudents.setDisable(false);
                    tabTemplates.setDisable(false);
                    lvCitizens.setItems(citizenModel.getAllCitizen());
                    lvStuderende.setItems(userModel.getAllStudent());
                    lvTemplates.setItems(citizenModel.getAllTemplates());
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

    public void btnConnectStudent(ActionEvent actionEvent) throws IOException {
        ISceneLoader<AddStudentToCitizenController> tilfoejStuderendePaaBorgerControllerSceneLoader = new TilfoejStuderendeScene();
        tilfoejStuderendePaaBorgerControllerSceneLoader.loadNewScene(new Stage());
        AddStudentToCitizenController addStudentToCitizenController = tilfoejStuderendePaaBorgerControllerSceneLoader.getController();
        addStudentToCitizenController.setCitizen(selectCitizen);
        addStudentToCitizenController.setAllModels(citizenModel, userModel);
    }

    public void btnDeleteCitizen(ActionEvent actionEvent) {
        if (selectCitizen != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på du vil slette denne borger?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.YES)) {
                citizenModel.deleteCitizen(selectCitizen);

            }
        }
    }

    private void setAllListener() {

        lvCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCase = null;
                selectCitizen = newValue;
                lblAge.setText(newValue.getAgeProperty().get() + " år");
                lblName.setText(newValue.getFirstNameProperty().get() + " " + selectCitizen.getLastNameProperty().get());
                if (newValue.getStudent() != null) {
                    lblStudent.setText(newValue.getStudent().getFullNameProperty().get());
                } else {
                    lblStudent.setText("Ingen tilknyttede studerende.");
                }
            }
            checkForNullValuesAndDisableCircle(newValue, oldValue);
        });

        lvStuderendesBorgere.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectCitizen = newValue;
            }
            checkForNullValuesAndDisableCircle(newValue, oldValue);
        });

        lvTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCase = null;
                selectCitizenTemplate = newValue;
                selectCitizen = newValue;
            }
            checkForNullValuesAndDisableCircle(newValue, oldValue);
        });

        lvStuderende.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCase = null;
                lvStuderendesBorgere.getItems().clear();
                for (Citizen b : citizenModel.getAllCitizen()) {
                    if (b.getStudent() != null && b.getStudent().getIdProperty().get() == newValue.getIdProperty().get()) {
                        lvStuderendesBorgere.getItems().add(b);
                    }
                }
            }
        });

        // Search functionality in the list view
        txtSearchBarBorgere.textProperty().addListener((observableValue, oldValue, newValue) -> {
            citizenModel.searchCitizen(newValue);
        });

        // Search functionality in the list view
        txtSearchBarTemplates.textProperty().addListener((observableValue, oldValue, newValue) -> {
            citizenModel.searchTemplates(newValue);
        });

        // Search functionality in the list view
        txtSearchBarStudentBorgere.textProperty().addListener((observableValue, oldValue, newValue) -> {
            userModel.searchStudent(newValue);
        });
    }

    public void btnCitizenToTemplate(ActionEvent actionEvent) {
        citizenModel.createTemplateFromCitizen(selectCitizen);
    }
}
