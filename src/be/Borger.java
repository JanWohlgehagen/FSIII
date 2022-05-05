package be;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Borger {
    private IntegerProperty ID = new SimpleIntegerProperty();
    private IntegerProperty age = new SimpleIntegerProperty();
    private StringProperty firstName= new SimpleStringProperty();
    private StringProperty lastName= new SimpleStringProperty();
    private BooleanProperty isTemplate = new SimpleBooleanProperty();

    //Generelle oplysninger
    private StringProperty mestring = new SimpleStringProperty();
    private StringProperty motivation = new SimpleStringProperty();
    private StringProperty ressourcer = new SimpleStringProperty();
    private StringProperty roller = new SimpleStringProperty();
    private StringProperty vaner = new SimpleStringProperty();
    private StringProperty uddannelse = new SimpleStringProperty();
    private StringProperty livshistorie = new SimpleStringProperty();
    private StringProperty netvaerk = new SimpleStringProperty();
    private StringProperty helbredsoplysninger = new SimpleStringProperty();
    private StringProperty hjaelpemidler= new SimpleStringProperty();
    private StringProperty boligensIndretning = new SimpleStringProperty();

    private ArrayList<Case> listOfCases= new ArrayList<>();
    private Funktionstilstand funktionstilstand;
    private Helbredstilstand helbredstilstand;

    public Borger(String firstName, String lastName, boolean isTemplate, int age) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.isTemplate.set(isTemplate);
        this.age.set(age);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setIsTemplate(boolean isTemplate) {
        this.isTemplate.set(isTemplate);
    }

    public BooleanProperty isTemplateProperty() {
        return isTemplate;
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }


    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty mestringProperty() {
        return mestring;
    }

    public void setMestring(String mestring) {
        this.mestring.set(mestring);
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public StringProperty ressourcerProperty() {
        return ressourcer;
    }

    public void setRessourcer(String ressourcer) {
        this.ressourcer.set(ressourcer);
    }

    public StringProperty rollerProperty() {
        return roller;
    }

    public void setRoller(String roller) {
        this.roller.set(roller);
    }

    public StringProperty vanerProperty() {
        return vaner;
    }

    public void setVaner(String vaner) {
        this.vaner.set(vaner);
    }

    public StringProperty uddannelseProperty() {
        return uddannelse;
    }

    public void setUddannelse(String uddannelse) {
        this.uddannelse.set(uddannelse);
    }

    public StringProperty livshistorieProperty() {
        return livshistorie;
    }

    public void setLivshistorie(String livshistorie) {
        this.livshistorie.set(livshistorie);
    }

    public StringProperty netvaerkProperty() {
        return netvaerk;
    }

    public void setNetvaerk(String netvaerk) {
        this.netvaerk.set(netvaerk);
    }

    public StringProperty helbredsoplysningerProperty() {
        return helbredsoplysninger;
    }

    public void setHelbredsoplysninger(String helbredsoplysninger) {
        this.helbredsoplysninger.set(helbredsoplysninger);
    }

    public StringProperty hjaelpemidlerProperty() {
        return hjaelpemidler;
    }

    public void setHjaelpemidler(String hjaelpemidler) {
        this.hjaelpemidler.set(hjaelpemidler);
    }

    public StringProperty boligensIndretningProperty() {
        return boligensIndretning;
    }

    public void setBoligensIndretning(String boligensIndretning) {
        this.boligensIndretning.set(boligensIndretning);
    }

    public ArrayList<Case> getListOfCases() {
        return listOfCases;
    }

    public void setListOfCases(ArrayList<Case> listOfCases) {
        this.listOfCases = listOfCases;
    }

    public Funktionstilstand getFunktionstilstand() {
        return funktionstilstand;
    }

    public void setFunktionstilstand(Funktionstilstand funktionstilstand) {
        this.funktionstilstand = funktionstilstand;
    }

    public Helbredstilstand getHelbredstilstand() {
        return helbredstilstand;
    }

    public void setHelbredstilstand(Helbredstilstand helbredstilstand) {
        this.helbredstilstand = helbredstilstand;
    }

    @Override
    public String toString() {
        return firstName.get() + " " +  lastName.get() + " " + age.get() + "år";

    }
}
