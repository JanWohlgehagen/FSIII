package gui.model;

import be.Borger;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import bll.Seachers.CitizenSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CitizenModel {
    private ObservableList<Borger> allCitizens;
    private ObservableList<Borger> allTemplates;
    private List<Borger> allCitizensCache = new ArrayList<>();
    private List<Borger> allTemplatesCache = new ArrayList<>();
    private CitizenSearcher citizenSearcher;

    private final IManagerFacade managerFacade;

    public CitizenModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
        allCitizens = FXCollections.observableList(managerFacade.getAllCitizen());
        allTemplates = FXCollections.observableList(managerFacade.getAllTemplates());

        allCitizensCache.addAll(allCitizens);
        allTemplatesCache.addAll(allTemplates);
        citizenSearcher = new CitizenSearcher();
    }


    public ObservableList<Borger> getAllCitizen() {
        allCitizens = FXCollections.observableList(managerFacade.getAllCitizen());
        return allCitizens;
    }

    public ObservableList<Borger> getAllTemplates(){
        allTemplates = FXCollections.observableList(managerFacade.getAllTemplates());
        return allTemplates;
    }


    public void createCitizen(Borger borger) {
        if (borger.isTemplateProperty().get())
        {
            allTemplates.add(managerFacade.createCitizen(borger));
            borger.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
            borger.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());
            managerFacade.createEmptyTilstande(borger);
            managerFacade.createGenerelleOplysninger(borger);
        }
        else
        allCitizens.add(managerFacade.createCitizen(borger));
        borger.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        borger.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());
        managerFacade.createEmptyTilstande(borger);
        managerFacade.createGenerelleOplysninger(borger);
    }


    public void updateCitizen(Borger borger) {
        managerFacade.updateCitizen(borger);
    }


    public void deleteCitizen(Borger borger) {
        managerFacade.deleteCitizen(borger);
    }

    public void updateSagsoplysninger(Borger borger) {
        managerFacade.updateSagsoplysninger(borger);
    }

    public void getTilstande(Borger borger) {
        managerFacade.getTilstande(borger);
    }

    public void getGenerelleOplysninger(Borger borger) {
        managerFacade.getGenerelleOplysninger(borger);
    }

    public void searchCitizen(String query){
        if (query.isBlank() || query.isEmpty()) {
            allCitizens.clear();
            allCitizens.addAll(allCitizensCache);
        } else {
            allCitizens.clear();
            allCitizens.addAll(citizenSearcher.search(allCitizensCache, query));
        }
    }

    public void searchTemplates(String query){
        if (query.isBlank() || query.isEmpty()) {
            allTemplates.clear();
            allTemplates.addAll(allTemplatesCache);
        } else {
            allTemplates.clear();
            allTemplates.addAll(citizenSearcher.search(allTemplatesCache, query));
        }
    }
}
