package be;

import java.util.HashMap;
import java.util.List;

public class HealthAssessment {

    // Hashmap that holds the chart of health categrories, the String signifies the Main category and the HelbredstilstandsUnderkategory object holds the information the student have to fill out
    HashMap<String, List<HelbredstilstandsUnderkategori>> tilstandsKortet = new HashMap<>();

    public HealthAssessment() {
    }

    public HashMap<String, List<HelbredstilstandsUnderkategori>> getHelbredsTilstandsKort() {
        return tilstandsKortet;
    }

    public void setHelbredstilstandskort(HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP) {
        this.tilstandsKortet = helbredstilstandeHP;
    }

}