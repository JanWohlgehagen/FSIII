package gui.model;

import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class FunktionstilstandsUnderkategoriModel {

    private final IManagerFacade managerFacade;

    public FunktionstilstandsUnderkategoriModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<String> getFunktionstilstandsUnderkategoriList() {
        return managerFacade.getFunktionstilstandsUnderkategoriList();
    }

}
