package dal.interfaces;

import be.Borger;
import be.Case;
import be.Credential;
import be.Person;

import java.util.ArrayList;
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

    //Citizen DAL Functions

    public Borger createCitizen(Borger borger);

    public List<Borger> getAllCitizens();

    public void updateCitizen(Borger borger);

    public void deleteCitizen(Borger borger);

}
