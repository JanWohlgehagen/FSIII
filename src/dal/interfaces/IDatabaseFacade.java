package dal.interfaces;

import be.Borger;
import be.Case;
import be.Credential;
import be.Funktionstilstand;
import be.user.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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

    public List<String> getFunktionstilstand();

    public List<String> getFunktionstilstandsUnderkategori();

    //Citizen DAL Functions

    public Borger createCitizen(Borger borger);

    public List<Borger> getAllCitizens();

    public List<Borger> getAllTemplates();

    public void updateCitizen(Borger borger);

    public void deleteCitizen(Borger borger);

    public void updateGenerelleOplysninger(Borger borger);

    public void createGenerelleOplysninger(Borger borger);

    public Borger getGenerelleOplysninger(Borger borger);
}
