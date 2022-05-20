package bll;

import be.Funktionstilstand;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class FunktionstilstandsUnderkategoriManager {

    private final IDatabaseFacade databaseFacade;

    public FunktionstilstandsUnderkategoriManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<String> getFunktionstilstandsUnderkategoriList() {
        return databaseFacade.getFunktionstilstandsUnderkategori();
    }

    public Funktionstilstand getFunktionsTilstandUnderkategorier() {
        return databaseFacade.getEmptyFunktionsTilstand();
    }

}
