package bll;

import be.Funktionstilstand;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class HelbredstilstandsUnderkategoriManager {

    private final IDatabaseFacade databaseFacade;

    public HelbredstilstandsUnderkategoriManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<String> getHelbredstilstandsUnderkategoriList() {
        return databaseFacade.getHelbredstilstandsUnderkategori();
    }

}
