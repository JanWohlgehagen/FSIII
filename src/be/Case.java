package be;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Case {
    //Istedet for personID kunne case evt. holde en borger så casen ved hvilken borger den er tilknyttet?
    private IntegerProperty citizenID =  new SimpleIntegerProperty();
    private IntegerProperty caseID = new SimpleIntegerProperty();
    private StringProperty overkategoriTitle = new SimpleStringProperty();
    private StringProperty underkategoriTitle = new SimpleStringProperty();
    private StringProperty caseDescription= new SimpleStringProperty();
    private BooleanProperty isBevilget = new SimpleBooleanProperty();
    private StringProperty Bevillingstekst= new SimpleStringProperty();
    private StringProperty plan= new SimpleStringProperty();
    private StringProperty opfoelgningstag = new SimpleStringProperty();
    private StringProperty henvisning= new SimpleStringProperty();
    private StringProperty aasagsfritekst= new SimpleStringProperty();
    private StringProperty aasagsdiagnose= new SimpleStringProperty();
    private StringProperty aasagstilstand= new SimpleStringProperty();
    private StringProperty borgerensonsker= new SimpleStringProperty();
    private ArrayList<CaseDocumentation> listOfDocumentations = new ArrayList<>();

    public Case(int citizenID, String overkategoriTitle, String underkategoriTitle) {
        this.citizenID.set(citizenID);
        this.overkategoriTitle.set(overkategoriTitle);
        this.underkategoriTitle.set(underkategoriTitle);
    }

    public ArrayList<CaseDocumentation> getListOfDocumentations() {
        return listOfDocumentations;
    }

    public void setListOfDocumentations(ArrayList<CaseDocumentation> listOfDocumentations) {
        this.listOfDocumentations = listOfDocumentations;
    }

    public IntegerProperty getCitizenIDProperty() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID.set(citizenID);
    }


    public IntegerProperty getCaseIDProperty() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID.set(caseID);
    }

    public StringProperty getOverkategoriTitleProperty() {
        return overkategoriTitle;
    }

    public StringProperty getUnderkategoriTitleProperty() {
        return underkategoriTitle;
    }

    public StringProperty getHenvisningProperty() {
        return henvisning;
    }

    public void setHenvisning(String henvisning) {
        this.henvisning.set(henvisning);
    }

    public StringProperty getAasagsfritekstProperty() {
        return aasagsfritekst;
    }

    public void setAasagsfritekst(String aasagsfritekst) {
        this.aasagsfritekst.set(aasagsfritekst);
    }

    public StringProperty getAasagsdiagnoseProperty() {
        return aasagsdiagnose;
    }

    public void setAasagsdiagnose(String aasagsdiagnose) {
        this.aasagsdiagnose.set(aasagsdiagnose);
    }

    public StringProperty getAasagstilstandProperty() {
        return aasagstilstand;
    }

    public void setAasagstilstand(String aasagstilstand) {
        this.aasagstilstand.set(aasagstilstand);
    }

    public StringProperty getBorgerensonskerProperty() {
        return borgerensonsker;
    }

    public void setBorgerensonsker(String borgerensonsker) {
        this.borgerensonsker.set(borgerensonsker);
    }

    public StringProperty getCaseDescriptionProperty() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription.set(caseDescription);
    }


    public BooleanProperty isBevilgetProperty() {
        return isBevilget;
    }

    public void setIsBevilget(boolean isBevilget) {
        this.isBevilget.set(isBevilget);
    }


    public StringProperty getBevillingstekstProperty() {
        return Bevillingstekst;
    }

    public void setBevillingstekst(String bevillingstekst) {
        this.Bevillingstekst.set(bevillingstekst);
    }

    public StringProperty getPlanProperty() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan.set(plan);
    }


    public StringProperty getOpfoelgningstagProperty() {
        return opfoelgningstag;
    }

    public void setOpfoelgningstag(String opfoelgningstag) {
        this.opfoelgningstag.set(opfoelgningstag);
    }

    @Override
    public String toString() {
        return this.overkategoriTitle.get() + " -> " + this.underkategoriTitle.get();
    }
}
