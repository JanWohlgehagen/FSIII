package gui.model;

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

    public UserModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
        sortUser(managerFacade.getAllUser());
    }

    public List<User> allUsers(){
        return managerFacade.getAllUser();
    }
    private void sortUser(List<User> users){
        for (User user: users) {
            switch (user.getUserType()){
                case STUDENT -> allStudent.add(user);
                case TEACHER -> allTeacher.add(user);
                case ADMIN -> allAdmin.add(user);
            }
        }
    }

    public ObservableList<User> getAllStudent() {
        return allStudent;
    }

    public void addStudent(User user){
        allStudent.add(user);
    }

    public void removeStudent(User user){
        allStudent.remove(user);
    }

    public ObservableList<User> getAllTeacher() {
        return allTeacher;
    }

    public void addTeacher(Teacher teacher){
        allTeacher.add(teacher);
    }

    public void removeTeacher(Teacher teacher){
        allTeacher.remove(teacher);
    }
}
