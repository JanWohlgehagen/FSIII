package bll;

import be.Person;
import bll.Interfaces.IManagerFacade;
import dal.DatabaseFacade;

import java.io.IOException;

public class ManagerFacade implements IManagerFacade {

    private final CredentialManager credentialManager;

    public ManagerFacade() throws IOException {
        credentialManager = new CredentialManager(new DatabaseFacade());
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
