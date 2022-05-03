package bll.Interfaces;

import be.Person;

public interface IManagerFacade {

    Person loginCredential(String userName, String userPassword);

    String newHashPassword(String password);
}
