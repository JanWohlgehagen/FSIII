package gui.model;

import be.Case;
import be.FunctionAssessment;
import be.HealthAssessment;
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

    public HealthAssessment getTitleHelbredsTilstand(){
        return managerFacade.getTitleHelbredsTilstand();
    }

    public FunctionAssessment getTitleFunktionsTilstand(){
        return managerFacade.getTitleFunktionsTilstand();
    }
}
