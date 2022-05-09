package bll;

import be.Borger;
import be.Case;
import be.Funktionstilstand;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class FunktionstilstandManager {

    private final IDatabaseFacade databaseFacade;

    public FunktionstilstandManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<String> getFunktionstilstandsList() {
        return databaseFacade.getFunktionstilstand();
    }

    public void updateFunktionstilstand(Borger borger) {
        databaseFacade.updateFunktiontilstand(borger);
    }
}
