package bll;

import be.Borger;
import dal.DatabaseFacade;
import dal.interfaces.IDatabaseFacade;

import java.util.List;

public class CitizenManager {
    private IDatabaseFacade databaseFacade;

    public CitizenManager(DatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public List<Borger> getAllCitizen() {
        List<Borger> listOfCitizens = databaseFacade.getAllCitizens();
        for (Borger borger : listOfCitizens) {
            if (borger.getStudentIDProperty().get() != 0) {
                borger.setStudent(databaseFacade.getUserById(borger.getStudentIDProperty().get()));
            }
        }
        return listOfCitizens;
    }

    public List<Borger> getAllTemplates() {
        return databaseFacade.getAllTemplates();
    }

    public Borger createCitizen(Borger borger) {
        return databaseFacade.createCitizen(borger);
    }

    public void updateCitizen(Borger borger) {
        databaseFacade.updateCitizen(borger);
    }

    public void addStudentToCitizen(Borger borger) {
        databaseFacade.addStudentToCitizen(borger);
    }

    public void deleteCitizen(Borger borger) {
        databaseFacade.deleteCitizen(borger);
    }

    public void updateGenerelleOplysninger(Borger borger) {
        databaseFacade.updateGenerelleOplysninger(borger);
    }

    public Borger getGenerelleOplysninger(Borger borger) {
        return databaseFacade.getGenerelleOplysninger(borger);
    }

    public void createEmptyTilstande(Borger borger) {
        databaseFacade.createEmptyHelbredstilstand(borger);
        databaseFacade.createEmptyFunktionstilstand(borger);
    }

    public void getTilstande(Borger borger) {
        databaseFacade.getTilstandeOnCitizen(borger);
    }
}
