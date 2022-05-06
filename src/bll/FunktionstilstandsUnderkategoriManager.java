package bll;

import be.FunktionstilstandsUnderkategori;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class FunktionstilstandsUnderkategoriManager {

    private final IDatabaseFacade databaseFacade;

    public FunktionstilstandsUnderkategoriManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<String> getFunktionstilstandsUnderkategoriList() {
        return databaseFacade.getFunktionstilstand();
    }

}
