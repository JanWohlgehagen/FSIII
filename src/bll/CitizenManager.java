package bll;

import be.Borger;
import dal.DatabaseFacade;

import java.util.List;

public class CitizenManager {
    private DatabaseFacade databaseFacade;

    public CitizenManager(DatabaseFacade databaseFacade)
    {
        this.databaseFacade = databaseFacade;
    }

    public List<Borger> getAllCitizen()
    {
        return databaseFacade.getAllCitizens();
    }

    public Borger createCitizen(Borger borger)
    {
        return databaseFacade.createCitizen(borger);
    }

    public void updateCitizen(Borger borger)
    {
        databaseFacade.updateCitizen(borger);
    }

    public void deleteCitizen (Borger borger)
    {
        databaseFacade.deleteCitizen(borger);
    }
}
