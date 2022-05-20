package bll;

import be.*;
import be.user.User;
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
    public Case getCaseOnCitizen(int citizenID, int caseID) {
        return caseManager.getCaseOnCitizen(citizenID, caseID);
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
    public List<Borger> getAllCitizen() {
        return citizenManager.getAllCitizen();
    }

    @Override
    public List<Borger> getAllTemplates() {
        return citizenManager.getAllTemplates();
    }

    @Override
    public Borger createCitizen(Borger borger) {
        return citizenManager.createCitizen(borger);
    }

    @Override
    public void addStudentToCitizen(Borger borger) {
        citizenManager.addStudentToCitizen(borger);
    }

    @Override
    public void deleteCitizen(Borger borger) {
        citizenManager.deleteCitizen(borger);
    }

    @Override
    public void updateSagsoplysninger(Borger borger) {
        citizenManager.updateGenerelleOplysninger(borger);
        citizenManager.updateCitizen(borger);
        citizenManager.updateFunktionstilstand(borger);
        citizenManager.updateHelbredstilstand(borger);
    }

    @Override
    public Borger getGenerelleOplysninger(Borger borger) {
        return citizenManager.getGenerelleOplysninger(borger);
    }

    /***************************************************/
    /******************* Tilstande *********************/
    /***************************************************/

    @Override
    public void createEmptyTilstande(Borger borger) {
        citizenManager.createEmptyTilstande(borger);
    }

    @Override
    public Funktionstilstand getEmptyFunktionsTilstand() {
        return citizenManager.getEmptyFunktionsTilstand();
    }

    @Override
    public Funktionstilstand getTitleFunktionsTilstand() {
        return caseManager.getTitleFunktionsTilstand();
    }

    @Override
    public Funktionstilstand getFunktionstilstandOnCitizen(Borger borger) {
        return citizenManager.getFunktionstilstandOnCitizen(borger);
    }

    @Override
    public Helbredstilstand getEmptyHelbredsTilstand() {
        return citizenManager.getEmptyHelbredsTilstand();
    }

    @Override
    public Helbredstilstand getTitleHelbredsTilstand() {
        return caseManager.getTitleHelbredsTilstand();
    }

    @Override
    public Helbredstilstand getHelbredstilstandOnCitizen(Borger borger) {
        return citizenManager.getHelbredstilstandOnCitizen(borger);
    }

    @Override
    public void updateHelbredstilstand(Borger borger) {
        citizenManager.updateHelbredstilstand(borger);
    }
}
