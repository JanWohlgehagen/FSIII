package dal.interfaces;

import be.Borger;
import be.Case;
import be.Credential;
import be.user.User;

import java.util.List;

/**
 * Specifies the contract for the data access layer in the application.
 */
public interface IDatabaseFacade {

    Credential checkCredential(String userName);
    User getPersonById(int id);

    public List<Case> getAllCasesOnCitizen(int citizenid);

    public Case getCaseOnCitizen(int citizenID, int caseID);

    public void updateCaseOnCitizen(int citizenID, Case selectCase);

    public void deleteCaseOnCitizen(int citizenID, int caseID);

    public Case createCaseOnCitizen(Case newCase);

    //Citizen DAL Functions

    public Borger createCitizen(Borger borger);

    public List<Borger> getAllCitizens();

    public List<Borger> getAllTemplates();

    public void updateCitizen(Borger borger);

    public void deleteCitizen(Borger borger);

}
