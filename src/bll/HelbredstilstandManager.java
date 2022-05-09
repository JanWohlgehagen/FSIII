package bll;

import be.Borger;
import be.Helbredstilstand;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class HelbredstilstandManager {
    private final IDatabaseFacade databaseFacade;

    public HelbredstilstandManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public Helbredstilstand getEmptyHelbredstilstand()
    {
        return databaseFacade.getEmptyHelbredsTilstand();
    }

    public void updateHelbredstilstand(Borger borger)
    {
        databaseFacade.updateHelbredstilstand(borger);
    }

    public void deleteHelbredstilstand (Borger borger)
    {
        databaseFacade.deleteHelbredstilstandOnCitizen(borger);
    }

    public List<String> getHelbredstilstandsList() {
        return databaseFacade.getHelbredstilstand();
    }
}