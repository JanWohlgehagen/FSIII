package be;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class FunktionstilstandsUnderkategori {

    private IntegerProperty id = new SimpleIntegerProperty(-1); // default int value would not work because zero is part of the scale used in the application.
    private StringProperty overKategori = new SimpleStringProperty();

    // The citizen's own input
    private StringProperty udførelse = new SimpleStringProperty();
    private StringProperty betydning = new SimpleStringProperty();
    private StringProperty oenskerOgMaal = new SimpleStringProperty();

    // The worker's input
    //tilstandsklassifikation is essentially the title (or category)
    private StringProperty tilstandsklassifikation = new SimpleStringProperty();
    private StringProperty vurdering = new SimpleStringProperty();
    private StringProperty aarsag = new SimpleStringProperty();
    private StringProperty fagligNotat = new SimpleStringProperty();
    private StringProperty opfølgning = new SimpleStringProperty();
    private IntegerProperty niveau = new SimpleIntegerProperty();
    private IntegerProperty forventetTilstand = new SimpleIntegerProperty();
    private Observation observation = new Observation();

    public FunktionstilstandsUnderkategori(int id, String tilstandsklassifikation, String overKategori)
    {
        this.id.set(id);
        this.tilstandsklassifikation.set(tilstandsklassifikation);
        this.overKategori.set(overKategori);
    }

    public FunktionstilstandsUnderkategori(int id,String udførelse, String betydning, String oenskerOgMaal, String tilstandsklassifikation, String vurdering,
                                           String aarsag, String fagligNotat, String opfølgning, String overKategori, int niveau, int forventetTilstand, Observation observation) {
        this.id.set(id);
        this.udførelse.set(udførelse);
        this.betydning.set(betydning);
        this.oenskerOgMaal.set(oenskerOgMaal);
        this.tilstandsklassifikation.set(tilstandsklassifikation);
        this.vurdering.set(vurdering);
        this.aarsag.set(aarsag);
        this.fagligNotat.set(fagligNotat);
        this.opfølgning.set(opfølgning);
        this.overKategori.set(overKategori);
        this.niveau.set(niveau);
        this.forventetTilstand.set(forventetTilstand);
        setObservation(observation);
    }

    // This constructor is for use, if the user chooses that a subcategory is not relevant
    public FunktionstilstandsUnderkategori(String overKategori, String tilstandsklassifikation, int niveau){
        this.tilstandsklassifikation.set(tilstandsklassifikation);
        this.niveau.set(niveau);
        this.setOverKategori(overKategori);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty getUdførelseProperty() {
        return udførelse;
    }

    public void setUdførelse(String udførelse) {
        this.udførelse.set(udførelse);
    }

    public StringProperty getBetydningProperty() {
        return betydning;
    }

    public void setBetydning(String betydning) {
        this.betydning.set(betydning);
    }

    public StringProperty getOenskerOgMaalProperty() {
        return oenskerOgMaal;
    }

    public void setOenskerOgMaal(String oenskerOgMaal) {
        this.oenskerOgMaal.set(oenskerOgMaal);
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

    public StringProperty getOpfølgningProperty() {
        return opfølgning;
    }

    public void setOpfølgning(String opfølgning) {
        this.opfølgning.set(opfølgning);
    }

    public IntegerProperty getNiveauProperty() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau.set(niveau);
    }

    public IntegerProperty getForventetTilstandProperty() {
        return forventetTilstand;
    }

    public void setForventetTilstand(int forventetTilstand) {
        this.forventetTilstand.set(forventetTilstand);
    }

    public StringProperty getOverKategoriProperty() {
        return overKategori;
    }

    public void setOverKategori(String overKategori) {
        this.overKategori.set(overKategori);
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    @Override
    public String toString(){
        return this.tilstandsklassifikation.get();
    }
}
