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

    List<User> getAllUser();

    List<User> getAllStudent();

    List<User> getAllTeacher();

    List<User> getAllAdmin();

    public User newUser(User newUser);

    void deleteUser(User user);

    void editUser(User user);

    /** Class */

    public WClass createClass(WClass wClass);

    void editClass(WClass wClass);

    public void deleteClass(WClass wClass);

    public List<WClass> getAllClass();

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

    /** CITIZEN */

    public List<Citizen> getAllCitizen();

    public List<Citizen> getAllTemplates();

    public Citizen createCitizen(Citizen citizen);

    public void addStudentToCitizen(Citizen citizen);

    public void deleteCitizen(Citizen citizen);

    public void updateSagsoplysninger(Citizen citizen);

    public Citizen getGenerelleOplysninger(Citizen citizen);

    public void createEmptyTilstande(Citizen citizen);


    //FUNKTIONSTILSTAND
    public FunctionAssessment getEmptyFunktionsTilstand();

    public FunctionAssessment getTitleFunktionsTilstand();

    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen);

    //HELBREDSTILSTAND

    public HealthAssessment getEmptyHelbredsTilstand();

    public HealthAssessment getTitleHelbredsTilstand();

    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen);

    public void updateHelbredstilstand(Citizen citizen);

}
