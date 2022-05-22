package be;

import javafx.beans.property.*;

public class Case {
    //Istedet for personID kunne case evt. holde en borger så casen ved hvilken borger den er tilknyttet?
    private final IntegerProperty citizenID = new SimpleIntegerProperty();
    private final IntegerProperty caseID = new SimpleIntegerProperty();
    private final StringProperty overCategoryTitle = new SimpleStringProperty();
    private final StringProperty subcategoryTitle = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final BooleanProperty isGranted = new SimpleBooleanProperty();
    private final StringProperty grantedText = new SimpleStringProperty();
    private final StringProperty plan = new SimpleStringProperty();
    private final StringProperty followUpTag = new SimpleStringProperty();
    private final StringProperty reference = new SimpleStringProperty();
    private final StringProperty cause = new SimpleStringProperty();
    private final StringProperty causeDiagnosis = new SimpleStringProperty();
    private final StringProperty causeCondition = new SimpleStringProperty();
    private final StringProperty citizenWishes = new SimpleStringProperty();
    private final StringProperty caseResponsible = new SimpleStringProperty();

    public Case(int citizenID, String overCategoryTitle, String subcategoryTitle) {
        this.citizenID.set(citizenID);
        this.overCategoryTitle.set(overCategoryTitle);
        this.subcategoryTitle.set(subcategoryTitle);
    }

    public IntegerProperty getCitizenIDProperty() {
        return citizenID;
    }

    public IntegerProperty getCaseIDProperty() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID.set(caseID);
    }

    public StringProperty getOverCategoryTitleProperty() {
        return overCategoryTitle;
    }

    public void setOverCategoryTitle(String overCategoryTitle) {
        this.overCategoryTitle.set(overCategoryTitle);
    }

    public StringProperty getSubcategoryTitleProperty() {
        return subcategoryTitle;
    }

    public void setSubcategoryTitle(String subcategoryTitle) {
        this.subcategoryTitle.set(subcategoryTitle);
    }

    public StringProperty getCaseResponsibleProperty() {
        return caseResponsible;
    }

    public void setCaseResponsible(String caseResponsible) {
        this.caseResponsible.set(caseResponsible);
    }

    public StringProperty getReferenceProperty() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference.set(reference);
    }

    public StringProperty getCauseProperty() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }

    public StringProperty getCauseDiagnosisProperty() {
        return causeDiagnosis;
    }

    public void setCauseDiagnosis(String causeDiagnosis) {
        this.causeDiagnosis.set(causeDiagnosis);
    }

    public StringProperty getCauseConditionProperty() {
        return causeCondition;
    }

    public void setCauseCondition(String causeCondition) {
        this.causeCondition.set(causeCondition);
    }

    public StringProperty getCitizenWishesProperty() {
        return citizenWishes;
    }

    public void setCitizenWishes(String citizenWishes) {
        this.citizenWishes.set(citizenWishes);
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }


    public BooleanProperty isGrantedProperty() {
        return isGranted;
    }

    public void setIsGranted(boolean isGranted) {
        this.isGranted.set(isGranted);
    }


    public StringProperty getGrantedTextProperty() {
        return grantedText;
    }

    public void setGrantedText(String grantedText) {
        this.grantedText.set(grantedText);
    }

    public StringProperty getPlanProperty() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan.set(plan);
    }


    public StringProperty getFollowUpTagProperty() {
        return followUpTag;
    }

    public void setFollowUpTag(String followUpTag) {
        this.followUpTag.set(followUpTag);
    }

    @Override
    public String toString() {
        return this.overCategoryTitle.get() + " -> " + this.subcategoryTitle.get();
    }
}
