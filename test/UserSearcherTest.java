
import be.User;
import bll.Seachers.UserSearcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserSearcherTest {
    UserSearcher userSearcher = new UserSearcher();
    List<User> searchBase = new ArrayList<>();

    @BeforeEach
    void setUp() {

        User u1 = new User("Mikkel", "Mikkelsen");
        User u2 = new User("John", "Doe");
        User u3 = new User("Simon", "Mikkelsen");
        User u4 = new User("Simone", "Mikkelsen");
        User u5 = new User("Kathrine", "Mikkelsen");


        searchBase.add(u1);
        searchBase.add(u2);
        searchBase.add(u3);
        searchBase.add(u4);
        searchBase.add(u5);
    }

    @AfterEach
    void tearDown() {
        searchBase.clear();
    }

    @Test
    void testSearchPersonInList() {
        int expected = 4;
        int actual = userSearcher.search(searchBase, "Mikkel").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSearchPersonNotInList4() {
        int expected = 0;
        int actual = userSearcher.search(searchBase, "Mukkel").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSearchPersonAllInList() {
        int expected = 4;
        int actual = userSearcher.search(searchBase, "Mikkelsen").size();

        Assertions.assertEquals(expected, actual);
    }
}
