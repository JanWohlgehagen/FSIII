package gui.model;

import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class HelbredstilstandModel {

    private final IManagerFacade managerFacade;

    public HelbredstilstandModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<String> getHelbredstilstandsList() {
        return managerFacade.getHelbredstilstandsList();
    }

}