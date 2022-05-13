package dal.interfaces;

import be.*;
import be.user.User;

import java.util.List;

/**
 * Specifies the contract for the data access layer in the application.
 */
public interface IDatabaseFacade {

    Credential checkCredential(String userName);
    User getUserById(int id);

    List<User> getAllUser();

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

    //Tilstande Functions

    public void getTilstandeOnCitizen(Borger borger);

    public void createEmptyFunktionstilstand (Borger borger);

    public void updateFunktiontilstand(Borger borger);

    public void deleteFunktionstilstandOnCitizen(Borger borger);


    public void createEmptyHelbredstilstand (Borger borger);

    public void updateHelbredstilstand(Borger borger);

    public void deleteHelbredstilstandOnCitizen(Borger borger);

    public Funktionstilstand getEmptyFunktionsTilstand();

    public Helbredstilstand getEmptyHelbredsTilstand();

    public List<String> getHelbredstilstand();

    public List<String> getHelbredstilstandsUnderkategori();
}
