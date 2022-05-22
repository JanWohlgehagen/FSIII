package dal;


import be.*;
import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IDatabaseFacade;

import java.io.IOException;
import java.util.List;

public class DatabaseFacade implements IDatabaseFacade {

    private DBConnecting dbConnecting;
    private DBLoginDAO dbLoginDAO;
    private DBUserDAO dbUserDAO;
    private DBCaseDAO dbCaseDAO;
    private DBCitizenDAO dbCitizenDAO;
    private DBGenerelInformationDAO dbGenerelInformationDAO;
    private DBFunctionAssessmentDAO dbFunctionAssessmentDAO;
    private DBHealthAssessmentDAO dbHealthAssessmentDAO;
    private DBClassDAO dbClassDAO;

    public DatabaseFacade() throws IOException {
        dbConnecting = new DBConnecting();
        dbLoginDAO = new DBLoginDAO(dbConnecting);
        dbUserDAO = new DBUserDAO(dbConnecting);
        dbClassDAO = new DBClassDAO(dbConnecting);
        dbCaseDAO = new DBCaseDAO(dbConnecting);
        dbCitizenDAO = new DBCitizenDAO(dbConnecting);
        dbGenerelInformationDAO = new DBGenerelInformationDAO(dbConnecting);
        dbFunctionAssessmentDAO = new DBFunctionAssessmentDAO(dbConnecting);
        dbHealthAssessmentDAO = new DBHealthAssessmentDAO(dbConnecting);
    }

    @Override
    public Credential checkCredential(String userName) {
        return dbLoginDAO.checkCredential(userName);
    }

    @Override
    public User getUserById(int id) {
        return dbUserDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return dbUserDAO.getAllUser();
    }

    @Override
    public List<WClass> getAllClass() {
        return dbClassDAO.getAllClass();
    }

    /***************************************************/
    /******************** Class ************************/
    /***************************************************/

    @Override
    public WClass createClass(WClass wClass) {
        return dbClassDAO.createClass(wClass);
    }

    @Override
    public void deleteClass(WClass wClass) {
        dbClassDAO.deleteClass(wClass);
    }

    @Override
    public void editClass(WClass wClass) {
        dbClassDAO.editClass(wClass);
    }

    @Override
    public List<User> getAllStudentInClass(WClass wClass) {
        return dbClassDAO.getAllStudentInClass(wClass);
    }

    @Override
    public List<User> getAllTeacherInClass(WClass wClass) {
        return dbClassDAO.getAllTeacherInClass(wClass);
    }

    @Override
    public void addStudentToClass(User student, WClass wClass) throws SQLServerException {
        dbClassDAO.addStudentToClass(student, wClass);
    }

    @Override
    public void addTeacherToClass(User teacher, WClass wClass) throws SQLServerException {
        dbClassDAO.addTeacherToClass(teacher, wClass);
    }

    @Override
    public void removeStudentFromClass(User student, WClass wClass) {
        dbClassDAO.removeStudentFromClass(student, wClass);
    }

    @Override
    public void removeTeacherFromClass(User teacher, WClass wClass) {
        dbClassDAO.removeTeacherFromClass(teacher, wClass);
    }

    @Override
    public User newUser(User newUser) {
        return dbUserDAO.newUser(newUser);
    }

    @Override
    public void deleteUser(User user) {
        dbUserDAO.deleteUser(user);
    }

    @Override
    public void editUser(User user) {
        dbUserDAO.editUser(user);
    }

    @Override
    public void createNewLoginUser(Credential credential) {
        dbUserDAO.createNewLoginUser(credential);
    }

    @Override
    public void updateLoginName(Credential credential) {
        dbUserDAO.updateLoginName(credential);
    }

    @Override
    public void updatePassword(Credential credential) {
        dbUserDAO.updatePassword(credential);
    }

    /***************************************************/
    /******************** Case *************************/
    /***************************************************/

    @Override
    public List<Case> getAllCasesOnCitizen(int citizenid) {
        return dbCaseDAO.getAllCasesOnCitizen(citizenid);
    }

    @Override
    public Case getCaseOnCitizen(int citizenID, int caseID) {
        return dbCaseDAO.getCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public void updateCaseOnCitizen(int citizenID, Case selectCase) {
        dbCaseDAO.updateCaseOnCitizen(citizenID, selectCase);
    }

    @Override
    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        dbCaseDAO.deleteCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public Case createCaseOnCitizen(Case newCase) {
        return dbCaseDAO.createCaseOnCitizen(newCase);
    }


    /***************************************************/
    /******************** Citizen **********************/
    /***************************************************/

    @Override
    public Borger createCitizen(Borger borger) {
        return dbCitizenDAO.createCitizen(borger);
    }

    @Override
    public List<Borger> getAllCitizens() {
        return dbCitizenDAO.getAllCitizens();
    }

    @Override
    public List<Borger> getAllTemplates() {
        return dbCitizenDAO.getAllTemplates();
    }

    @Override
    public void updateCitizen(Borger borger) {
        dbCitizenDAO.updateCitizen(borger);
    }

    @Override
    public void addStudentToCitizen(Borger borger) {
        dbCitizenDAO.addStudentToCitizen(borger);
    }

    @Override
    public void deleteCitizen(Borger borger) {
        dbCitizenDAO.deleteCitizen(borger);
    }

    /***************************************************/
    /******************** Citizen **********************/
    /***************************************************/

    @Override
    public void updateGenerelleOplysninger(Borger borger) {
        dbGenerelInformationDAO.updateGenerelleOplysninger(borger);
    }

    @Override
    public Borger getGenerelleOplysninger(Borger borger) {
        return dbGenerelInformationDAO.getGenerelleOplysninger(borger);
    }

    /***************************************************/
    /******************** Tilstande ********************/
    /***************************************************/

    @Override
    public HealthAssessment getHelbredstilstandOnCitizen(Borger borger) {
        return dbHealthAssessmentDAO.getHelbredstilstandOnCitizen(borger);
    }

    @Override
    public FunctionAssessment getFunktionstilstandOnCitizen(Borger borger) {
        return dbFunctionAssessmentDAO.getFunktionstilstandOnCitizen(borger);
    }

    @Override
    public void createEmptyFunktionstilstand(Borger borger) {
        dbFunctionAssessmentDAO.createEmptyFunktionstilstand(borger);
    }

    @Override
    public void updateFunktiontilstand(Borger borger) {
        dbFunctionAssessmentDAO.updateFunktionstilstand(borger);
    }

    @Override
    public void deleteFunktionstilstandOnCitizen(Borger borger) {
        dbFunctionAssessmentDAO.deleteFunktionstilstandOnCitizen(borger);
    }

    @Override
    public void createEmptyHelbredstilstand(Borger borger) {
        dbHealthAssessmentDAO.createEmptyHelbredstilstandOnCitizen(borger);
    }

    @Override
    public void updateHelbredstilstand(Borger borger) {
        dbHealthAssessmentDAO.updateHelbredstilstand(borger);
    }

    @Override
    public void deleteHelbredstilstandOnCitizen(Borger borger) {
        dbHealthAssessmentDAO.deleteHelbredstilstand(borger);
    }

    /***************************************************/
    /*************** Tilstandskategorier ***************/
    /***************************************************/

    @Override
    public FunctionAssessment getEmptyFunktionsTilstand() {
        return dbFunctionAssessmentDAO.getEmptyFunktionstilstands();
    }

    @Override
    public HealthAssessment getEmptyHelbredsTilstand() {
        return dbHealthAssessmentDAO.getEmptyHelbredstilstand();
    }

}