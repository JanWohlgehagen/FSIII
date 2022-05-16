package gui.model;

import be.WClass;
import be.user.Teacher;
import be.user.User;
import be.user.UserType;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserModel {

    private final IManagerFacade managerFacade;

    private ObservableList<User> allStudent = FXCollections.observableArrayList();
    private ObservableList<User> allTeacher  = FXCollections.observableArrayList();
    private ObservableList<User> allAdmin  = FXCollections.observableArrayList();
    private ObservableList<User> userInClass = FXCollections.observableArrayList();

    public UserModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<User> allUsers(){
        return managerFacade.getAllUser();
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
        userInClass.clear();
        userInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void teacherInClass(WClass wClass){
        userInClass.clear();
        userInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
    }

    public void teacherAndStudentInClass(WClass wClass){
        userInClass.clear();
        userInClass.addAll(managerFacade.getAllTeacherInClass(wClass));
        userInClass.addAll(managerFacade.getAllStudentInClass(wClass));
    }

    public void addStudentToClass(User user, WClass wClass){
        managerFacade.addStudentToClass(user, wClass);
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
