package gui.controller;

import be.Borger;
import be.user.Student;
import gui.model.CitizenModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class TilfoejStuderendePaaBorgerController {
    @FXML
    private ComboBox cboxClasses;
    @FXML
    private ListView<Student> lvStudents;

    private TilfoejStuderendePaaBorgerController tilfoejStuderendePaaBorgerController;
    private CitizenModel citizenModel;
    private Borger selectedBorger;


    public void btnAddStudent(ActionEvent actionEvent) {
        selectedBorger.setStudentID(lvStudents.getSelectionModel().getSelectedItem().getIdProperty().get());
        citizenModel.updateCitizen(selectedBorger);
    }

    public void setModels(CitizenModel citizenModel)
    {
        this.citizenModel = citizenModel;

    }

    public void setBorger(Borger b)
    {
        selectedBorger = b;
    }
}
