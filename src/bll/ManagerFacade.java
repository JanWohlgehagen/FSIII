package bll;

import be.*;
import be.user.User;
import bll.Interfaces.IManagerFacade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DatabaseFacade;

import java.io.IOException;
import java.util.List;

public class ManagerFacade implements IManagerFacade {

    private final CredentialManager credentialManager;
    private final CaseManager caseManager;
    private final CitizenManager citizenManager;
    private final FunktionstilstandManager funktionstilstandManager;
    private final FunktionstilstandsUnderkategoriManager funktionstilstandsUnderkategoriManager;
    private final HelbredstilstandManager helbredstilstandManager;

    public ManagerFacade() throws IOException {
        credentialManager = new CredentialManager(new DatabaseFacade());
        caseManager = new CaseManager(new DatabaseFacade());
        citizenManager = new CitizenManager(new DatabaseFacade());
        funktionstilstandManager = new FunktionstilstandManager(new DatabaseFacade());
        funktionstilstandsUnderkategoriManager = new FunktionstilstandsUnderkategoriManager(new DatabaseFacade());
        helbredstilstandManager = new HelbredstilstandManager(new DatabaseFacade());
    }

                            /***************************************************/
                            /****************** Credential *********************/
                            /***************************************************/

    @Override
    public User loginCredential(String userName, String userPassword) {
        return credentialManager.loginCredential(userName, userPassword);
    }

    @Override
    public String newHashPassword(String password) {
        return credentialManager.newHashPassword(password);
    }



                            /***************************************************/
                            /******************** Case *************************/
                            /***************************************************/


    @Override
    public List<Case> getAllCasesOnCitizen(int citizenid) {
        return caseManager.getAllCasesOnCitizen(citizenid);
    }

    @Override
    public Case getCaseOnCitizen(int citizenID, int caseID) {
        return caseManager.getCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public void updateCaseOnCitizen(int citizenID,Case selectCase) {
        caseManager.updateCaseOnCitizen(citizenID, selectCase);
    }

    @Override
    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        caseManager.deleteCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public Case createCaseOnCitizen(Case newCase) {
        return caseManager.createCaseOnCitizen(newCase);
    }

                                /***************************************************/
                                /******************** Citizen **********************/
                                /***************************************************/

    @Override
    public List<Borger> getAllCitizen() {
        return citizenManager.getAllCitizen();
    }

    @Override
    public List<Borger> getAllTemplates() {
        return citizenManager.getAllTemplates();
    }

    @Override
    public Borger createCitizen(Borger borger) {
        return citizenManager.createCitizen(borger);
    }

    @Override
    public void updateCitizen(Borger borger) {
        citizenManager.updateCitizen(borger);
    }

    @Override
    public void deleteCitizen(Borger borger) {
        citizenManager.deleteCitizen(borger);
    }

    @Override
    public void updateGenerelleOplysninger(Borger borger){
        citizenManager.updateGenerelleOplysninger(borger);
    }

    @Override
    public void createGenerelleOplysninger(Borger borger) {
        citizenManager.createGenerelleOplysninger(borger);
    }

    @Override
    public Borger getGenerelleOplysninger(Borger borger) {
        return citizenManager.getGenerelleOplysninger(borger);
    }

    @Override
    public void createEmptyTilstande(Borger borger) {
        citizenManager.createEmptyTilstande(borger);
    }

    public List<String> getFunktionstilstandsList()  {
        return funktionstilstandManager.getFunktionstilstandsList();
    }

    @Override
    public List<String> getFunktionstilstandsUnderkategoriList() {
        return funktionstilstandsUnderkategoriManager.getFunktionstilstandsUnderkategoriList();
    }

    @Override
    public Funktionstilstand getEmptyFunktionsTilstand() {
        return funktionstilstandsUnderkategoriManager.getFunktionsTilstandUnderkategorier();
    }

    @Override
    public Helbredstilstand getEmptyHelbredsTilstand() {
        return helbredstilstandManager.getEmptyHelbredstilstand();
    }


}
