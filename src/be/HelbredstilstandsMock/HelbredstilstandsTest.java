package be.HelbredstilstandsMock;

import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import be.Helbredstilstand;

import java.util.ArrayList;
import java.util.List;

public class HelbredstilstandsTest {
    public static void main(String[] args) {

        List<FunktionstilstandsUnderkategori> list = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> list1 = new ArrayList<>();

        FunktionstilstandsUnderkategori fs = new FunktionstilstandsUnderkategori("Egenomsorg", "Gå på toilet", 9);
        FunktionstilstandsUnderkategori fs1 = new FunktionstilstandsUnderkategori("Egenomsorg", "Spise", 9);
        FunktionstilstandsUnderkategori fs2 = new FunktionstilstandsUnderkategori("Egenomsorg", "Drikke", 9);

        FunktionstilstandsUnderkategori fs3 = new FunktionstilstandsUnderkategori("Praktiske opgaver", "udføre daglige rutiner", 9);
        FunktionstilstandsUnderkategori fs4 = new FunktionstilstandsUnderkategori("Praktiske opgaver", "lave mad", 9);
        FunktionstilstandsUnderkategori fs5 = new FunktionstilstandsUnderkategori("Praktiske opgaver", "lave husligt arbejde", 9);
        list.add(fs);
        list.add(fs1);
        list.add(fs2);

        list1.add(fs3);
        list1.add(fs4);
        list1.add(fs5);

        Funktionstilstand funktionstilstand = new Funktionstilstand();

        funktionstilstand.addCategoryField(list.get(0).getOverKategoriProperty().get(), list);
        funktionstilstand.addCategoryField(list1.get(0).getOverKategoriProperty().get(), list1);

        for (String helbred: funktionstilstand.getFunktionsTilstandsKort().keySet()) {
            System.out.println(funktionstilstand.getFunktionsTilstandsKort().get(helbred) +  " - ");
        }

        System.exit(-1);
    }
}
