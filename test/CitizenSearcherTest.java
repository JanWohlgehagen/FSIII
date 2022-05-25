import be.Citizen;
import bll.Seachers.CitizenSearcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CitizenSearcherTest {
    CitizenSearcher citizenSearcher = new CitizenSearcher();
    List<Citizen> searchBase = new ArrayList<>();

    @BeforeEach
    void setUp() {
       boolean isTemplate = false;

        Citizen c1 = new Citizen("John", "Doe", isTemplate, 78);
        Citizen c2 = new Citizen("Bent", "Doe", isTemplate, 78);
        Citizen c3 = new Citizen("Victor", "Doe", isTemplate, 78);
        Citizen c4 = new Citizen("Lea", "Dalgaard", isTemplate, 78);
        Citizen c5 = new Citizen("Henning", "lavent", isTemplate, 78);

        searchBase.add(c1);
        searchBase.add(c2);
        searchBase.add(c3);
        searchBase.add(c4);
        searchBase.add(c5);
    }

    @AfterEach
    void tearDown() {
        searchBase.clear();
    }

    @Test
    void testSearchPersonInList() {
        int expected = 1;
        int actual = citizenSearcher.search(searchBase, "Henning").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSearchPersonNotInList4() {
        int expected = 0;
        int actual = citizenSearcher.search(searchBase, "Hunning").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSearchPersonAllInList() {
        int expected = 3;
        int actual = citizenSearcher.search(searchBase, "Doe").size();

        Assertions.assertEquals(expected, actual);
    }
}
