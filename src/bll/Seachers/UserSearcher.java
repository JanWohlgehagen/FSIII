package bll.Seachers;

import be.User;

import java.util.ArrayList;
import java.util.List;

public class UserSearcher {

    private List<User> searchResult = new ArrayList<>();

    /**
     * Searches through a list based on the objects' names and returns a list of matches
     */
    public List<User> search(List<User> searchBase, String query) {
        searchResult.clear();
        for (User user : searchBase) {
            if (compareName(user.getFirstNameProperty().get(), query) || compareName(user.getLastNameProperty().get(), query)) {
                searchResult.add(user);
            }
        }
        return searchResult;
    }

    public boolean compareName(String name, String query) {
        return name.toLowerCase().contains(query.toLowerCase());
    }
}
