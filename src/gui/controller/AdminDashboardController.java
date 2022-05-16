package gui.controller;

import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import be.Helbredstilstand;
import be.HelbredstilstandsUnderkategori;
import be.user.User;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.model.UserModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TabPane tabPaneParent;

    @FXML
    private TableView<User> tvAllstudent;
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
    private TableView<User> tvClass;
    @FXML
    private TableColumn<User, String> tcClassName;
    @FXML
    private TableView<User> tvUserInClass;
    @FXML
    private TableColumn<User, String> tcUserName;
    @FXML
    private TableColumn<User, String> tcUserRolle;

    @FXML
    private ComboBox comboBoxStudentClass;
    @FXML
    private ComboBox comboboxTeacherClass;


    private  List<User> allUser = new ArrayList<>();
    TableRow<User> row;
    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tvAllstudent.setItems(userModel.getAllStudent());
        tcAllStudentName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllStudentRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

        tvAllTeacher.setItems(userModel.getAllTeacher());
        tcAllTeacherName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllTeacherRolle.setCellValueFactory(param -> param.getValue().getUserTypeProperty());

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
    }

    public void handleMouseRemoveStudentFromClass(MouseEvent mouseEvent) {
    }
}
