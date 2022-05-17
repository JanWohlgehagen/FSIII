package gui.controller;

import be.Borger;
import be.WClass;
import be.user.Student;
import be.user.User;
import gui.model.CitizenModel;
import gui.model.UserModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TilfoejStuderendePaaBorgerController implements Initializable {

    @FXML
    private GridPane parentPane;
    @FXML
    private ComboBox<WClass> cboxClasses;
    @FXML
    private ListView<User> lvStudents;

    private TilfoejStuderendePaaBorgerController tilfoejStuderendePaaBorgerController;
    private CitizenModel citizenModel;
    private Borger selectedBorger;
    private UserModel userModel;
    private DashboardController dashboardController;


    public void btnAddStudent(ActionEvent actionEvent) {
        selectedBorger.setStudentID(lvStudents.getSelectionModel().getSelectedItem().getIdProperty().get());
        citizenModel.updateCitizen(selectedBorger);
        dashboardController.updateCitizenList();
        getStage().close();
    }

    public void setModelsAndControllers(CitizenModel citizenModel, UserModel userModel, DashboardController dashboardController)
    {
        this.citizenModel = citizenModel;
        this.userModel = userModel;
        this.dashboardController = dashboardController;

    }

    public void setBorger(Borger b)
    {
        selectedBorger = b;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            setCboxClasses();
                });

        cboxClasses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WClass>() {
            @Override
            public void changed(ObservableValue<? extends WClass> observable, WClass oldValue, WClass newValue) {
                if(newValue!=null) {
                    userModel.studentInClass(newValue);
                    lvStudents.setItems(userModel.getStudentInClass());
                }
            }
        });
    }

    public void setCboxClasses() {
        cboxClasses.setItems(userModel.getAllClass());
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }
}

