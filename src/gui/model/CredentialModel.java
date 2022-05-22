package gui.model;

import be.user.User;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;

public class CredentialModel {

    private IManagerFacade managerFacade;

    public CredentialModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public User checkCredential(String userName, String password) {
        return managerFacade.loginCredential(userName, password);
    }

    public void hashCredential(String password) {

    }
}