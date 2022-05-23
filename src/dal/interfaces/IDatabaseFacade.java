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

    public List<User> getAllUser();

    public User newUser(User newUser);

    void deleteUser(User user);

    void editUser(User user);

    void createNewLoginUser(Credential credential);

    void updateLoginName(Credential credential);

    void updatePassword(Credential credential);

    /** CLASS */

    public List<WClass> getAllClass();

    public WClass createClass(WClass wClass);

    public void deleteClass(WClass wClass);

    void editClass(WClass wClass);

    public List<User> getAllStudentInClass(WClass wClass);

    public List<User> getAllTeacherInClass(WClass wClass);

    public void addStudentToClass(User user, WClass wClass) throws SQLServerException;

    public void addTeacherToClass(User teacher, WClass wClass) throws SQLServerException;

    public void removeStudentFromClass(User student, WClass wClass);

    public void removeTeacherFromClass(User teacher, WClass wClass);

    /** CASE */

    public List<Case> getAllCasesOnCitizen(int citizenid);

    public Case getCaseOnCitizen(int citizenID, int caseID);

    public void updateCaseOnCitizen(int citizenID, Case selectCase);

    public void deleteCaseOnCitizen(int citizenID, int caseID);

    public Case createCaseOnCitizen(Case newCase);

    //Citizen DAL Functions

    public Citizen createCitizen(Citizen citizen);

    public List<Citizen> getAllCitizens();

    public List<Citizen> getAllTemplates();

    public void updateCitizen(Citizen citizen);

    public void addStudentToCitizen(Citizen citizen);

    public void deleteCitizen(Citizen citizen);

    public void updateGenerelleOplysninger(Citizen citizen);

    public Citizen getGenerelleOplysninger(Citizen citizen);

    /** Tilstande Functions */


    public void updateFunktiontilstand(Citizen citizen);

    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen);

    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen);

    public void updateHelbredstilstand(Citizen citizen);


}
