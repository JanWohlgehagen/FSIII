package be;

import java.util.HashMap;
import java.util.List;

public class FunctionAssessment {
    // Hashmap that holds the chart of health categrories, the String signifies the Main category and the HelbredstilstandsUnderkategory object holds the information the student have to fill out
    HashMap<String, List<FunktionstilstandsUnderkategori>> tilstandsKortet = new HashMap<>();

    public FunctionAssessment() {
    }

    public void addCategoryField(String overkategori, List<FunktionstilstandsUnderkategori> underkategorier) {
        tilstandsKortet.put(overkategori, underkategorier);
    }

    public HashMap<String, List<FunktionstilstandsUnderkategori>> getFunktionsTilstandsKort() {
        return tilstandsKortet;
    }

    public void setFunktionsTilstandskort(HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandeHP) {
        this.tilstandsKortet = funktionstilstandeHP;
    }
}
