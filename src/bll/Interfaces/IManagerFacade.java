package bll.Interfaces;

import be.Borger;
import be.Case;
import be.user.User;

import java.util.List;

public interface IManagerFacade {

    User loginCredential(String userName, String userPassword);

    String newHashPassword(String password);



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

    public void updateGenerelleOplysninger(Borger borger);

    public void createGenerelleOplysninger(Borger borger);

    public Borger getGenerelleOplysninger(Borger borger);
}
