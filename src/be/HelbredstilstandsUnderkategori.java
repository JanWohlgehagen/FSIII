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

    public StringProperty getTilstandsklassifikationProperty() {
        return tilstandsklassifikation;
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
