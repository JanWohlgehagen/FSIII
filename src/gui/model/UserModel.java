package gui.model;

import be.Credential;
import be.WClass;
import be.user.User;
import be.user.UserType;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.List;

public class UserModel {

    private final IManagerFacade managerFacade;

    private ObservableList<User> allStudent = FXCollections.observableArrayList();
    private ObservableList<User> allTeacher  = FXCollections.observableArrayList();
    private ObservableList<User> allAdmin  = FXCollections.observableArrayList();
    private ObservableList<WClass> allClass  = FXCollections.observableArrayList();
    private ObservableList<User> studentInClass = FXCollections.observableArrayList();
    private ObservableList<User> teacherInClass = FXCollections.observableArrayList();
    private ObservableList<User> teacherAndStudentInClass = FXCollections.observableArrayList();

    public UserModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<User> allUsers(){
        return managerFacade.getAllUser();
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

    public ObservableList<WClass> getAllClass(){
        if(allClass.isEmpty()){
            allClass.addAll(managerFacade.getAllClass());
        }
        return allClass;
    }

    public ObservableList<User> getAllStudent() {
        if(allStudent.isEmpty()){
            allStudent.addAll(managerFacade.getAllStudent());
        }
        return allStudent;
    }

    public ObservableList<User> getAllTeacher() {
        if(allTeacher.isEmpty()){
            allTeacher.addAll(managerFacade.getAllTeacher());
        }
        return allTeacher;
    }

    public ObservableList<User> getAllAdmin() {
        if(allAdmin.isEmpty()){
            allAdmin.addAll(managerFacade.getAllAdmin());
        }
        return allAdmin;
    }

    public void studentInClass(WClass wClass){
        studentInClass.clear();
        studentInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void teacherInClass(WClass wClass){
        teacherInClass.clear();
        teacherInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
    }

    public void teacherAndStudentInClass(WClass wClass){
        teacherAndStudentInClass.clear();
        teacherAndStudentInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
        teacherAndStudentInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void addStudentToClass(User user, WClass wClass) {
        try {
            managerFacade.addStudentToClass(user, wClass);
            studentInClass.add(user);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Denne person er allerede i klassen.", ButtonType.OK);
            alert.show();
        }

    }
    public void addTeacherToClass(User teacher, WClass wClass){
        try {
            managerFacade.addTeacherToClass(teacher, wClass);
            teacherInClass.add(teacher);
        }catch (Exception e){
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
        if(newUser.getUserType().equals(UserType.STUDENT)){
            allStudent.add(managerFacade.newUser(newUser));
        }else if(newUser.getUserType().equals(UserType.TEACHER)){
           allTeacher.add(managerFacade.newUser(newUser));
        }
    }

    public void createNewLoginUser(Credential credential){
        managerFacade.createNewLoginUser(credential);
    }

    public void editLoginUser(Credential credential){
        managerFacade.editLoginUser(credential);
    }

    public void removeUser(User user){
        if(user.getUserType().equals(UserType.STUDENT)){
            allStudent.remove(user);
        }else if(user.getUserType().equals(UserType.TEACHER)){
            allTeacher.remove(user);
        }
        managerFacade.deleteUser(user);
    }

    public void editUser(User user) {
        managerFacade.editUser(user);

        if(allTeacher.contains(user)){
            allTeacher.clear();
            allTeacher.addAll(managerFacade.getAllTeacher());
        }else if (allStudent.contains(user)){
            allStudent.clear();
            allStudent.addAll(managerFacade.getAllStudent());
        }
    }

    public void createClass(WClass wClass){
        allClass.add(managerFacade.createClass(wClass));
    }

    public void deleteClass(WClass wClass){
        managerFacade.deleteClass(wClass);
        allClass.remove(wClass);
    }

    public void editClass(WClass wClass){
        managerFacade.editClass(wClass);
        allClass.clear();
        allClass.addAll(managerFacade.getAllClass());
    }
}
