package gui.model;

import be.Borger;
import be.Case;
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
        managerFacade.createEmptyTilstande(borger);
    }

    public void createCitizenFromTemplate(Borger templateBorger) {


        Borger borger = new Borger(templateBorger.getFirstNameProperty().get(), templateBorger.getLastNameProperty().get(),
                false, templateBorger.getAgeProperty().get());

        allCitizens.add(managerFacade.createCitizen(borger));
        borger.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        borger.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());
        managerFacade.createEmptyTilstande(borger);

        // Add the data from Template
        var cases = managerFacade.getAllCasesOnCitizen(templateBorger.getIDProperty().get());
        for (var aCase : cases) {
            var newCase = new Case(borger.getIDProperty().get(), aCase.getOverCategoryTitleProperty().get(), aCase.getSubcategoryTitleProperty().get());
            newCase.setIsGranted(false);
            newCase.setReference(aCase.getReferenceProperty().get());
            newCase.setCaseResponsible(aCase.getCaseResponsibleProperty().get());
            newCase.setDescription(aCase.getDescriptionProperty().get());
            newCase.setCause(aCase.getCauseProperty().get());
            newCase.setCauseDiagnosis(aCase.getCauseDiagnosisProperty().get());
            newCase.setCauseCondition(aCase.getCauseConditionProperty().get());
            newCase.setCitizenWishes(aCase.getCitizenWishesProperty().get());
            newCase.setGrantedText(aCase.getGrantedTextProperty().get());
            newCase.setPlan(aCase.getPlanProperty().get());
            newCase.setFollowUpTag(aCase.getFollowUpTagProperty().get());
            managerFacade.createCaseOnCitizen(newCase);
        }

        // Set the Helbredstiltand and Funktionstilstands
        if (templateBorger.getHelbredstilstand() == null ) {
            templateBorger.setFunktionstilstand(managerFacade.getFunktionstilstandOnCitizen(templateBorger));
            templateBorger.setHelbredstilstand(managerFacade.getHelbredstilstandOnCitizen(templateBorger));
        }
        if(templateBorger.getGeneralinformation() == null){
            managerFacade.getGenerelleOplysninger(templateBorger); // TODO laves om til at man får en Generalinformation og ikke en borger
        }

        borger.setHelbredstilstand(templateBorger.getHelbredstilstand());
        borger.setFunktionstilstand(templateBorger.getFunktionstilstand());
        borger.setGeneralinformation(templateBorger.getGeneralinformation());

        managerFacade.updateSagsoplysninger(borger);
    }

    public void addStudentToCitizen(Borger borger) {
        managerFacade.addStudentToCitizen(borger);
    }


    public void deleteCitizen(Borger borger) {
        managerFacade.deleteCitizen(borger);
        allCitizens.remove(borger);
    }

    public void updateSagsoplysninger(Borger borger) {
        managerFacade.updateSagsoplysninger(borger);
    }

    public void setTilstandeOnCitizen(Borger borger) {
       borger.setHelbredstilstand(managerFacade.getHelbredstilstandOnCitizen(borger));
       borger.setFunktionstilstand(managerFacade.getFunktionstilstandOnCitizen(borger));
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
