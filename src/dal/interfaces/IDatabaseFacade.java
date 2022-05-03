package dal.interfaces;

import be.Credential;
import be.Person;

/**
 * Specifies the contract for the data access layer in the application.
 */
public interface IDatabaseFacade {

    Credential checkCredential(String userName);
    Person getPersonById(int id);

}
