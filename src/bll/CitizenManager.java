package bll;

import be.Borger;
import be.FunctionAssessment;
import be.HealthAssessment;
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
            if (borger.getStudentID() != 0) {
                borger.setStudent(databaseFacade.getUserById(borger.getStudentID()));
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

    public HealthAssessment getHelbredstilstandOnCitizen(Borger borger){
        return databaseFacade.getHelbredstilstandOnCitizen(borger);
    }

    public HealthAssessment getEmptyHelbredsTilstand(){
        return databaseFacade.getEmptyHelbredsTilstand();
    }

    public FunctionAssessment getFunktionstilstandOnCitizen(Borger borger){
        return databaseFacade.getFunktionstilstandOnCitizen(borger);
    }

    public FunctionAssessment getEmptyFunktionsTilstand(){
        return databaseFacade.getEmptyFunktionsTilstand();
    }
}
