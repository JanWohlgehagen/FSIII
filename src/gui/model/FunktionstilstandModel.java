package gui.model;

import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class FunktionstilstandModel {

    private final IManagerFacade managerFacade;

    public FunktionstilstandModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<String> getFunktionstilstandsList() {
        return managerFacade.getFunktionstilstandsList();
    }

}
