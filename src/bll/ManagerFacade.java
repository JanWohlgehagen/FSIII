package bll;

import be.Person;
import bll.Interfaces.IManagerFacade;

public class ManagerFacade implements IManagerFacade {

    private CredentialManager credentialManager;

    public ManagerFacade() {
        credentialManager = new CredentialManager();
    }

    @Override
    public Person loginCredential(String userName, String userPassword) {
        return credentialManager.loginCredential(userName, userPassword);
    }

    @Override
    public String newHashPassword(String password) {
        return credentialManager.newHashPassword(password);
    }
}
