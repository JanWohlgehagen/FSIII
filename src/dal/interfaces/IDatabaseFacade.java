package dal.interfaces;

import be.*;
import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

/**
 * Specifies the contract for the data access layer in the application.
 */
public interface IDatabaseFacade {

    /** USER */

    Credential checkCredential(String userName);

    User getUserById(int id);

    List<User> getAllUser();

    User newUser(User newUser);

    void deleteUser(User user);

    void editUser(User user);

    void createNewLoginUser(Credential credential);

    void updateLoginName(Credential credential);

    void updatePassword(Credential credential);

    /** CLASS */

    List<WClass> getAllClass();

    WClass createClass(WClass wClass);

    void deleteClass(WClass wClass);

    void editClass(WClass wClass);

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

    //Citizen DAL Functions

    Citizen createCitizen(Citizen citizen);

    List<Citizen> getAllCitizens();

    List<Citizen> getAllTemplates();

    void updateCitizen(Citizen citizen);

    void addStudentToCitizen(Citizen citizen);

    void deleteCitizen(Citizen citizen);

    void updateGenerelleOplysninger(Citizen citizen);

    Citizen getGenerelleOplysninger(Citizen citizen);

    /** Tilstande Functions */


    void updateFunktiontilstand(Citizen citizen);

    HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen);

    FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen);

    void updateHelbredstilstand(Citizen citizen);
}
