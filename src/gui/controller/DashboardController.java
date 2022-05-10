package gui.controller;

import be.Borger;
import be.Case;
import be.user.User;

import be.user.UserType;
import bll.ManagerFacade;
import gui.model.*;
import gui.util.*;


import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    private TextField txtSearchBar;
    @FXML
    private  ListView<Borger> lvCitizens;
    @FXML
    private  Tab tabTemplates;
    @FXML
    private  Tab tabStudents;

    @FXML
    private Label lblName;
    @FXML
    private Label lblAge;


    private DashboardController dashboardController;
    private User loginUser;

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private ObservableList<Borger> allCitizens;
    private Case selectedCase;
    private Borger selectCitizen;
    private FunktionstilstandModel funktionstilstandModel;
    private FunktionstilstandsUnderkategoriModel funktionstilstandsUnderkategoriModel;
    private HelbredstilstandModel helbredstilstandModel;
    private HelbredstilstandsUnderkategoriModel helbredstilstandsUnderkategoriModel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                caseModel = new CaseModel(new ManagerFacade());
                citizenModel = new CitizenModel(new ManagerFacade());
                funktionstilstandModel = new FunktionstilstandModel(new ManagerFacade());
                funktionstilstandsUnderkategoriModel = new FunktionstilstandsUnderkategoriModel(new ManagerFacade());
                helbredstilstandModel = new HelbredstilstandModel(new ManagerFacade());
                helbredstilstandsUnderkategoriModel = new HelbredstilstandsUnderkategoriModel(new ManagerFacade());
                lvCitizens.setItems(citizenModel.getAllCitizen());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        lvCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                selectCitizen = newValue;
                lblAge.setText(selectCitizen.getAgeProperty().get() + " år");
                lblName.setText(selectCitizen.getFirstNameProperty().get() + " " + selectCitizen.getLastNameProperty().get());
            }
            if(newValue != null && oldValue == null){
                btnSagsaabning.setDisable(false);
                btnSagsOplysning.setDisable(false);
                btnAfgorelseBestilling.setDisable(false);
                btnPlanlaegning.setDisable(false);
                btnOpfolgning.setDisable(false);
                btnUdorelseLevering.setDisable(false);
            }
        });


    }

    public void setSelectedCase(Case selectionCase){
        this.selectedCase = selectionCase;
    }

    public Case getSelectedCase(){
        return selectedCase;
    }

    public Borger getSelectedCitizen() {
        return selectCitizen;
    }

    public void setSelectedCitizen(Borger selectCitizen) {
        this.selectCitizen = selectCitizen;
    }

    public CaseModel getCaseModel(){
        return caseModel;
    }

    public void handleButtonSagsåbning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CaseOpeningController> caseOpeningScene =  new CaseOpeningScene();
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
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
            ISceneLoader<BestillingsViewController> bestillingsScene = new BestillingsScene();
            bestillingsScene.loadNewScene(new Stage());
            BestillingsViewController bestillingsViewController = bestillingsScene.getController();
            bestillingsViewController.setBestillingsViewController(bestillingsViewController);
            bestillingsViewController.setDashboardController(dashboardController);

    }

    public void handleButtonPlanlaegning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<PlanlaegningController> planlaegningScene = new PlanlaegningScene();
        planlaegningScene.loadNewScene(new Stage());
        PlanlaegningController planlaegningController = planlaegningScene.getController();
        planlaegningController.setDashboardController(dashboardController);
    }

    public void handleButtonLevering(ActionEvent actionEvent) throws IOException {
        if(selectedCase == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
        else {
            ISceneLoader<UdfoerelseIOgLeveringController> caseDocumentationScene = new CaseDocumentationScene();
            caseDocumentationScene.loadNewScene(new Stage());
            UdfoerelseIOgLeveringController udfoerelseIOgLeveringController = caseDocumentationScene.getController();
            udfoerelseIOgLeveringController.setCaseDocumentationViewController(udfoerelseIOgLeveringController);
            udfoerelseIOgLeveringController.setCurrentCase(selectedCase);
        }
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void setloginPerson(User user){
        this.loginUser = user;
        if(user.getUserType().equals(UserType.STUDENT)) {
            tabStudents.setDisable(true);
            tabTemplates.setDisable(true);
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
}
