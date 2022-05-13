package bll.Interfaces;

import be.*;
import be.user.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface IManagerFacade {

    User loginCredential(String userName, String userPassword);

    String newHashPassword(String password);

    List<User> getAllUser();


    // CASE
    public List<Case> getAllCasesOnCitizen(int citizenid);

    public Case getCaseOnCitizen(int citizenID, int caseID);

    public void updateCaseOnCitizen(int citizenID, Case selectCase);

    public void deleteCaseOnCitizen(int citizenID, int caseID);

    public Case createCaseOnCitizen(Case newCase);

    //CITIZEN
    public List<Borger> getAllCitizen();

    public List<Borger> getAllTemplates();

    public Borger createCitizen(Borger borger);

    public void updateCitizen(Borger borger);

    public void deleteCitizen (Borger borger);

    public void updateSagsoplysninger(Borger borger);

    public void createGenerelleOplysninger(Borger borger);

    public Borger getGenerelleOplysninger(Borger borger);

    public void createEmptyTilstande(Borger borger);

    //FUNKTIONSTILSTAND

    public List<String> getFunktionstilstandsList();

    public List<String> getFunktionstilstandsUnderkategoriList();

    public Funktionstilstand getEmptyFunktionsTilstand();

    //HELBREDSTILSTAND

    public Helbredstilstand getEmptyHelbredsTilstand();
    public void updateHelbredstilstand(Borger borger);

    public void deleteHelbredstilstand (Borger borger);

    public List<String> getHelbredstilstandsList();

    public List<String> getHelbredstilstandsUnderkategoriList();

    void getTilstande(Borger borger);
}
