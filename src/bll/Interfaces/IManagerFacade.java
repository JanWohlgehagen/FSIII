package bll.Interfaces;

import be.*;
import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface IManagerFacade {

    /** USER */

    User loginCredential(String userName, String userPassword);

    void createNewLoginUser(Credential credential);

    void editLoginUser(Credential credential);

    List<User> getAllStudent();

    List<User> getAllTeacher();

    User newUser(User newUser);

    void deleteUser(User user);

    void editUser(User user);

    /** Class */

    WClass createClass(WClass wClass);

    void editClass(WClass wClass);

    void deleteClass(WClass wClass);

    List<WClass> getAllClass();

    List<User> getAllStudentInClass(WClass wClass);

    List<User> getAllTeacherInClass(WClass wClass);

    void addStudentToClass(User user, WClass wClass) throws SQLServerException;

    void addTeacherToClass(User teacher, WClass wClass) throws SQLServerException;

    void removeStudentFromClass(User student, WClass wClass);

    void removeTeacherFromClass(User teacher, WClass wClass);


    /** CASE */

    List<Case> getAllCasesOnCitizen(int citizenid);

    void updateCaseOnCitizen(int citizenID, Case selectCase);

    void deleteCaseOnCitizen(int citizenID, int caseID);

    Case createCaseOnCitizen(Case newCase);

    /** CITIZEN */

    List<Citizen> getAllCitizen();

    List<Citizen> getAllTemplates();

    Citizen createCitizen(Citizen citizen);

    void addStudentToCitizen(Citizen citizen);

    void deleteCitizen(Citizen citizen);

    void updateSagsoplysninger(Citizen citizen);

    Citizen getGenerelleOplysninger(Citizen citizen);


    //FUNKTIONSTILSTAND
    FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen);

    //HELBREDSTILSTAND
    HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen);

}
