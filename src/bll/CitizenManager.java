package bll;

import be.Borger;
import be.Funktionstilstand;
import be.Helbredstilstand;
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



    public Borger getGenerelleOplysninger(Borger borger) {
        return databaseFacade.getGenerelleOplysninger(borger);
    }

    public void updateGenerelleOplysninger(Borger borger) {
        databaseFacade.updateGenerelleOplysninger(borger);
    }

    public void updateHelbredstilstand(Borger borger) {
        databaseFacade.updateHelbredstilstand(borger);
    }

    public void updateFunktionstilstand(Borger borger) {
        databaseFacade.updateFunktiontilstand(borger);
    }

    public void createEmptyTilstande(Borger borger) {
        databaseFacade.createEmptyHelbredstilstand(borger);
        databaseFacade.createEmptyFunktionstilstand(borger);
    }

    public Helbredstilstand getHelbredstilstandOnCitizen(Borger borger){
        return databaseFacade.getHelbredstilstandOnCitizen(borger);
    }

    public Helbredstilstand getEmptyHelbredsTilstand(){
        return databaseFacade.getEmptyHelbredsTilstand();
    }

    public Funktionstilstand getFunktionstilstandOnCitizen(Borger borger){
        return databaseFacade.getFunktionstilstandOnCitizen(borger);
    }

    public Funktionstilstand getEmptyFunktionsTilstand(){
        return databaseFacade.getEmptyFunktionsTilstand();
    }
}
