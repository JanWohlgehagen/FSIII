package dal.interfaces;

import be.Case;
import be.Credential;
import be.Person;

import java.util.List;

/**
 * Specifies the contract for the data access layer in the application.
 */
public interface IDatabaseFacade {

    Credential checkCredential(String userName);
    Person getPersonById(int id);

    public List<Case> getAllCasesOnCitizen(int id);

    public Case getCaseOnCitizen(int citizenID, int caseID);

    public void updateCaseOnCitizen(int citizenID, int caseID);

    public void deleteCaseOnCitizen(int citizenID, int caseID);

    public Case createCaseOnCitizen(int citizenID);
}
