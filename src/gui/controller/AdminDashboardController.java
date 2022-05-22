package gui.controller;

import be.*;
import be.User;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.model.UserModel;
import gui.util.CreateAndEditClassScene;
import gui.util.CreateTeacherAndStudentScene;
import gui.util.EditTeacherAndStudentScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button btnInfoTeacher;
    @FXML
    private Button btnInfoStudent;

    @FXML
    private ComboBox<WClass> comboBoxStudentClass;
    @FXML
    private ComboBox<WClass> comboboxTeacherClass;

    @FXML
    private TextField txtSearchBarTeacher;
    @FXML
    private TextField txtSearchBarStudent;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tvAllStudent.setItems(userModel.getAllStudent());
        btnInfoStudent.setTooltip(getTooltipForAddUserToClass());
        tcAllStudentName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllStudentRolle.setCellValueFactory(param -> param.getValue().getUserTypeStringProperty());

        tvStudentInClass.setItems(userModel.getStudentInClass());
        tcStudentInClassName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcStudentInClassRolle.setCellValueFactory(param -> param.getValue().getUserTypeStringProperty());

        tvAllTeacher.setItems(userModel.getAllTeacher());
        btnInfoTeacher.setTooltip(getTooltipForAddUserToClass());
        tcAllTeacherName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcAllTeacherRolle.setCellValueFactory(param -> param.getValue().getUserTypeStringProperty());

        tvTeacherInClass.setItems(userModel.getTeacherInClass());
        tcTeacherInClassName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcTeacherInClassRolle.setCellValueFactory(param -> param.getValue().getUserTypeStringProperty());

        tvClass.setItems(userModel.getAllClass());
        tcClassName.setCellValueFactory(param -> param.getValue().getNameProperty());

        tvUserInClass.setItems(userModel.getTeacherAndStudentInClass());
        tcUserName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tcUserRolle.setCellValueFactory(param -> param.getValue().getUserTypeStringProperty());

        comboBoxStudentClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                userModel.studentInClass(newValue);
            }
        });
        comboboxTeacherClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                userModel.teacherInClass(newValue);
            }
        });

        tvClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                userModel.teacherAndStudentInClass(newValue);
            }
        });

        txtSearchBarStudent.textProperty().addListener((observableValue, oldValue, newValue) -> {
            userModel.searchStudent(newValue);
        });

        txtSearchBarTeacher.textProperty().addListener((observableValue, oldValue, newValue) -> {
            userModel.searchTeacher(newValue);
        });


    }

    public void handleNewTeacher(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateTeacherAndStudentController> createTeacherAndStudentScene = new CreateTeacherAndStudentScene();
        createTeacherAndStudentScene.loadNewScene(new Stage());
        CreateTeacherAndStudentController createTeacherAndStudentController = createTeacherAndStudentScene.getController();
        createTeacherAndStudentController.setUserModel(userModel);
        createTeacherAndStudentController.setTeacher(true);
    }

    public void handleEditTeacher(ActionEvent actionEvent) throws IOException {
        User teacher = tvAllTeacher.getSelectionModel().getSelectedItem();

        ISceneLoader<EditTeacherAndStudentController> editTeacherAndStudentScene = new EditTeacherAndStudentScene();
        editTeacherAndStudentScene.loadNewScene(new Stage());
        EditTeacherAndStudentController editTeacherAndStudentController = editTeacherAndStudentScene.getController();
        editTeacherAndStudentController.setUserModel(userModel);
        editTeacherAndStudentController.setTeacher(teacher);

    }

    public void handleDeleteTeacher(ActionEvent actionEvent) {
        User teacher = tvAllTeacher.getSelectionModel().getSelectedItem();
        userModel.removeUser(teacher);
    }

    public void handleNewStudent(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateTeacherAndStudentController> createTeacherAndStudentScene = new CreateTeacherAndStudentScene();
        createTeacherAndStudentScene.loadNewScene(new Stage());
        CreateTeacherAndStudentController createTeacherAndStudentController = createTeacherAndStudentScene.getController();
        createTeacherAndStudentController.setUserModel(userModel);
        createTeacherAndStudentController.setStudent(true);
    }

    public void handleEditStudent(ActionEvent actionEvent) throws IOException {
        User student = tvAllStudent.getSelectionModel().getSelectedItem();

        ISceneLoader<EditTeacherAndStudentController> editTeacherAndStudentScene = new EditTeacherAndStudentScene();
        editTeacherAndStudentScene.loadNewScene(new Stage());
        EditTeacherAndStudentController editTeacherAndStudentController = editTeacherAndStudentScene.getController();
        editTeacherAndStudentController.setUserModel(userModel);
        editTeacherAndStudentController.setStudent(student);
    }

    public void handleDeleteStudent(ActionEvent actionEvent) {
        User student = tvAllStudent.getSelectionModel().getSelectedItem();
        userModel.removeUser(student);
    }

    public void handleMouseAddStudentToClass(MouseEvent mouseEvent) {
        User student = tvAllStudent.getSelectionModel().getSelectedItem();
        WClass wClass = comboBoxStudentClass.getSelectionModel().getSelectedItem();

        if (mouseEvent.isControlDown() && student != null && wClass != null) {
            userModel.addStudentToClass(student, wClass);
        } else if (mouseEvent.isControlDown() && wClass == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en klasse først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleMouseAddTeacherToClass(MouseEvent mouseEvent) {
        User teacher = tvAllTeacher.getSelectionModel().getSelectedItem();
        WClass wClass = comboboxTeacherClass.getSelectionModel().getSelectedItem();

        if (mouseEvent.isControlDown() && teacher != null && wClass != null) {
            userModel.addTeacherToClass(teacher, wClass);
        } else if (mouseEvent.isControlDown() && wClass == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en klasse først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleMouseRemoveStudentFromClass(MouseEvent mouseEvent) {
        User student = tvStudentInClass.getSelectionModel().getSelectedItem();
        WClass wClass = comboBoxStudentClass.getSelectionModel().getSelectedItem();
        if (student != null && wClass != null) {
            userModel.removeStudentFromClass(student, wClass);
        }
    }

    public void handleMouseRemoveTeacherFromClass(MouseEvent mouseEvent) {
        User teacher = tvTeacherInClass.getSelectionModel().getSelectedItem();
        WClass wClass = comboboxTeacherClass.getSelectionModel().getSelectedItem();
        if (teacher != null && wClass != null) {
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

    public void handleNewClass(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditClassController> createAndEditClassScene = new CreateAndEditClassScene();
        createAndEditClassScene.loadNewScene(new Stage());
        CreateAndEditClassController createAndEditClassController = createAndEditClassScene.getController();
        createAndEditClassController.setUserModel(userModel);
    }

    public void handleEditClass(ActionEvent actionEvent) throws IOException {
        ISceneLoader<CreateAndEditClassController> createAndEditClassScene = new CreateAndEditClassScene();
        createAndEditClassScene.loadNewScene(new Stage());
        CreateAndEditClassController createAndEditClassController = createAndEditClassScene.getController();
        createAndEditClassController.setUserModel(userModel);
        if (tvClass.getSelectionModel().getSelectedItem() != null) {
            createAndEditClassController.setClass(tvClass.getSelectionModel().getSelectedItem());
        }
    }

    public void handleDeleteClass(ActionEvent actionEvent) {
        userModel.deleteClass(tvClass.getSelectionModel().getSelectedItem());
    }

    public Tooltip getTooltipForAddUserToClass() {
        Tooltip tooltip = new Tooltip("For at tilføje en person til en klasse, så skal du holde 'Control-knappen' nede og derefter klikke på en person.");
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setShowDelay(Duration.millis(0));
        tooltip.setWrapText(true);
        tooltip.setPrefWidth(175);
        return tooltip;
    }
}
