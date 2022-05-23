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
    public Citizen createCitizen(Citizen citizen) {
        return dbCitizenDAO.createCitizen(citizen);
    }

    @Override
    public List<Citizen> getAllCitizens() {
        return dbCitizenDAO.getAllCitizens();
    }

    @Override
    public List<Citizen> getAllTemplates() {
        return dbCitizenDAO.getAllTemplates();
    }

    @Override
    public void updateCitizen(Citizen citizen) {
        dbCitizenDAO.updateCitizen(citizen);
    }

    @Override
    public void addStudentToCitizen(Citizen citizen) {
        dbCitizenDAO.addStudentToCitizen(citizen);
    }

    @Override
    public void deleteCitizen(Citizen citizen) {
        dbCitizenDAO.deleteCitizen(citizen);
    }

    /***************************************************/
    /******************** Citizen **********************/
    /***************************************************/

    @Override
    public void updateGenerelleOplysninger(Citizen citizen) {
        dbGenerelInformationDAO.updateGenerelleOplysninger(citizen);
    }

    @Override
    public Citizen getGenerelleOplysninger(Citizen citizen) {
        return dbGenerelInformationDAO.getGenerelleOplysninger(citizen);
    }

    /***************************************************/
    /******************** Tilstande ********************/
    /***************************************************/

    @Override
    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen) {
        return dbHealthAssessmentDAO.getHelbredstilstandOnCitizen(citizen);
    }

    public void updateFunktiontilstand(Borger borger) {
        dbFunktionstilstandDAO.updateFunktionstilstand(borger);
    }

    @Override
    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen) {
        return dbFunctionAssessmentDAO.getFunktionstilstandOnCitizen(citizen);
    }

    @Override
    public void createEmptyFunktionstilstand(Citizen citizen) {
        dbFunctionAssessmentDAO.createEmptyFunktionstilstand(citizen);
    }

    @Override
    public void createEmptyHelbredstilstand(Citizen citizen) {
        dbHealthAssessmentDAO.createEmptyHelbredstilstandOnCitizen(citizen);
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