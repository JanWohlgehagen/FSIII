package gui.model;

import be.Credential;
import be.WClass;
import be.User;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import bll.Seachers.UserSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private final IManagerFacade managerFacade;

    private ObservableList<User> allStudent = FXCollections.observableArrayList();
    private List<User> allStudentCache = new ArrayList<>(); //For searching
    private List<User> allTeacherCache = new ArrayList<>(); //For searching
    private ObservableList<User> allTeacher = FXCollections.observableArrayList();
    private ObservableList<WClass> allClass = FXCollections.observableArrayList();
    private ObservableList<User> studentInClass = FXCollections.observableArrayList();
    private ObservableList<User> teacherInClass = FXCollections.observableArrayList();
    private ObservableList<User> teacherAndStudentInClass = FXCollections.observableArrayList();
    private UserSearcher userSearcher;


    public UserModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
        userSearcher = new UserSearcher();
    }

    public User checkCredential(String userName, String password) {
        return managerFacade.loginCredential(userName, password);
    }

    public ObservableList<User> getStudentInClass() {
        return studentInClass;
    }

    public ObservableList<User> getTeacherInClass() {
        return teacherInClass;
    }

    public ObservableList<User> getTeacherAndStudentInClass() {
        return teacherAndStudentInClass;
    }

    public ObservableList<WClass> getAllClass() {
        if (allClass.isEmpty()) {
            allClass.addAll(managerFacade.getAllClass());
        }
        return allClass;
    }

    public ObservableList<User> getAllStudent() {
        if (allStudent.isEmpty()) {
            allStudent.addAll(managerFacade.getAllStudent());
        }
        allStudentCache.addAll(allStudent);
        return allStudent;
    }

    public ObservableList<User> getAllTeacher() {
        if (allTeacher.isEmpty()) {
            allTeacher.addAll(managerFacade.getAllTeacher());
        }
        allTeacherCache.addAll(allTeacher);
        return allTeacher;
    }

    public void studentInClass(WClass wClass) {
        studentInClass.clear();
        studentInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void teacherInClass(WClass wClass) {
        teacherInClass.clear();
        teacherInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
    }

    public void teacherAndStudentInClass(WClass wClass) {
        teacherAndStudentInClass.clear();
        teacherAndStudentInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
        teacherAndStudentInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void addStudentToClass(User user, WClass wClass) {
        try {
            managerFacade.addStudentToClass(user, wClass);
            studentInClass.add(user);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Denne person er allerede i klassen.", ButtonType.OK);
            alert.show();
        }

    }

    public void addTeacherToClass(User teacher, WClass wClass) {
        try {
            managerFacade.addTeacherToClass(teacher, wClass);
            teacherInClass.add(teacher);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Denne person er allerede i klassen.", ButtonType.OK);
            alert.show();
        }
    }

    public void removeStudentFromClass(User student, WClass wClass) {
        managerFacade.removeStudentFromClass(student, wClass);
        studentInClass.remove(student);
    }

    public void removeTeacherFromClass(User teacher, WClass wClass) {
        managerFacade.removeTeacherFromClass(teacher, wClass);
        teacherInClass.remove(teacher);
    }

    public void newUser(User newUser) {
        if (newUser.getUserType().equals(User.UserType.STUDENT)) {
            allStudent.add(managerFacade.newUser(newUser));
        } else if (newUser.getUserType().equals(User.UserType.TEACHER)) {
            allTeacher.add(managerFacade.newUser(newUser));
        }
    }

    public void createNewLoginUser(Credential credential) {
        managerFacade.createNewLoginUser(credential);
    }

    public void editLoginUser(Credential credential) {
        managerFacade.editLoginUser(credential);
    }

    public void removeUser(User user) {
        if (user.getUserType().equals(User.UserType.STUDENT)) {
            allStudent.remove(user);
        } else if (user.getUserType().equals(User.UserType.TEACHER)) {
            allTeacher.remove(user);
        }
        managerFacade.deleteUser(user);
    }

    public void editUser(User user) {
        managerFacade.editUser(user);

        if (allTeacher.contains(user)) {
            allTeacher.clear();
            allTeacher.addAll(managerFacade.getAllTeacher());
        } else if (allStudent.contains(user)) {
            allStudent.clear();
            allStudent.addAll(managerFacade.getAllStudent());
        }
    }

    public void createClass(WClass wClass) {
        allClass.add(managerFacade.createClass(wClass));
    }

    public void deleteClass(WClass wClass) {
        managerFacade.deleteClass(wClass);
        allClass.remove(wClass);
    }

    public void editClass(WClass wClass) {
        managerFacade.editClass(wClass);
        allClass.clear();
        allClass.addAll(managerFacade.getAllClass());
    }

    public void searchStudent(String query) {
        if (query.isBlank() || query.isEmpty()) {
            allStudent.clear();
            allStudent.addAll(allStudentCache);
        } else {
            allStudent.clear();
            allStudent.addAll(userSearcher.search(allStudentCache, query));
        }
    }

    public void searchTeacher(String query) {
        if (query.isBlank() || query.isEmpty()) {
            allTeacher.clear();
            allTeacher.addAll(allTeacherCache);
        } else {
            allTeacher.clear();
            allTeacher.addAll(userSearcher.search(allTeacherCache, query));
        }
    }
}
