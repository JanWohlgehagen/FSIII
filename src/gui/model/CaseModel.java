package gui.model;

import be.Case;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class CaseModel {

    private final IManagerFacade managerFacade;

    public CaseModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<Case> getAllCasesOnCitizen(int id){
        return managerFacade.getAllCasesOnCitizen(id);
    }

    public Case getCaseOnCitizen(int citizenID, int caseID){
        return managerFacade.getCaseOnCitizen(citizenID, caseID);
    }

    public void updateCaseOnCitizen(int citizenID, int caseID){
        managerFacade.updateCaseOnCitizen(citizenID, caseID);
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        managerFacade.deleteCaseOnCitizen(citizenID, caseID);
    }
}
