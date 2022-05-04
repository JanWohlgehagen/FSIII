package bll;

import be.Case;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class CaseManager {

    private final IDatabaseFacade databaseFacade;

    public CaseManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public Case createCaseOnCitizen(int citizenID){
       return databaseFacade.createCaseOnCitizen(citizenID);
    }

    public List<Case> getAllCasesOnCitizen(int id){
        return databaseFacade.getAllCasesOnCitizen(id);
    }

    public Case getCaseOnCitizen(int citizenID, int caseID){
        return databaseFacade.getCaseOnCitizen(citizenID, caseID);
    }

    public void updateCaseOnCitizen(int citizenID, Case selectCase){
        databaseFacade.updateCaseOnCitizen(citizenID, selectCase);
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        databaseFacade.deleteCaseOnCitizen(citizenID, caseID);
    }

    public Case createCase(Case newCase) {
        return databaseFacade.createCase(newCase);
    }
}
