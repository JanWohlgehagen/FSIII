package bll;

import be.Borger;
import be.Case;
import be.user.User;
import bll.Interfaces.IManagerFacade;
import dal.DatabaseFacade;

import java.io.IOException;
import java.util.List;

public class ManagerFacade implements IManagerFacade {

    private final CredentialManager credentialManager;
    private final CaseManager caseManager;
    private final CitizenManager citizenManager;

    public ManagerFacade() throws IOException {
        credentialManager = new CredentialManager(new DatabaseFacade());
        caseManager = new CaseManager(new DatabaseFacade());
        citizenManager = new CitizenManager(new DatabaseFacade());
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
    public List<Case> getAllCasesOnCitizen(int id) {
        return caseManager.getAllCasesOnCitizen(id);
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
    public Case createCaseOnCitizen(int citizenID) {
        return caseManager.createCaseOnCitizen(citizenID);
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

}
