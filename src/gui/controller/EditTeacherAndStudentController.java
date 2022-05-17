package gui.controller;

import be.user.User;
import gui.model.UserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherAndStudentController implements Initializable {

    public GridPane parentPaneGridPane;
    public TextField txtFirstName;
    public TextField txtLastName;

    private User student;

    private UserModel userModel;

    private User teacher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if(student != null){
                txtFirstName.setText(student.getFirstNameProperty().get());
                txtLastName.setText(student.getLastNameProperty().get());
            }else if(teacher != null){
                txtFirstName.setText(teacher.getFirstNameProperty().get());
                txtLastName.setText(teacher.getLastNameProperty().get());
            }
        });

    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setStudent(User student){
        this.student = student;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void btnSave(ActionEvent actionEvent) {
        if(student != null){
            student.setFirstName(txtFirstName.getText());
            student.setLastName(txtLastName.getText());
            userModel.editUser(student);
        }else if(teacher != null){
           teacher.setFirstName(txtFirstName.getText());
           teacher.setLastName(txtLastName.getText());
           userModel.editUser(teacher);
        }

        Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
        stage.close();

    }
}
