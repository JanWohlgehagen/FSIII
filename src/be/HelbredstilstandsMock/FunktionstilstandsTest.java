package be.HelbredstilstandsMock;

import be.Helbredstilstand;
import be.HelbredstilstandsUnderkategori;

import java.util.ArrayList;
import java.util.List;

public class FunktionstilstandsTest {
    public static void main(String[] args) {

        HelbredstilstandsUnderkategori hs = new HelbredstilstandsUnderkategori("Noget med piller", "Piller", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");
        HelbredstilstandsUnderkategori hs1 = new HelbredstilstandsUnderkategori("Noget andet med piller", "Piller", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");
        HelbredstilstandsUnderkategori hs2 = new HelbredstilstandsUnderkategori("Noget tredje piller", "Piller", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");
        HelbredstilstandsUnderkategori hs4 = new HelbredstilstandsUnderkategori("Noget med Bæ", "Bæ", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");
        HelbredstilstandsUnderkategori hs5 = new HelbredstilstandsUnderkategori("Noget andet med bæ", "Bæ", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");
        HelbredstilstandsUnderkategori hs6 = new HelbredstilstandsUnderkategori("Noget tredje med bæ", "Bæ", "tilstand 1", "Vurdering", "Årsag", "Et meget fagligt notat", "Aktuelt Problem");

        List<HelbredstilstandsUnderkategori> list = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> list1 = new ArrayList<>();

        list.add(hs);
        list.add(hs1);
        list.add(hs2);

        list1.add(hs4);
        list1.add(hs5);
        list1.add(hs6);

        Helbredstilstand helbredstilstand = new Helbredstilstand();

        helbredstilstand.addCategoryField(list.get(0).getOverkategoriProperty().get(), list);
        helbredstilstand.addCategoryField(list1.get(0).getOverkategoriProperty().get(), list1);

        for (String helbred: helbredstilstand.getHelbredsTilstandsKort().keySet()) {
            System.out.println(helbredstilstand.getHelbredsTilstandsKort().get(helbred) +  " - ");
        }

        System.exit(-1);
    }
}
