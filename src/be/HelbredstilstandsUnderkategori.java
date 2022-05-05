package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HelbredstilstandsUnderkategori {

    //tilstandsklassifikation is essentially the title (or category)
    private StringProperty tilstandsklassifikation = new SimpleStringProperty();
    private StringProperty overkategori = new SimpleStringProperty();
    private StringProperty tilstand = new SimpleStringProperty();
    private StringProperty forventetTilstand = new SimpleStringProperty();
    private StringProperty vurdering = new SimpleStringProperty();
    private StringProperty aarsag = new SimpleStringProperty();
    private StringProperty fagligNotat = new SimpleStringProperty();

    public HelbredstilstandsUnderkategori(String tilstandsklassifikation, String overkategori, String tilstand, String forventetTilstand, String vurdering, String aarsag, String fagligNotat) {
        this.setTilstand(tilstand);
        this.setForventetTilstand(forventetTilstand);
        this.setOverkategori(overkategori);
        this.setTilstandsklassifikation(tilstandsklassifikation);
        this.setVurdering(vurdering);
        this.setAarsag(aarsag);
        this.setFagligNotat(fagligNotat);
    }

    // This constructor is for use, if the user chooses that a subcategory is not relevant
    public HelbredstilstandsUnderkategori(String tilstandsklassifikation, String overkategori) {
        this.setOverkategori(overkategori);
        this.setTilstandsklassifikation(tilstandsklassifikation);
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

    @Override
    public String toString(){
        return this.tilstandsklassifikation.get();
    }

}
