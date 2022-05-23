package bll.Interfaces;

import be.*;
import be.user.User;
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

    public List<Borger> getAllCitizen();

    public List<Borger> getAllTemplates();

    public Borger createCitizen(Borger borger);

    public void updateCitizen(Borger borger);

    public void addStudentToCitizen(Borger borger);

    public void deleteCitizen(Borger borger);

    public void updateSagsoplysninger(Borger borger);

    public Borger getGenerelleOplysninger(Borger borger);


    /** FUNKTIONSTILSTAND */

    public List<String> getFunktionstilstandsList();

    public List<String> getFunktionstilstandsUnderkategoriList();

    public Funktionstilstand getEmptyFunktionsTilstand();

    /** HELBREDSTILSTAND */

    public Helbredstilstand getEmptyHelbredsTilstand();

    public void updateHelbredstilstand(Borger borger);

    public List<String> getHelbredstilstandsList();

    public List<String> getHelbredstilstandsUnderkategoriList();

    public void getTilstande(Borger borger);
}
