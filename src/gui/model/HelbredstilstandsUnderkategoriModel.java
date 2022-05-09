package gui.model;

import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

import java.util.List;

public class HelbredstilstandsUnderkategoriModel {

    private final IManagerFacade managerFacade;

    public HelbredstilstandsUnderkategoriModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public List<String> getHelbredstilstandsUnderkategoriList() {
        return managerFacade.getHelbredstilstandsUnderkategoriList();
    }

}
