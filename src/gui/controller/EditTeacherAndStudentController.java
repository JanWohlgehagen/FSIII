package gui.controller;

import be.user.User;
import gui.model.UserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherAndStudentController implements Initializable {


    @FXML
    private GridPane parentPaneGridPane;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtLoginName;
    @FXML
    private TextField txtPassword;

    private User student;

    private UserModel userModel;

    private User teacher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if (student != null) {
                txtFirstName.setText(student.getFirstNameProperty().get());
                txtLastName.setText(student.getLastNameProperty().get());
                txtLoginName.setText(student.getCredential().getUserName());
            } else if (teacher != null) {
                txtFirstName.setText(teacher.getFirstNameProperty().get());
                txtLastName.setText(teacher.getLastNameProperty().get());
                txtLoginName.setText(teacher.getCredential().getUserName());
            }
        });

    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void btnSave(ActionEvent actionEvent) {
        if (student != null) {
            student.setFirstName(txtFirstName.getText());
            student.setLastName(txtLastName.getText());
            student.getCredential().setUserId(student.getIdProperty().get());
            student.getCredential().setUserName(txtLoginName.getText());
            student.getCredential().setPassword(txtPassword.getText());
            userModel.editUser(student);
            userModel.editLoginUser(student.getCredential());
        } else if (teacher != null) {
            teacher.setFirstName(txtFirstName.getText());
            teacher.setLastName(txtLastName.getText());
            teacher.getCredential().setUserId(teacher.getIdProperty().get());
            teacher.getCredential().setUserName(txtLoginName.getText());
            teacher.getCredential().setPassword(txtPassword.getText());

            userModel.editUser(teacher);
            userModel.editLoginUser(teacher.getCredential());
        }

        Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
        stage.close();

    }
}
