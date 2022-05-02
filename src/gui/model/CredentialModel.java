package gui.model;

import be.Person;
import bll.ManagerFacade;

public class CredentialModel {

   private ManagerFacade managerFacade;

    public CredentialModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    public Person checkCredential(String userName, String password){
        return managerFacade.loginCredential(userName, password);
    }

    public void hashCredential(String password){

    }
}
