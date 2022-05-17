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

import java.net.URL;
import java.util.ResourceBundle;

public class TilfoejStuderendePaaBorgerController implements Initializable {
    @FXML
    private ComboBox<WClass> cboxClasses;
    @FXML
    private ListView<User> lvStudents;

    private TilfoejStuderendePaaBorgerController tilfoejStuderendePaaBorgerController;
    private CitizenModel citizenModel;
    private Borger selectedBorger;
    private UserModel userModel;


    public void btnAddStudent(ActionEvent actionEvent) {
        selectedBorger.setStudentID(lvStudents.getSelectionModel().getSelectedItem().getIdProperty().get());
        citizenModel.updateCitizen(selectedBorger);
    }

    public void setModels(CitizenModel citizenModel, UserModel userModel)
    {
        this.citizenModel = citizenModel;
        this.userModel = userModel;


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
}
