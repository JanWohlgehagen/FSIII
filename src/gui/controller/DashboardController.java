package gui.controller;

import be.Borger;
import be.Case;
import be.Person;

import be.user.UserType;
import bll.ManagerFacade;
import dal.DatabaseFacade;
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
    private Person loginPerson;
    private Case currentCase;

    private CaseModel caseModel;
    private CitizenModel citizenModel;
    private ObservableList<Borger> allCitizens;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                caseModel = new CaseModel(new ManagerFacade());
                citizenModel = new CitizenModel(new ManagerFacade());

                lvCitizens.setItems(citizenModel.getAllCitizen());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void handleButtonSagsåbning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CaseOpeningController> caseOpeningScene =  new CaseOpeningScene();
        caseOpeningScene.loadNewScene(new Stage());
        CaseOpeningController caseOpeningController = caseOpeningScene.getController();
        caseOpeningController.setCaseModel(caseModel);
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) {
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setSagsoplysningsController(sagsoplysningController);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        if(currentCase == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
        else {
            ISceneLoader<BestillingsViewController> bestillingsScene = new BestillingsScene();
            bestillingsScene.loadNewScene(new Stage());
            BestillingsViewController bestillingsViewController = bestillingsScene.getController();
            bestillingsViewController.setBestillingsViewController(bestillingsViewController);
            bestillingsViewController.setCurrentCase(currentCase);
        }
    }

    public void handleButtonPlanlægning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<PlanlægningController> planlægningScene = new PlanlægningScene();
        planlægningScene.loadNewScene(new Stage());
        PlanlægningController planlægningController = planlægningScene.getController();
        planlægningController.setDashboardController(dashboardController);
    }

    public void handleButtonLevering(ActionEvent actionEvent) throws IOException {
        if(currentCase == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
        else {
            ISceneLoader<CaseDocumentationViewController> caseDocumentationScene = new CaseDocumentationScene();
            caseDocumentationScene.loadNewScene(new Stage());
            CaseDocumentationViewController caseDocumentationViewController = caseDocumentationScene.getController();
            caseDocumentationViewController.setCaseDocumentationViewController(caseDocumentationViewController);
            caseDocumentationViewController.setCurrentCase(currentCase);
        }
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void setloginPerson(Person person){
        this.loginPerson = person;
        if(loginPerson.getUserType().equals(UserType.STUDENT)) {
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
