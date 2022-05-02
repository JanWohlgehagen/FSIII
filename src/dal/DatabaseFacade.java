package dal;


import be.Credential;
import be.Person;
import dal.interfaces.IDatabaseFacade;

import java.io.IOException;

public class DatabaseFacade implements IDatabaseFacade {

    private DBConnecting dbConnecting;
    private DBLoginDAO dbLoginDAO;
    private DBPersonDAO dbPersonDAO;

    public DatabaseFacade() throws IOException {
        dbConnecting = new DBConnecting();
        dbLoginDAO = new DBLoginDAO(dbConnecting);
        dbPersonDAO = new DBPersonDAO(dbConnecting);
    }

    @Override
    public Credential checkCredential(String userName) {
        return dbLoginDAO.checkCredential(userName);
    }

    @Override
    public Person getPersonById(int id) {
        return dbPersonDAO.getPersonById(id);
    }


}