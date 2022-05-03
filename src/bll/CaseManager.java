package bll;

import be.Case;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class CaseManager {

    private final IDatabaseFacade databaseFacade;

    public CaseManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<Case> getAllCasesOnCitizen(int id){
        return databaseFacade.getAllCasesOnCitizen(id);
    }

    public Case getCaseOnCitizen(int citizenID, int caseID){
        return databaseFacade.getCaseOnCitizen(citizenID, caseID);
    }

    public void updateCaseOnCitizen(int citizenID, int caseID){
        databaseFacade.updateCaseOnCitizen(citizenID, caseID);
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        databaseFacade.deleteCaseOnCitizen(citizenID, caseID);
    }
}
