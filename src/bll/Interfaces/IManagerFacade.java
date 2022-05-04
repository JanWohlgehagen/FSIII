package bll.Interfaces;

import be.Case;
import be.Person;

import java.util.List;

public interface IManagerFacade {

    Person loginCredential(String userName, String userPassword);

    String newHashPassword(String password);




    public List<Case> getAllCasesOnCitizen(int id);

    public Case getCaseOnCitizen(int citizenID, int caseID);

    public void updateCaseOnCitizen(int citizenID, int caseID);

    public void deleteCaseOnCitizen(int citizenID, int caseID);

    public Case createCaseOnCitizen(int citizenID);

}
