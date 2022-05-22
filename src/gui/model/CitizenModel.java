package gui.model;

import be.Borger;
import be.Case;
import be.Funktionstilstand;
import be.Helbredstilstand;
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

    public ObservableList<Borger> getAllTemplates() {
        allTemplates = FXCollections.observableList(managerFacade.getAllTemplates());
        return allTemplates;
    }


    public void createCitizen(Borger borger) {
        if (borger.isTemplateProperty().get()) {
            allTemplates.add(managerFacade.createCitizen(borger));
        } else {
            allCitizens.add(managerFacade.createCitizen(borger));
        }
        borger.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        borger.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());

    }


    public void createTemplateFromCitizen (Borger borger)
    {
        allTemplates.add(managerFacade.createCitizen(borger));
        //TODO Vent på DB Fix ..
    }

    public void createCitizenFromTemplate(Borger templateBorger){

        Borger borger = new Borger(templateBorger.getFirstNameProperty().get(), templateBorger.getLastNameProperty().get(),
                false, templateBorger.getAgeProperty().get());

        allCitizens.add(managerFacade.createCitizen(borger));
        borger.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        borger.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());

        // Add the data from Template
        var cases = managerFacade.getAllCasesOnCitizen(templateBorger.getIDProperty().get());
        for (var aCase : cases) {
            var newCase = new Case(borger.getIDProperty().get(), aCase.getOverkategoriTitleProperty().get(), aCase.getUnderkategoriTitleProperty().get());
            newCase.setIsBevilget(false);
            newCase.setHenvisning(aCase.getHenvisningProperty().get());
            newCase.setSagsansvarlig(aCase.getSagsansvarligProperty().get());
            newCase.setCaseDescription(aCase.getCaseDescriptionProperty().get());
            newCase.setAasagsfritekst(aCase.getAasagsfritekstProperty().get());
            newCase.setAasagsdiagnose(aCase.getAasagsdiagnoseProperty().get());
            newCase.setAasagstilstand(aCase.getAasagstilstandProperty().get());
            newCase.setBorgerensonsker(aCase.getBorgerensonskerProperty().get());
            newCase.setBevillingstekst(aCase.getBevillingstekstProperty().get());
            newCase.setPlan(aCase.getPlanProperty().get());
            newCase.setOpfoelgningstag(aCase.getOpfoelgningstagProperty().get());
            managerFacade.createCaseOnCitizen(newCase);
        }

        // Set the Helbredstiltand and Funktionstilstands
        if (templateBorger.getHelbredstilstand() == null) {
            managerFacade.getTilstande(templateBorger);
        }

        borger.setHelbredstilstand(templateBorger.getHelbredstilstand());
        borger.setFunktionstilstand(templateBorger.getFunktionstilstand());

        borger.setMestring(templateBorger.getMestringProperty().get());
        borger.setMotivation(templateBorger.getMotivationProperty().get());
        borger.setRessourcer(templateBorger.getRessourcerProperty().get());
        borger.setRoller(templateBorger.getRollerProperty().get());
        borger.setVaner(templateBorger.getVanerProperty().get());
        borger.setUddannelse(templateBorger.getUddannelseProperty().get());
        borger.setLivshistorie(templateBorger.getLivshistorieProperty().get());
        borger.setNetvaerk(templateBorger.getNetvaerkProperty().get());
        borger.setHelbredsoplysninger(templateBorger.getHelbredsoplysningerProperty().get());
        borger.setHjaelpemidler(templateBorger.getHjaelpemidlerProperty().get());
        borger.setBoligensIndretning(templateBorger.getBoligensIndretningProperty().get());
        managerFacade.updateSagsoplysninger(borger);
    }


    public void updateCitizen(Borger borger) {
        managerFacade.updateCitizen(borger);
    }

    public void addStudentToCitizen(Borger borger) {
        managerFacade.addStudentToCitizen(borger);
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

    public void searchCitizen(String query) {
        if (query.isBlank() || query.isEmpty()) {
            allCitizens.clear();
            allCitizens.addAll(allCitizensCache);
        } else {
            allCitizens.clear();
            allCitizens.addAll(citizenSearcher.search(allCitizensCache, query));
        }
    }

    public void searchTemplates(String query) {
        if (query.isBlank() || query.isEmpty()) {
            allTemplates.clear();
            allTemplates.addAll(allTemplatesCache);
        } else {
            allTemplates.clear();
            allTemplates.addAll(citizenSearcher.search(allTemplatesCache, query));
        }
    }
}
