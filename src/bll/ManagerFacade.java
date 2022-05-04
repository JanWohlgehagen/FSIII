package bll;

import be.Case;
import be.Person;
import bll.Interfaces.IManagerFacade;
import dal.DatabaseFacade;

import java.io.IOException;
import java.util.List;

public class ManagerFacade implements IManagerFacade {

    private final CredentialManager credentialManager;
    private final CaseManager caseManager;

    public ManagerFacade() throws IOException {
        credentialManager = new CredentialManager(new DatabaseFacade());
        caseManager = new CaseManager(new DatabaseFacade());
    }

                            /***************************************************/
                            /****************** Credential *********************/
                            /***************************************************/

    @Override
    public Person loginCredential(String userName, String userPassword) {
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
    public void updateCaseOnCitizen(int citizenID, int caseID) {
        caseManager.updateCaseOnCitizen(citizenID, caseID);
    }

    @Override
    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        caseManager.deleteCaseOnCitizen(citizenID, caseID);
    }

}
