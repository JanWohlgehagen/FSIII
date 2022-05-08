package bll;

import be.Helbredstilstand;
import dal.interfaces.IDatabaseFacade;

public class HelbredstilstandManager {
    private final IDatabaseFacade databaseFacade;

    public HelbredstilstandManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public Helbredstilstand getEmptyHelbredstilstand()
    {
        return databaseFacade.getEmptyHelbredsTilstand();
    }
}
