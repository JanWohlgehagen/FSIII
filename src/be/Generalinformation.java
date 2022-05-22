package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Generalinformation {

    private final StringProperty mestring = new SimpleStringProperty();
    private final StringProperty motivation = new SimpleStringProperty();
    private final StringProperty ressourcer = new SimpleStringProperty();
    private final StringProperty roller = new SimpleStringProperty();
    private final StringProperty vaner = new SimpleStringProperty();
    private final StringProperty uddannelse = new SimpleStringProperty();
    private final StringProperty livshistorie = new SimpleStringProperty();
    private final StringProperty netvaerk = new SimpleStringProperty();
    private final StringProperty helbredsoplysninger = new SimpleStringProperty();
    private final StringProperty hjaelpemidler = new SimpleStringProperty();
    private final StringProperty boligensIndretning = new SimpleStringProperty();

    public Generalinformation() {
    }

    public StringProperty getMestringProperty() {
        return mestring;
    }

    public void setMestring(String mestring) {
        this.mestring.set(mestring);
    }

    public StringProperty getMotivationProperty() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public StringProperty getRessourcerProperty() {
        return ressourcer;
    }

    public void setRessourcer(String ressourcer) {
        this.ressourcer.set(ressourcer);
    }

    public StringProperty getRollerProperty() {
        return roller;
    }

    public void setRoller(String roller) {
        this.roller.set(roller);
    }

    public StringProperty getVanerProperty() {
        return vaner;
    }

    public void setVaner(String vaner) {
        this.vaner.set(vaner);
    }

    public StringProperty getUddannelseProperty() {
        return uddannelse;
    }

    public void setUddannelse(String uddannelse) {
        this.uddannelse.set(uddannelse);
    }

    public StringProperty getLivshistorieProperty() {
        return livshistorie;
    }

    public void setLivshistorie(String livshistorie) {
        this.livshistorie.set(livshistorie);
    }

    public StringProperty getNetvaerkProperty() {
        return netvaerk;
    }

    public void setNetvaerk(String netvaerk) {
        this.netvaerk.set(netvaerk);
    }

    public StringProperty getHelbredsoplysningerProperty() {
        return helbredsoplysninger;
    }

    public void setHelbredsoplysninger(String helbredsoplysninger) {
        this.helbredsoplysninger.set(helbredsoplysninger);
    }
    public StringProperty getHjaelpemidlerProperty() {
        return hjaelpemidler;
    }
    public void setHjaelpemidler(String hjaelpemidler) {
        this.hjaelpemidler.set(hjaelpemidler);
    }

    public StringProperty getBoligensIndretningProperty() {
        return boligensIndretning;
    }

    public void setBoligensIndretning(String boligensIndretning) {
        this.boligensIndretning.set(boligensIndretning);
    }
}
