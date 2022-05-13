package bll;

import be.user.User;
import bll.Interfaces.IManagerFacade;
import dal.DatabaseFacade;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class UserManager {

    private IDatabaseFacade databaseFacade;

    public UserManager(DatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<User> getAllUser() {
        return databaseFacade.getAllUser();
    }
}
