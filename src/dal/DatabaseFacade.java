package dal;


import be.Borger;
import be.Case;
import be.Credential;
import be.user.User;
import dal.interfaces.IDatabaseFacade;

import java.io.IOException;
import java.util.List;

public class DatabaseFacade implements IDatabaseFacade {

    private DBConnecting dbConnecting;
    private DBLoginDAO dbLoginDAO;
    private DBPersonDAO dbPersonDAO;
    private DBCaseDAO dbCaseDAO;
    private DBCitizenDAO dbCitizenDAO;

    public DatabaseFacade() throws IOException {
        dbConnecting = new DBConnecting();
        dbLoginDAO = new DBLoginDAO(dbConnecting);
        dbPersonDAO = new DBPersonDAO(dbConnecting);
        dbCaseDAO = new DBCaseDAO(dbConnecting);
        dbCitizenDAO = new DBCitizenDAO(dbConnecting);

    }

    @Override
    public Credential checkCredential(String userName) {
        return dbLoginDAO.checkCredential(userName);
    }

    @Override
    public User getPersonById(int id) {
        return dbPersonDAO.getPersonById(id);
    }



                            /***************************************************/
                            /******************** Case *************************/
                            /***************************************************/

    @Override
    public List<Case> getAllCasesOnCitizen(int id) {
        return dbCaseDAO.getAllCasesOnCitizen(id);
    }

    @Override
    public Case getCaseOnCitizen(int citizenID, int caseID) {
        return dbCaseDAO.getCaseOnCitizen(citizenID,caseID);
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
    public Case createCaseOnCitizen(int citizenID) {
        return dbCaseDAO.createCaseOnCitizen(citizenID);
    }

    @Override
    public Case createCase(Case newCase) {
        return dbCaseDAO.createCase(newCase);
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
    public void deleteCitizen(Borger borger) {
        dbCitizenDAO.deleteCitizen(borger);

    }

}