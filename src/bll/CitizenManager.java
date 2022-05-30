package bll;

import be.Citizen;
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

    /**
     * Returns a list of all citizens in the database, and attaches it to a student, if one is found in the database
     */
    public List<Citizen> getAllCitizen() {
        List<Citizen> listOfCitizens = databaseFacade.getAllCitizens();
        for (Citizen citizen : listOfCitizens) {
            if (citizen.getStudentID() != 0) {
                citizen.setStudent(databaseFacade.getUserById(citizen.getStudentID()));
            }
        }
        return listOfCitizens;
    }

    public List<Citizen> getAllTemplates() {
        return databaseFacade.getAllTemplates();
    }

    public Citizen createCitizen(Citizen citizen) {
        return databaseFacade.createCitizen(citizen);
    }

    public void updateCitizen(Citizen citizen) {
        databaseFacade.updateCitizen(citizen);
    }

    public void addStudentToCitizen(Citizen citizen) {
        databaseFacade.addStudentToCitizen(citizen);
    }

    public void deleteCitizen(Citizen citizen) {
        databaseFacade.deleteCitizen(citizen);
    }



    public Citizen getGenerelleOplysninger(Citizen citizen) {
        return databaseFacade.getGenerelleOplysninger(citizen);
    }

    public void updateGenerelleOplysninger(Citizen citizen) {
        databaseFacade.updateGenerelleOplysninger(citizen);
    }

    public void updateHelbredstilstand(Citizen citizen) {
        databaseFacade.updateHelbredstilstand(citizen);
    }

    public void updateFunktionstilstand(Citizen citizen) {
        databaseFacade.updateFunktiontilstand(citizen);
    }

    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen){
        return databaseFacade.getHelbredstilstandOnCitizen(citizen);
    }

    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen){
        return databaseFacade.getFunktionstilstandOnCitizen(citizen);
    }
}
