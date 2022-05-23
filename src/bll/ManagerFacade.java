package bll;

import be.*;
import be.User;
import bll.Interfaces.IManagerFacade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DatabaseFacade;

import java.io.IOException;
import java.util.List;

public class ManagerFacade implements IManagerFacade {

    private final CredentialManager credentialManager;
    private final CaseManager caseManager;
    private final CitizenManager citizenManager;
    private final UserManager userManager;

    public ManagerFacade(DatabaseFacade databaseFacade) throws IOException {
        credentialManager = new CredentialManager(databaseFacade);
        caseManager = new CaseManager(databaseFacade);
        citizenManager = new CitizenManager(databaseFacade);
        userManager = new UserManager(databaseFacade);
    }

    /***************************************************/
    /****************** Credential/User ****************/
    /***************************************************/

    @Override
    public User loginCredential(String userName, String userPassword) {
        return userManager.loginCredential(userName, userPassword);
    }

    @Override
    public void createNewLoginUser(Credential credential) {
        userManager.createNewLoginUser(credential);
    }

    @Override
    public void editLoginUser(Credential credential) {
        userManager.editLoginUser(credential);
    }

    @Override
    public List<User> getAllUser() {
        return userManager.getAllUser();
    }

    @Override
    public List<User> getAllStudent() {
        return userManager.getAllStudent();
    }

    @Override
    public List<User> getAllTeacher() {
        return userManager.getAllTeacher();
    }

    @Override
    public List<User> getAllAdmin() {
        return userManager.getAllAdmin();
    }

    @Override
    public User newUser(User newUser) {
        return userManager.newUser(newUser);
    }

    @Override
    public void deleteUser(User user) {
        userManager.deleteUser(user);
    }

    @Override
    public void editUser(User user) {
        userManager.editUser(user);
    }

    /***************************************************/
    /********************* Class ***********************/
    /***************************************************/

    @Override
    public WClass createClass(WClass wClass) {
        return userManager.createClass(wClass);
    }

    @Override
    public void deleteClass(WClass wClass) {
        userManager.deleteClass(wClass);
    }

    @Override
    public void editClass(WClass wClass) {
        userManager.editClass(wClass);
    }

    @Override
    public List<WClass> getAllClass() {
        return userManager.allClass();
    }

    @Override
    public List<User> getAllStudentInClass(WClass wClass) {
        return userManager.getAllStudentInClass(wClass);
    }

    @Override
    public List<User> getAllTeacherInClass(WClass wClass) {
        return userManager.getAllTeacherInClass(wClass);
    }

    @Override
    public void addStudentToClass(User user, WClass wClass) throws SQLServerException {
        userManager.addStudentToClass(user, wClass);
    }

    @Override
    public void addTeacherToClass(User teacher, WClass wClass) throws SQLServerException {
        userManager.addTeacherToClass(teacher, wClass);
    }

    @Override
    public void removeStudentFromClass(User student, WClass wClass) {
        userManager.removeStudentFromClass(student, wClass);
    }

    @Override
    public void removeTeacherFromClass(User teacher, WClass wClass) {
        userManager.removeTeacherFromClass(teacher, wClass);
    }


    /***************************************************/
    /******************** Case *************************/
    /***************************************************/


    @Override
    public List<Case> getAllCasesOnCitizen(int citizenid) {
        return caseManager.getAllCasesOnCitizen(citizenid);
    }

    @Override
    public void updateCaseOnCitizen(int citizenID, Case selectCase) {
        caseManager.updateCaseOnCitizen(citizenID, selectCase);
    }

    @Override
    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        caseManager.deleteCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public Case createCaseOnCitizen(Case newCase) {
        return caseManager.createCaseOnCitizen(newCase);
    }

    /***************************************************/
    /******************** Citizen **********************/
    /***************************************************/

    @Override
    public List<Citizen> getAllCitizen() {
        return citizenManager.getAllCitizen();
    }

    @Override
    public List<Citizen> getAllTemplates() {
        return citizenManager.getAllTemplates();
    }

    @Override
    public Citizen createCitizen(Citizen citizen) {
        return citizenManager.createCitizen(citizen);
    }

    @Override
    public void addStudentToCitizen(Citizen citizen) {
        citizenManager.addStudentToCitizen(citizen);
    }

    @Override
    public void deleteCitizen(Citizen citizen) {
        citizenManager.deleteCitizen(citizen);
    }

    @Override
    public void updateSagsoplysninger(Citizen citizen) {
        citizenManager.updateGenerelleOplysninger(citizen);
        citizenManager.updateCitizen(citizen);
        citizenManager.updateFunktionstilstand(citizen);
        citizenManager.updateHelbredstilstand(citizen);
    }

    @Override
    public Citizen getGenerelleOplysninger(Citizen citizen) {
        return citizenManager.getGenerelleOplysninger(citizen);
    }

    /***************************************************/
    /******************* Tilstande *********************/
    /***************************************************/

    @Override
    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen) {
        return citizenManager.getFunktionstilstandOnCitizen(citizen);
    }

    @Override
    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen) {
        return citizenManager.getHelbredstilstandOnCitizen(citizen);
    }

    @Override
    public void updateHelbredstilstand(Citizen citizen) {
        citizenManager.updateHelbredstilstand(citizen);
    }
}
