package be;

import java.util.HashMap;
import java.util.List;

public class Funktionstilstand {
    // Hashmap that holds the chart of health categrories, the String signifies the Main category and the HelbredstilstandsUnderkategory object holds the information the student have to fill out
    HashMap<String, List<FunktionstilstandsUnderkategori>> tilstandsKortet = new HashMap<>();

    public Funktionstilstand() {}

    public void addCategoryField(String overkategori, List<FunktionstilstandsUnderkategori> underkategorier){
        tilstandsKortet.put(overkategori, underkategorier);
    }

    public HashMap<String, List <FunktionstilstandsUnderkategori>> getFunktionsTilstandsKort(){
        return tilstandsKortet;
    }
}
