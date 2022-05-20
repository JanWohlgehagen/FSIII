package bll.Seachers;

import be.Borger;

import java.util.ArrayList;
import java.util.List;

public class CitizenSearcher {

    private List<Borger> searchResult = new ArrayList<>();

    public List<Borger> search(List<Borger> searchBase, String query) {
        searchResult.clear();


        for (Borger borger : searchBase) {
            if (compareName(borger.getFirstNameProperty().get(), query) || compareName(borger.getLastNameProperty().get(), query)) {
                searchResult.add(borger);
            }
        }
        return searchResult;
    }

    public boolean compareName(String name, String query) {
        return name.toLowerCase().contains(query.toLowerCase());
    }
}
