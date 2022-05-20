package gui.model;

import be.Case;
import be.Funktionstilstand;
import be.Helbredstilstand;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class CaseModel {

    private final IManagerFacade managerFacade;

    public CaseModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<Case> getAllCasesOnCitizen(int id) {
        return managerFacade.getAllCasesOnCitizen(id);
    }

    public void updateCaseOnCitizen(int citizenID, Case selectCase) {
        managerFacade.updateCaseOnCitizen(citizenID, selectCase);
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID) {
        managerFacade.deleteCaseOnCitizen(citizenID, caseID);
    }

    public Case createCaseOnCitizen(Case newCase) {
        return managerFacade.createCaseOnCitizen(newCase);
    }

    public Helbredstilstand getTitleHelbredsTilstand(){
        return managerFacade.getTitleHelbredsTilstand();
    }

    public Funktionstilstand getTitleFunktionsTilstand(){
        return managerFacade.getTitleFunktionsTilstand();
    }
}
