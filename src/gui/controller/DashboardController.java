package gui.controller;

import be.Borger;
import be.Case;
import be.user.User;

import be.user.UserType;
import bll.ManagerFacade;
import gui.model.CaseModel;
import gui.model.CitizenModel;
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
    private TextField txtSearchBar;
    @FXML
    private  ListView<Borger> lvCitizens;
    @FXML
    private  Tab tabTemplates;
    @FXML
    private  Tab tabStudents;


    private DashboardController dashboardController;
    private User loginUser;

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private ObservableList<Borger> allCitizens;
    private Case selectedCase;
    private Borger selectCitizen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                caseModel = new CaseModel(new ManagerFacade());
                citizenModel = new CitizenModel(new ManagerFacade());
<<<<<<< Updated upstream
                funktionstilstandModel = new FunktionstilstandModel(new ManagerFacade());
=======
>>>>>>> Stashed changes

                lvCitizens.setItems(citizenModel.getAllCitizen());

            } catch (IOException e) {
                e.printStackTrace();
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

    public void handleButtonSagsåbning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CaseOpeningController> caseOpeningScene =  new CaseOpeningScene();
        caseOpeningScene.loadNewScene(new Stage());
        CaseOpeningController caseOpeningController = caseOpeningScene.getController();
        caseOpeningController.setCaseModel(caseModel);
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<OpfoelgningController> opfoelgningScene = new OpfoelgningScene();
        opfoelgningScene.loadNewScene(new Stage());
        OpfoelgningController opfoelgningController = opfoelgningScene.getController();
        opfoelgningController.setDashboardController(dashboardController);
        opfoelgningController.setCaseModel(caseModel);
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setDashboardController(dashboardController);
        sagsoplysningController.setCitizenModel(citizenModel);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        if(selectedCase == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
        else {
            ISceneLoader<BestillingsViewController> bestillingsScene = new BestillingsScene();
            bestillingsScene.loadNewScene(new Stage());
            BestillingsViewController bestillingsViewController = bestillingsScene.getController();
            bestillingsViewController.setBestillingsViewController(bestillingsViewController);
            bestillingsViewController.setCurrentCase(selectedCase);
        }
    }

    public void handleButtonPlanlægning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<PlanlaegningController> planlaegningScene = new PlanlaegningScene();
        planlaegningScene.loadNewScene(new Stage());
        PlanlaegningController planlaegningController = planlaegningScene.getController();
        planlaegningController.setDashboardController(dashboardController);
        planlaegningController.setCaseModel(caseModel);
    }

    public void handleButtonLevering(ActionEvent actionEvent) throws IOException {
        if(selectedCase == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
        else {
            ISceneLoader<CaseDocumentationViewController> caseDocumentationScene = new CaseDocumentationScene();
            caseDocumentationScene.loadNewScene(new Stage());
            CaseDocumentationViewController caseDocumentationViewController = caseDocumentationScene.getController();
            caseDocumentationViewController.setCaseDocumentationViewController(caseDocumentationViewController);
            caseDocumentationViewController.setCurrentCase(selectedCase);
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
        createCitizenViewController.setCreateCitizenViewController(createCitizenViewController);
        createCitizenViewController.setDashboardController(this);
    }

    public void updateCitizenList() {
        lvCitizens.getItems().clear();
        lvCitizens.setItems(citizenModel.getAllCitizen());
    }
}
