package be;

import java.util.HashMap;
import java.util.List;

public class Helbredstilstand {

    // Hashmap that holds the chart of health categrories, the String signifies the Main category and the HelbredstilstandsUnderkategory object holds the information the student have to fill out
    HashMap<String, List<HelbredstilstandsUnderkategori>> tilstandsKortet = new HashMap<>();

    public Helbredstilstand() {}

    public void addCategoryField(String overkategori, List<HelbredstilstandsUnderkategori> underkategorier){
        tilstandsKortet.put(overkategori, underkategorier);
    }

    public HashMap<String, List <HelbredstilstandsUnderkategori>> getHelbredsTilstandsKort(){
        return tilstandsKortet;
    }

    public void setHelbredstilstandskort(HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP) {
        this.tilstandsKortet = helbredstilstandeHP;
    }
}