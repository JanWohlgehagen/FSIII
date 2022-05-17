package gui.controller;

import be.*;
import be.user.User;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TabPane tabPaneParent;

    @FXML
    private TableView<User> tvAllStudent;
    @FXML
    private TableColumn<User, String> tcAllStudentName;
    @FXML
    private TableColumn<User, String> tcAllStudentRolle;
    @FXML
    private TableView<User> tvStudentInClass;
    @FXML
    private TableColumn<User, String> tcStudentInClassName;
    @FXML
    private TableColumn<User, String> tcStudentInClassRolle;


    @FXML
    private TableView<User> tvAllTeacher;
    @FXML
    private TableColumn<User, String> tcAllTeacherName;
    @FXML
    private TableColumn<User, String> tcAllTeacherRolle;
    @FXML
    private TableView<User> tvTeacherInClass;
    @FXML
    private TableColumn<User, String> tcTeacherInClassName;
    @FXML
    private TableColumn<User, String> tcTeacherInClassRolle;

    @FXML
    private TableView<WClass> tvClass;
    @FXML
    private TableColumn<WClass, String> tcClassName;
    @FXML
    private TableView<User> tvUserInClass;
    @FXML
    private TableColumn<User, String> tcUserName;
    @FXML
    private TableColumn<User, String> tcUserRolle;

    @FXML
    private ComboBox<WClass> comboBoxStudentClass;
    @FXML
    private ComboBox<WClass> comboboxTeacherClass;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tvAllStudent.setItems(userModel.getAllStudent());
        tcAllStudentName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllStudentRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        tvStudentInClass.setItems(userModel.getStudentInClass());
        tcStudentInClassName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcStudentInClassRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        tvAllTeacher.setItems(userModel.getAllTeacher());
        tcAllTeacherName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllTeacherRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        tvTeacherInClass.setItems(userModel.getTeacherInClass());
        tcTeacherInClassName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcTeacherInClassRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        tvClass.setItems(userModel.getAllClass());
        tcClassName.setCellValueFactory(param -> param.getValue().getNameProperty());

        tvUserInClass.setItems(userModel.getTeacherAndStudentInClass());
        tcUserName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcUserRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        comboBoxStudentClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                userModel.studentInClass(newValue);
            }
        });
        comboboxTeacherClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                userModel.teacherInClass(newValue);
            }
        });

        tvClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                userModel.teacherAndStudentInClass(newValue);
            }
        });
        }


    public void handleNewTeacher(ActionEvent actionEvent) {
    }

    public void handleEditTeacher(ActionEvent actionEvent) {
    }

    public void handleDeleteTeacher(ActionEvent actionEvent) {
    }

    public void HandleAddTeacherToClass(ActionEvent actionEvent) {
    }

    public void handleRemoveTeacherFromClass(ActionEvent actionEvent) {
    }

    public void handleSaveTeacherClass(ActionEvent actionEvent) {
    }

    public void handleNewStudent(ActionEvent actionEvent) {
    }

    public void handleEditStudent(ActionEvent actionEvent) {
    }

    public void handleDeleteStudent(ActionEvent actionEvent) {
    }

    public void HandleAddStudentToClass(ActionEvent actionEvent) {
    }

    public void handleRemoveStudentFromClass(ActionEvent actionEvent) {
    }

    public void handleSaveStudentClass(ActionEvent actionEvent) {
    }

    public void handleMouseAddStudentToClass(MouseEvent mouseEvent) {
        User student = tvAllStudent.getSelectionModel().getSelectedItem();
        WClass wClass = comboBoxStudentClass.getSelectionModel().getSelectedItem();
        if(student != null && wClass != null) {
            userModel.addStudentToClass(student, wClass);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en klasse først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleMouseAddTeacherToClass(MouseEvent mouseEvent) {
        User teacher = tvAllTeacher.getSelectionModel().getSelectedItem();
        WClass wClass = comboboxTeacherClass.getSelectionModel().getSelectedItem();
        if(teacher != null && wClass != null) {
            userModel.addTeacherToClass(teacher, wClass);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en klasse først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleMouseRemoveStudentFromClass(MouseEvent mouseEvent) {
        User student = tvStudentInClass.getSelectionModel().getSelectedItem();
        WClass wClass = comboBoxStudentClass.getSelectionModel().getSelectedItem();
        if(student != null && wClass != null) {
            userModel.removeStudentFromClass(student, wClass);
        }
    }

    public void handleMouseRemoveTeacherFromClass(MouseEvent mouseEvent) {
        User teacher = tvTeacherInClass.getSelectionModel().getSelectedItem();
        WClass wClass = comboboxTeacherClass.getSelectionModel().getSelectedItem();
        if(teacher != null && wClass != null) {
            userModel.removeTeacherFromClass(teacher, wClass);
        }
    }


    public void handleComboboxSetStudentInClass(Event event) {
        comboBoxStudentClass.getItems().clear();
        comboBoxStudentClass.setItems(userModel.getAllClass());
    }

    public void handleComboboxSetTeacherInClass(Event event) {
        comboboxTeacherClass.getItems().clear();
        comboboxTeacherClass.setItems(userModel.getAllClass());
    }
}
