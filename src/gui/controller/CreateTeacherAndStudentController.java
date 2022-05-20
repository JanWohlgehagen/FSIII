package gui.controller;

import be.Credential;
import be.user.User;
import gui.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTeacherAndStudentController implements Initializable {


    @FXML
    private GridPane parentPaneGridPane;

    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLoginName;
    @FXML
    private TextField txtPassword;

    private boolean isStudent = false;

    private boolean isTeacher = false;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public void btnSave(ActionEvent actionEvent) {
        if (!txtFirstName.getText().isBlank() && !txtLastName.getText().isBlank() && !txtLoginName.getText().isBlank() && !txtPassword.getText().isBlank()) {
            User newUser = new User(txtFirstName.getText(), txtLastName.getText());


            if (isTeacher) {
                newUser.setUserType("TEACHER");
                userModel.newUser(newUser);
            } else if (isStudent) {
                newUser.setUserType("STUDENT");
                userModel.newUser(newUser);
            }
            newUser.setCredential(new Credential(newUser.getIdProperty().get(), txtLoginName.getText(), txtPassword.getText()));
            userModel.createNewLoginUser(newUser.getCredential());
            Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Der er ikke indsat et name.", ButtonType.OK);
            alert.show();
        }
    }
}
