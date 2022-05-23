package bll.Seachers;

import be.Citizen;

import java.util.ArrayList;
import java.util.List;

public class CitizenSearcher {

    private List<Citizen> searchResult = new ArrayList<>();

    public List<Citizen> search(List<Citizen> searchBase, String query) {
        searchResult.clear();
        for (Citizen citizen : searchBase) {
            if (compareName(citizen.getFirstNameProperty().get(), query) || compareName(citizen.getLastNameProperty().get(), query)) {
                searchResult.add(citizen);
            }
        }
        return searchResult;
    }

    public boolean compareName(String name, String query) {
        return name.toLowerCase().contains(query.toLowerCase());
    }
}
