package gui.model;

import be.Borger;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.List;

public class CitizenModel {
    private ObservableList<Borger> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Borger> allTemplates = FXCollections.observableArrayList();

    private final IManagerFacade managerFacade;

    public CitizenModel(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
        allCitizens.addAll(managerFacade.getAllCitizen());
        allTemplates.addAll(managerFacade.getAllTemplates());
    }


    public List<Borger> getAllCitizen() {
        return managerFacade.getAllCitizen();
    }

    public List<Borger> getAllTemplates()
    {
        return managerFacade.getAllTemplates();
    }


    public void createCitizen(Borger borger) {
         allCitizens.add(managerFacade.createCitizen(borger));
    }


    public void updateCitizen(Borger borger) {
        managerFacade.updateCitizen(borger);
    }


    public void deleteCitizen(Borger borger) {
        managerFacade.deleteCitizen(borger);
    }

    public void updateGenerelleOplysninger(Borger borger) {
        managerFacade.updateGenerelleOplysninger(borger);
    }
}
