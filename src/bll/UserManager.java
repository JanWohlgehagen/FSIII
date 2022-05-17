package bll;

import be.WClass;
import be.user.User;
import be.user.UserType;
import bll.Interfaces.IManagerFacade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DatabaseFacade;
import dal.interfaces.IDatabaseFacade;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private IDatabaseFacade databaseFacade;
    private List<User> allUserInDatabase;
    private List<User> allTeacherInDatabase;
    private List<User> allStudentInDatabase;
    private List<User> allAdminInDatabase;

    public UserManager(DatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
        allUserInDatabase = new ArrayList<>();
        allStudentInDatabase = new ArrayList<>();
        allTeacherInDatabase = new ArrayList<>();
        allAdminInDatabase = new ArrayList<>();
    }

    public List<User> getAllUser() {
        if(allUserInDatabase.isEmpty()) {
            allUserInDatabase = databaseFacade.getAllUser();
            for (User user : allUserInDatabase) {
                switch (user.getUserType()) {
                    case STUDENT -> allStudentInDatabase.add(user);
                    case TEACHER -> allTeacherInDatabase.add(user);
                    case ADMIN -> allAdminInDatabase.add((user));
                }
            }
        }
        return allUserInDatabase;
    }

    public List<User> getAllStudent(){
        if(allStudentInDatabase.isEmpty()){
            getAllUser();
        }
        return allStudentInDatabase;
    }

    public List<User> getAllTeacher(){
       if(allTeacherInDatabase.isEmpty()){
           getAllUser();
       }
        return allTeacherInDatabase;
    }

    public List<User> getAllAdmin(){
       if(allAdminInDatabase.isEmpty()){
           getAllUser();
       }
        return allAdminInDatabase;
    }

    public WClass createClass(WClass wClass){
        return databaseFacade.createClass(wClass);
    }

    public void deleteClass(WClass wClass){
        databaseFacade.deleteClass(wClass);
    }

    public List<WClass> allClass(){
        return databaseFacade.getAllClass();
    }

    public List<User> getAllStudentInClass(WClass wClass){
        return databaseFacade.getAllStudentInClass(wClass);
    }

    public List<User> getAllTeacherInClass(WClass wClass){
        return databaseFacade.getAllTeacherInClass(wClass);
    }

    public void addStudentToClass(User user, WClass wClass) throws SQLServerException {
        databaseFacade.addStudentToClass(user, wClass);
    }
}
