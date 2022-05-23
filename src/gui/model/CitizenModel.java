package gui.model;

import be.Citizen;
import be.Case;
import bll.Interfaces.IManagerFacade;
import bll.ManagerFacade;
import bll.Seachers.CitizenSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CitizenModel {
    private ObservableList<Citizen> allCitizens;
    private ObservableList<Citizen> allTemplates;
    private List<Citizen> allCitizensCache = new ArrayList<>();
    private List<Citizen> allTemplatesCache = new ArrayList<>();
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

    public ObservableList<Citizen> getAllCitizen() {
        allCitizens = FXCollections.observableList(managerFacade.getAllCitizen());
        return allCitizens;
    }

    public ObservableList<Citizen> getAllTemplates() {
        allTemplates = FXCollections.observableList(managerFacade.getAllTemplates());
        return allTemplates;
    }


    public void createCitizen(Citizen citizen) {
        if (citizen.isTemplateProperty().get()) {
            allTemplates.add(managerFacade.createCitizen(citizen));
        } else {
            allCitizens.add(managerFacade.createCitizen(citizen));
        }
        citizen.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        citizen.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());
        managerFacade.createEmptyTilstande(citizen);
    }

    public void createCitizenFromTemplate(Citizen templateCitizen) {


        Citizen citizen = new Citizen(templateCitizen.getFirstNameProperty().get(), templateCitizen.getLastNameProperty().get(),
                false, templateCitizen.getAgeProperty().get());

        allCitizens.add(managerFacade.createCitizen(citizen));
        citizen.setFunktionstilstand(managerFacade.getEmptyFunktionsTilstand());
        citizen.setHelbredstilstand(managerFacade.getEmptyHelbredsTilstand());
        managerFacade.createEmptyTilstande(citizen);

        // Add the data from Template
        var cases = managerFacade.getAllCasesOnCitizen(templateCitizen.getIDProperty().get());
        for (var aCase : cases) {
            var newCase = new Case(citizen.getIDProperty().get(), aCase.getOverCategoryTitleProperty().get(), aCase.getSubcategoryTitleProperty().get());
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
        if (templateCitizen.getHelbredstilstand() == null ) {
            templateCitizen.setFunktionstilstand(managerFacade.getFunktionstilstandOnCitizen(templateCitizen));
            templateCitizen.setHelbredstilstand(managerFacade.getHelbredstilstandOnCitizen(templateCitizen));
        }
        if(templateCitizen.getGeneralinformation() == null){
            managerFacade.getGenerelleOplysninger(templateCitizen); // TODO laves om til at man får en Generalinformation og ikke en borger
        }

        citizen.setHelbredstilstand(templateCitizen.getHelbredstilstand());
        citizen.setFunktionstilstand(templateCitizen.getFunktionstilstand());
        citizen.setGeneralinformation(templateCitizen.getGeneralinformation());

        managerFacade.updateSagsoplysninger(citizen);
    }

    public void addStudentToCitizen(Citizen citizen) {
        managerFacade.addStudentToCitizen(citizen);
    }


    public void deleteCitizen(Citizen citizen) {
        managerFacade.deleteCitizen(citizen);
        allCitizens.remove(citizen);
    }

    public void updateSagsoplysninger(Citizen citizen) {
        managerFacade.updateSagsoplysninger(citizen);
    }

    public void setTilstandeOnCitizen(Citizen citizen) {
       citizen.setHelbredstilstand(managerFacade.getHelbredstilstandOnCitizen(citizen));
       citizen.setFunktionstilstand(managerFacade.getFunktionstilstandOnCitizen(citizen));
    }

    public void getGenerelleOplysninger(Citizen citizen) {
        managerFacade.getGenerelleOplysninger(citizen);
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
