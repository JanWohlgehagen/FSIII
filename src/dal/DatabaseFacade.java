package dal;


import be.Borger;
import be.Case;
import be.Credential;
import be.Funktionstilstand;
import be.user.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IDatabaseFacade;

import java.io.IOException;
import java.util.List;

public class DatabaseFacade implements IDatabaseFacade {

    private DBConnecting dbConnecting;
    private DBLoginDAO dbLoginDAO;
    private DBPersonDAO dbPersonDAO;
    private DBCaseDAO dbCaseDAO;
    private DBCitizenDAO dbCitizenDAO;
    private DBGenerelInformationDAO dbGenerelInformationDAO;
    private DBFunktionstilstandDAO dbFunktionstilstandDAO;

    public DatabaseFacade() throws IOException {
        dbConnecting = new DBConnecting();
        dbLoginDAO = new DBLoginDAO(dbConnecting);
        dbPersonDAO = new DBPersonDAO(dbConnecting);
        dbCaseDAO = new DBCaseDAO(dbConnecting);
        dbCitizenDAO = new DBCitizenDAO(dbConnecting);
        dbGenerelInformationDAO = new DBGenerelInformationDAO(dbConnecting);

        dbFunktionstilstandDAO = new DBFunktionstilstandDAO(dbConnecting);
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
    public List<Case> getAllCasesOnCitizen(int citizenid) {
        return dbCaseDAO.getAllCasesOnCitizen(citizenid);
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
    public Case createCaseOnCitizen(Case newCase) {
        return dbCaseDAO.createCaseOnCitizen(newCase);
    }

    @Override
    public List<String> getFunktionstilstand() {
        return dbFunktionstilstandDAO.getFunktionstilstandList();
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
        List<Borger> allCitizens = dbCitizenDAO.getAllCitizens();

        for(Borger b: allCitizens)
        {
        }
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

                                /***************************************************/
                                /******************** Citizen **********************/
                                /***************************************************/

    @Override
    public void updateGenerelleOplysninger(Borger borger) {
        dbGenerelInformationDAO.updateGenerelleOplysninger(borger);
    }

    @Override
    public void createGenerelleOplysninger(Borger borger) {
        dbGenerelInformationDAO.createGenerelleOplysninger(borger);
    }

    @Override
    public Borger getGenerelleOplysninger(Borger borger) {
        return dbGenerelInformationDAO.getGenerelleOplysninger(borger);
    }

}