package bll;

import be.Case;
import be.Funktionstilstand;
import be.Helbredstilstand;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class CaseManager {

    private final IDatabaseFacade databaseFacade;

    public CaseManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public Case createCaseOnCitizen(Case newCase) {
        return databaseFacade.createCaseOnCitizen(newCase);
    }

    public List<Case> getAllCasesOnCitizen(int citizenid) {
        return databaseFacade.getAllCasesOnCitizen(citizenid);
    }

    public Case getCaseOnCitizen(int citizenID, int caseID) {
        return databaseFacade.getCaseOnCitizen(citizenID, caseID);
    }

    public void updateCaseOnCitizen(int citizenID, Case selectCase) {
        databaseFacade.updateCaseOnCitizen(citizenID, selectCase);
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        databaseFacade.deleteCaseOnCitizen(citizenID, caseID);
    }

    public Helbredstilstand getTitleHelbredsTilstand(){
        return databaseFacade.getEmptyHelbredsTilstand();
    }

    public Funktionstilstand getTitleFunktionsTilstand(){
        return databaseFacade.getEmptyFunktionsTilstand();
    }
}
