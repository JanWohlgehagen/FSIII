package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HelbredstilstandsUnderkategori {

    //tilstandsklassifikation is essentially the title (or category)
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty tilstandsklassifikation = new SimpleStringProperty();
    private StringProperty overkategori = new SimpleStringProperty();
    private StringProperty tilstand = new SimpleStringProperty();
    private StringProperty forventetTilstand = new SimpleStringProperty();
    private StringProperty vurdering = new SimpleStringProperty();
    private StringProperty aarsag = new SimpleStringProperty();
    private StringProperty fagligNotat = new SimpleStringProperty();
    private Observation observation = new Observation();

    public HelbredstilstandsUnderkategori(int ID, String tilstandsklassifikation, String overkategori) {
        this.id.set(ID);
        this.tilstandsklassifikation.set(tilstandsklassifikation);
        this.overkategori.set(overkategori);
    }

    public HelbredstilstandsUnderkategori(int ID, String tilstandsklassifikation, String overkategori, String tilstand, String forventetTilstand, String vurdering, String aarsag, String fagligNotat, Observation observation) {
        this.id.set(ID);
        this.setTilstand(tilstand);
        this.setForventetTilstand(forventetTilstand);
        this.setOverkategori(overkategori);
        this.setTilstandsklassifikation(tilstandsklassifikation);
        this.setVurdering(vurdering);
        this.setAarsag(aarsag);
        this.setFagligNotat(fagligNotat);
        setObservation(observation);
    }

    // This constructor is for use, if the user chooses that a subcategory is not relevant
    public HelbredstilstandsUnderkategori(String tilstandsklassifikation, String overkategori) {
        this.setOverkategori(overkategori);
        this.setTilstandsklassifikation(tilstandsklassifikation);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty getTilstandProperty() {
        return tilstand;
    }

    public void setTilstand(String tilstand) {
        this.tilstand.set(tilstand);
    }

    public StringProperty getOverkategoriProperty() {
        return overkategori;
    }

    public void setOverkategori(String overkategori) {
        this.overkategori.set(overkategori);
    }

    public StringProperty getTilstandsklassifikationProperty() {
        return tilstandsklassifikation;
    }

    public void setTilstandsklassifikation(String tilstandsklassifikation) {
        this.tilstandsklassifikation.set(tilstandsklassifikation);
    }

    public StringProperty getVurderingProperty() {
        return vurdering;
    }

    public void setVurdering(String vurdering) {
        this.vurdering.set(vurdering);
    }

    public StringProperty getAarsagProperty() {
        return aarsag;
    }

    public void setAarsag(String aarsag) {
        this.aarsag.set(aarsag);
    }

    public StringProperty getFagligNotatProperty() {
        return fagligNotat;
    }

    public void setFagligNotat(String fagligNotat) {
        this.fagligNotat.set(fagligNotat);
    }

    public StringProperty getForventetTilstandProperty() {
        return forventetTilstand;
    }

    public void setForventetTilstand(String forventetTilstand) {
        this.forventetTilstand.set(forventetTilstand);
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return this.tilstandsklassifikation.get();
    }

}
