package be;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Case {
    //Istedet for personID kunne case evt. holde en borger så casen ved hvilken borger den er tilknyttet?
    private IntegerProperty personID =  new SimpleIntegerProperty();
    private IntegerProperty caseID = new SimpleIntegerProperty();
    private StringProperty caseTitle = new SimpleStringProperty();
    private StringProperty caseDescription= new SimpleStringProperty();
    private BooleanProperty isBevilget = new SimpleBooleanProperty();
    private StringProperty Bevillingstekst= new SimpleStringProperty();
    private StringProperty plan= new SimpleStringProperty();
    private StringProperty Opfoelgningstag= new SimpleStringProperty();
    private ArrayList<CaseDocumentation> listOfDocumentations = new ArrayList<>();

    public Case(int personID, String caseTitle, String caseDescription)
    {
        this.personID.set(personID);
        this.caseTitle.set(caseTitle);
        this.caseDescription.set(caseDescription);

    }

    public ArrayList<CaseDocumentation> getListOfDocumentations() {
        return listOfDocumentations;
    }

    public void setListOfDocumentations(ArrayList<CaseDocumentation> listOfDocumentations) {
        this.listOfDocumentations = listOfDocumentations;
    }

    public IntegerProperty personIDProperty() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID.set(personID);
    }


    public IntegerProperty caseIDProperty() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID.set(caseID);
    }


    public StringProperty caseTitleProperty() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle.set(caseTitle);
    }


    public StringProperty caseDescriptionProperty() {
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


    public StringProperty bevillingstekstProperty() {
        return Bevillingstekst;
    }

    public void setBevillingstekst(String bevillingstekst) {
        this.Bevillingstekst.set(bevillingstekst);
    }

    public StringProperty planProperty() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan.set(plan);
    }


    public StringProperty opfoelgningstagProperty() {
        return Opfoelgningstag;
    }

    public void setOpfoelgningstag(String opfoelgningstag) {
        this.Opfoelgningstag.set(opfoelgningstag);
    }

    @Override
    public String toString() {
        return caseTitle.get();
    }
}
