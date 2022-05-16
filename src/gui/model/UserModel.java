package gui.model;

import be.WClass;
import be.user.Teacher;
import be.user.User;
import be.user.UserType;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    public void removeStudent(User user){
        allStudent.remove(user);
    }

    public void addTeacher(Teacher teacher){
        allTeacher.add(teacher);
    }

    public void removeTeacher(Teacher teacher){
        allTeacher.remove(teacher);
    }
}
